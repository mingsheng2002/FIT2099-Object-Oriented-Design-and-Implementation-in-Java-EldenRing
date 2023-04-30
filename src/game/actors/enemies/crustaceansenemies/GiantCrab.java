package game.actors.enemies.crustaceansenemies;

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
import game.weapons.unportableweapons.GiantCrabPincer;

public class GiantCrab extends Crustacean implements Resettable {

  private final static int SPAWN_CHANCE = 2;
  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 407;
  private static final int MIN_RUNES_AWARD = 318;
  private static final int MAX_RUNES_AWARD = 4961;

  /**
   * Constructor.
   *
   */
  public GiantCrab(){
    super("Giant Crab", 'C', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new GiantCrabPincer());
    ResetManager.getInstance().registerResettable(this);
  }

  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}
