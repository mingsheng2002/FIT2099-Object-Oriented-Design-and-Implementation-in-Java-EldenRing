package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.enemyactions.DespawnAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.controllers.RunesManager;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements RunesRewarder {

  private Map<Integer, Behaviour> behaviours = new HashMap<>();
  private int spawnChance;
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
  public Enemy(String name, char displayChar, int hitPoints, int spawnChance, int despawnChance, int minRunes, int maxRunes) {
    super(name, displayChar, hitPoints);
    this.enemyMaxHitPoints = hitPoints;
    this.spawnChance = spawnChance;
    this.despawnChance = despawnChance;
    this.minRunes = minRunes;
    this.maxRunes = maxRunes;
    this.behaviours.put(2, new WanderBehaviour());
    // if dies, then reward player with runes
    RunesManager.getInstance().registerRewardOwner(this);
  }

  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.map = map;

    if (!this.hasCapability(Status.FOLLOWING) && RandomNumberGenerator.getRandomInt(100) < this.despawnChance) {
      return (new DespawnAction(this));
    }

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
    ActionList actions = new ActionList();

    // If adjacent actor is Player, gives Enemy a Follow Behaviour & gives Player an Attack Action
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      behaviours.put(1, new FollowBehaviour(otherActor));
      this.addCapability(Status.FOLLOWING);

      actions.add(new AttackAction(this, direction));
      if (!otherActor.getWeaponInventory().isEmpty()) {
        for (WeaponItem weapon : otherActor.getWeaponInventory()) {
          actions.add(new AttackAction(this, direction, weapon));
          if(weapon.hasCapability(Status.SPECIAL_SKILL)){
            actions.add(weapon.getSkill(this,direction));
          }
        }
      }
    }
    return actions;
  }

  public Map<Integer, Behaviour> getBehaviours() {
    return behaviours;
  }

  public GameMap getMap() {
    return map;
  }

  public void increaseMaxHp(int points) {
    // remove Follow Behaviour before reviving
    if (this.behaviours.get(1) != null) {
      this.behaviours.remove(1);
    }
    super.increaseMaxHp(points);
  }

  @Override
  public int getMinRewardRunes(){
    return minRunes;
  }

  @Override
  public int getMaxRewardRunes(){
    return maxRunes;
  }

  public int getSpawnChance() {
    return spawnChance;
  }
}
