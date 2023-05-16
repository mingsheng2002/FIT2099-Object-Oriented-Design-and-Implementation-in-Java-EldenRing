package game.actors.nonplayercharacters.enemies.crustaceans;

import game.actors.nonplayercharacters.enemies.crustaceans.Crustacean;
import game.resets.Resettable;
import game.resets.ResetManager;
import game.weapons.unportableweapons.GiantCrayfishPincer;

/**
 * A Crustacean type of enemy called Giant Crayfish.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Crustacean
 * @see Resettable
 */
public class GiantCrayfish extends Crustacean implements Resettable {

  /**
   * The spawn chance of Giant Crayfish from its specific ground.
   */
  private final static int SPAWN_CHANCE = 1;
  /**
   * The chance of Giant Crayfish despawning from the game map.
   */
  private static final int DESPAWN_CHANCE = 10;
  /**
   * Health of Giant Crayfish.
   */
  private static final int HIT_POINTS = 4803;
  /**
   * Minimum amount of runes dropped by Giant Crayfish if defeated by player.
   */
  private static final int MIN_RUNES_AWARD = 500;
  /**
   * Maximum amount of runes dropped by Giant Crayfish if defeated by player.
   */
  private static final int MAX_RUNES_AWARD = 2374;

  /**
   * Constructor for Giant Crayfish.
   * @see GiantCrayfishPincer
   * @see ResetManager#getInstance()
   * @see ResetManager#registerResettable(Resettable)
   */
  public GiantCrayfish() {
    super("Giant Crayfish", 'R', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new GiantCrayfishPincer());
    ResetManager.getInstance().registerResettable(this);
  }

  /**
   * When the game is reset, this method will be executed and Giant Crayfish will be removed from the game map.
   */
  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}
