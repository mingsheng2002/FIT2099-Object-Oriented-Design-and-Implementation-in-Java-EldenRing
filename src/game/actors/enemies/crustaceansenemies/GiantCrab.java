package game.actors.enemies.crustaceansenemies;

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

public class GiantCrab extends Enemy implements Resettable {

  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 407;
  private static final int DAMAGE = 208;
  private static final int HIT_RATE = 90;
  private static final int MIN_RUNES_AWARD = 318;
  private static final int MAX_RUNES_AWARD = 4961;

  /**
   * Constructor.
   *
   */
  public GiantCrab(){
    super("Giant Crab", 'C', HIT_POINTS, GiantCrab.DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addCapability(Status.FRIENDLY_TO_CRUSTACEANS_ENEMY);
    this.addCapability(Status.AREA_ATTACK);
    ResetManager.getInstance().registerResettable(this);
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

    // If adjacent actor can be attacked (not the same type), add Attack Behaviour to this enemy
    if (!otherActor.hasCapability(Status.FRIENDLY_TO_CRUSTACEANS_ENEMY)) { ///////////////
      if (this.getWeaponInventory().isEmpty()) {
        this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction));
      }
      else {
        this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
      }
    }

    return super.allowableActions(otherActor, direction, map);
  }

  public IntrinsicWeapon getIntrinsicWeapon(){
    return new IntrinsicWeapon(DAMAGE, "slams", HIT_RATE);
  }

  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}