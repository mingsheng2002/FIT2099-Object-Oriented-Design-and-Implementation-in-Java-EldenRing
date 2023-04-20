package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.weapons.Grossmesser;

public class HeavySkeletalSwordsman extends Enemy{

  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 153;

  /**
   * Constructor.
   *
   */
  public HeavySkeletalSwordsman(){
    super("Heavy Skeletal Swordsman", 'q', HIT_POINTS, HeavySkeletalSwordsman.DESPAWN_CHANCE);
    this.addWeaponToInventory(new Grossmesser());
    this.addCapability(Status.RESPAWNABLE);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return super.playTurn(actions, lastAction, map, display);
  }

  /**
   * The lone wolf can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    return super.allowableActions(otherActor, direction, map);
  }

}
