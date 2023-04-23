package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Resettable;
import game.actions.AttackAction;
import game.enums.Status;

public abstract class HarmlessEnemy extends Actor {

  private Actor actorToBeRevived;
  private GameMap map;

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public HarmlessEnemy(String name, char displayChar, int hitPoints, Actor actorToBeRevived) {
    super(name, displayChar, hitPoints);
    this.actorToBeRevived = actorToBeRevived;
    this.receiveWeaponFrom(actorToBeRevived);
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    this.map = map;
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      actions.add(new AttackAction(otherActor, direction, otherActor.getWeaponInventory().get(0)));
    }
    return actions;
  }

  public void reviveActor(GameMap map){
    actorToBeRevived.addWeaponToInventory(this.getWeaponInventory().get(0));
    actorToBeRevived.increaseMaxHp(0);
    Location here = map.locationOf(this);
    map.removeActor(this);
    here.addActor(actorToBeRevived);
  }

  public void receiveWeaponFrom(Actor actorToBeRevived) {
    WeaponItem weaponItem = actorToBeRevived.getWeaponInventory().get(0);
    this.addWeaponToInventory(weaponItem);
    actorToBeRevived.removeWeaponFromInventory(weaponItem);
  }

  public GameMap getMap() {
    return map;
  }
}
