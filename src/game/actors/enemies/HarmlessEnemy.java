package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Status;

public abstract class HarmlessEnemy extends Actor {

  private Enemy enemyToBeRevived;

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public HarmlessEnemy(String name, char displayChar, int hitPoints, Enemy deathEnemy) {
    super(name, displayChar, hitPoints);
    this.enemyToBeRevived = deathEnemy;
    deathEnemy.passWeapon(this);
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.RESTING)) {
      actions.add(new AttackAction(otherActor, direction, otherActor.getWeaponInventory().get(0)));
    }
    return actions;
  }

  public void reviveEnemy(GameMap map){
    enemyToBeRevived.addWeaponToInventory(this.getWeaponInventory().get(0));
    enemyToBeRevived.resetMaxHp(enemyToBeRevived.enemyMaxHitPoints);
    Location here = map.locationOf(this);
    map.removeActor(this);
    here.addActor(enemyToBeRevived);
  }
}
