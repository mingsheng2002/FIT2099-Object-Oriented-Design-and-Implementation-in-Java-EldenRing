package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor {

  private Map<Integer, Behaviour> behaviours = new HashMap<>();

  private int despawnChance;

  protected int enemyMaxHitPoints;

  private int minRune;
  private int maxRune;

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Enemy(String name, char displayChar, int hitPoints, int despawnChance, int minRune, int maxRune) {
    super(name, displayChar, hitPoints);
    this.enemyMaxHitPoints = hitPoints;
    this.despawnChance = despawnChance;
    this.minRune = minRune;
    this.maxRune = maxRune;
    this.addCapability(Status.HOSTILE_TO_ENEMY);
    this.behaviours.put(2, new WanderBehaviour());
  }

  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    if (!this.hasCapability(Status.FOLLOWING) && RandomNumberGenerator.getRandomInt(100) < this.despawnChance) {
      System.out.println(this + " has despawned from map");
      return (new DeathAction(this));
    }

    System.out.println(this + ": " + behaviours.values());
    for (Behaviour behaviour : behaviours.values()) {
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        if (action instanceof AttackAction || action instanceof AreaAttackAction) {
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
    // If otherActor is different Enemy type
    if (!otherActor.equals(this) && !otherActor.hasCapability(Status.RESTING) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      if (otherActor.getWeaponInventory().isEmpty()) {
        ((Enemy)otherActor).getBehaviours().put(0, new AttackBehaviour(this, direction));
      }
      else {
        ((Enemy)otherActor).getBehaviours().put(0, new AttackBehaviour(this, direction, otherActor.getWeaponInventory().get(0)));
      }
    }
    // if otherActor is Player
    else if (otherActor.hasCapability(Status.RESTING)) {
      actions.add(new AttackAction(this, direction));
      if (!otherActor.getWeaponInventory().isEmpty()) {
        for (WeaponItem weapon : otherActor.getWeaponInventory()) {
            actions.add(new AttackAction(this, direction, weapon));
            if (weapon.hasCapability(Status.AREA_ATTACK)) {
              actions.add(new AreaAttackAction(this, weapon));
          }
        }
        // // check for special skill attack - Area Attack - if holding Grossmesser - only add for once
      }
    }
    return actions;
  }

  public void passWeapon(HarmlessEnemy harmlessEnemy) {
    System.out.println(this.getWeaponInventory().get(0));
    WeaponItem weapon =  this.getWeaponInventory().get(0);
    harmlessEnemy.addWeaponToInventory(weapon);
    this.removeWeaponFromInventory(weapon);
  }

  public Map<Integer, Behaviour> getBehaviours() {
    return behaviours;
  }

  public int getMinRune(){
    return minRune;
  }

  public int getMaxRune(){
    return maxRune;
  }
}
