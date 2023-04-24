package game.actors.enemies.canislupusenemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.reset.Resettable;
import game.actors.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.reset.ResetManager;
import game.enums.Status;

public class GiantDog extends Enemy implements Resettable {

  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 693;
  private static final int DAMAGE = 314;
  private static final int HIT_RATE = 90;
  private static final int MIN_RUNES_AWARD = 313;
  private static final int MAX_RUNES_AWARD = 1808;

  public GiantDog(){
    super("Giant Dog", 'G', HIT_POINTS, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addCapability(Status.FRIENDLY_TO_CANIS_LUPUS_ENEMY);
    this.addCapability(Status.AREA_ATTACK);
    ResetManager.getInstance().registerResettable(this);
  }

  /**
   * At each turn, select a valid action to perform.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the valid action that can be performed in that iteration or null if no valid action is found
   */
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
    // If adjacent actor can be attacked (not the same type), add Attack Behaviour to this enemy
    if (!otherActor.hasCapability(Status.FRIENDLY_TO_CANIS_LUPUS_ENEMY)) { ///////////////
      if (this.getWeaponInventory().isEmpty()) {
        this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction));
      }
      else {
        this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
      }
    }
    return super.allowableActions(otherActor, direction, map);
  }

  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(DAMAGE, "slams", HIT_RATE);
  }

  @Override
  public void reset() {
    if (this.getMap() != null ){
      getMap().removeActor(this);
    }
  }
}
