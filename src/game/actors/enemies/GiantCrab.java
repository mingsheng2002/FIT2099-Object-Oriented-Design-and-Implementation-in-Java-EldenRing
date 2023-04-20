package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;

public class GiantCrab extends Enemy{

  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 407;
  private static final int DAMAGE = 208;
  private static final int HIT_RATE = 90;
  private static final int SKILL_CHANCE = 50;

  /**
   * Constructor.
   *
   */
  public GiantCrab(){
    super("Giant Crab", 'C', HIT_POINTS, GiantCrab.DESPAWN_CHANCE);
    this.addCapability(Status.AREA_ATTACK);
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

  public IntrinsicWeapon getIntrinsicWeapon(){
    return new IntrinsicWeapon(DAMAGE, "slams", HIT_RATE);
  }
}
