package game.actors.enemies.crustaceans;

import game.resets.Resettable;
import game.resets.ResetManager;
import game.weapons.unportableweapons.GiantCrabPincer;

/**
 * A Crustacean type of enemy called Giant Crab.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Crustacean
 * @see Resettable
 */
public class GiantCrab extends Crustacean implements Resettable {

  /**
   * The spawn chance of Giant Crab from its specific ground.
   */
  private final static int SPAWN_CHANCE = 2;
  /**
   * The chance of Giant Crab despawning from the game map.
   */
  private static final int DESPAWN_CHANCE = 10;
  /**
   * Health of Giant Crab.
   */
  private static final int HIT_POINTS = 407;
  /**
   * Minimum amount of runes dropped by Giant Crab if defeated by player.
   */
  private static final int MIN_RUNES_AWARD = 318;
  /**
   * Maximum amount of runes dropped by Giant Crab if defeated by player.
   */
  private static final int MAX_RUNES_AWARD = 4961;

  /**
   * Constructor for Giant Crab.
   * @see GiantCrabPincer
   * @see ResetManager#getInstance()
   * @see ResetManager#registerResettable(Resettable)
   */
  public GiantCrab(){
    super("Giant Crab", 'C', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new GiantCrabPincer());
    ResetManager.getInstance().registerResettable(this);
  }

  /**
   * When the game is reset, this method will be executed and Giant Crab will be removed from the game map.
   */
  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}
