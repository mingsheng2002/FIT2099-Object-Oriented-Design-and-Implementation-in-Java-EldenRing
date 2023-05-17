package game.actors.nonplayercharacters.enemies.harmlessenemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actors.nonplayercharacters.NonPlayerCharacter;
import game.enums.Status;

/**
 * A class representing the Harmless Enemy in the game, who causes no harm to the other actors, and is
 * formed temporarily on the Game Map due to the death of specific Enemy in the game.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see NonPlayerCharacter
 */
public abstract class HarmlessEnemy extends NonPlayerCharacter {

  /**
   * The Actor that will be revived with full health by this Harmless Enemy with certain condition.
   */
  private Actor actorToBeRevived;

  /**
   * Constructor for Harmless Enemy.
   * @param name the name of the Harmless Enemy.
   * @param displayChar the character that will represent the Harmless Enemy in the display
   * @param hitPoints the Harmless Enemy's starting hit points.
   * @param actorToBeRevived the Actor that will be revived with full health.
   */
  public HarmlessEnemy(String name, char displayChar, int hitPoints, Actor actorToBeRevived) {
    super(name, displayChar, hitPoints);
    this.actorToBeRevived = actorToBeRevived;
    passWeaponItem(actorToBeRevived, this);
  }

  /**
   * Returns a new collection of the Actions that the otherActor can do to the current Harmless Enemy.
   *
   * @param otherActor the Actor that might be performing attack on this Harmless Enemy.
   * @param direction  String representing the direction of the other Actor.
   * @param map        current GameMap
   * @return A collection of Actions.
   * @see ActionList
   * @see AttackAction
   * @see Status#HOSTILE_TO_ENEMY
   * @see Status#SPECIAL_SKILL
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
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
  /**
   * Revives an actor at the current location.
   * @param map the Game Map that the Harmless Enemy is currently on.
   */
  protected void reviveActor(GameMap map){
    passWeaponItem(this, actorToBeRevived);
    actorToBeRevived.increaseMaxHp(0);
    Location here = map.locationOf(this);
    map.removeActor(this);
    here.addActor(actorToBeRevived);
  }

  /**
   * Pass weapon item from the one Actor to another Actor.
   * @param provider the Actor that passes weapon item.
   * @param receiver the Actor that receives weapon item.
   */
  protected void passWeaponItem(Actor provider, Actor receiver) {
    if (!provider.getWeaponInventory().isEmpty()) {
      WeaponItem weaponItem = provider.getWeaponInventory().get(0);
      receiver.addWeaponToInventory(weaponItem);
      provider.removeWeaponFromInventory(weaponItem);
    }
  }
}
