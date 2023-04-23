package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.RewardRunes;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.controllers.RunesManager;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements RewardRunes {

  private Map<Integer, Behaviour> behaviours = new HashMap<>();

  private int despawnChance;

  protected int enemyMaxHitPoints;

  private int minRunes;
  private int maxRunes;
  private GameMap map;

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Enemy(String name, char displayChar, int hitPoints, int despawnChance, int minRunes, int maxRunes) {
    super(name, displayChar, hitPoints);
    this.enemyMaxHitPoints = hitPoints;
    this.despawnChance = despawnChance;
    this.minRunes = minRunes;
    this.maxRunes = maxRunes;
    RunesManager.getInstance().registerRewardRunesActor(this);
    this.addCapability(Status.HOSTILE_TO_ENEMY);
    this.behaviours.put(2, new WanderBehaviour());
  }

  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.map = map;

    if (!this.hasCapability(Status.FOLLOWING) && RandomNumberGenerator.getRandomInt(100) < this.despawnChance) {
      return (new DespawnAction());
    }

    System.out.println(this + ": " + behaviours.values());  /////////////////////////////////////////////

    for (int key : behaviours.keySet()) {
      Behaviour behaviour = behaviours.get(key);
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        // If it is an Attack Action
        if (key == 0) {
          behaviours.remove(0);
        }
        return action;
      }
    }
    return new DoNothingAction();
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

    if (otherActor.hasCapability(Status.RESTING)){
      behaviours.put(1, new FollowBehaviour(otherActor));
      this.addCapability(Status.FOLLOWING);
    }

    // If adjacent actor can be attacked, add Attack Behaviour to enemy
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.toString().equals(this.toString())) {
      if (this.getWeaponInventory().isEmpty()) {
        behaviours.put(0, new AttackBehaviour(otherActor, direction));
      }
      else {
        behaviours.put(0, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
      }
    }

    ActionList actions = new ActionList();

    // If adjacent actor is Player, gives Player an Attack Action
    if (otherActor.hasCapability(Status.RESTING)) {
      actions.add(new AttackAction(this, direction));
      if (!otherActor.getWeaponInventory().isEmpty()) {
        for (WeaponItem weapon : otherActor.getWeaponInventory()) {
          actions.add(new AttackAction(this, direction, weapon));
        }
      }
    }
    return actions;
  }

  @Override
  public int getMinRunes(){
    return minRunes;
  }

  public int getMaxRunes(){
    return maxRunes;
  }

  public GameMap getMap() {
    return map;
  }
}
