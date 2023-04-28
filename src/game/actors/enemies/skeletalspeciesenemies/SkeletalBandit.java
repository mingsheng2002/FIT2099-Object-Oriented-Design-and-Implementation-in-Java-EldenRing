package game.actors.enemies.skeletalspeciesenemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.Resettable;
import game.actors.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.resets.ResetManager;
import game.enums.Status;
import game.weapons.Scimitar;

public class SkeletalBandit extends Enemy implements Resettable {

  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 184;
  private static final int MIN_RUNES_AWARD = 35;
  private static final int MAX_RUNES_AWARD = 892;

  /**
   * Constructor.
   *
   */
  public SkeletalBandit() {
    super("Skeletal Bandit", 'b', HIT_POINTS, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new Scimitar());
    this.addCapability(Status.FRIENDLY_TO_SKELETAL_SPECIES_ENEMY);
    this.addCapability(Status.REVIVABLE);
    ResetManager.getInstance().registerResettable(this);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return super.playTurn(actions, lastAction, map, display);
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    // If adjacent actor can be attacked (not the same type), add Attack Behaviour to this enemy
    if (!otherActor.hasCapability(Status.FRIENDLY_TO_SKELETAL_SPECIES_ENEMY)) { ///////////////
      if (this.getWeaponInventory().isEmpty()) {
        this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction));
      }
      else {
        this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
      }
    }
    return super.allowableActions(otherActor, direction, map);
  }

  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}
