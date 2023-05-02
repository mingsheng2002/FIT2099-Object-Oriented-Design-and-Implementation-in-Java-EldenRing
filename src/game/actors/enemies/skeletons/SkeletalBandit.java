package game.actors.enemies.skeletons;

import game.resets.Resettable;
import game.resets.ResetManager;
import game.weapons.portableweapons.Scimitar;

/**
 * A Skeleton type of enemy called Skeletal Bandit.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Skeleton
 * @see Resettable
 */
public class SkeletalBandit extends Skeleton implements Resettable {

  /**
   * The spawn chance of Skeletal Bandit from its specific ground.
   */
  private static final int SPAWN_CHANCE = 27;
  /**
   * The chance of Skeletal Bandit despawning from the game map.
   */
  private static final int DESPAWN_CHANCE = 10;
  /**
   * Health of Skeletal Bandit.
   */
  private static final int HIT_POINTS = 184;
  /**
   * Minimum amount of runes dropped by Skeletal Bandit if defeated by player.
   */
  private static final int MIN_RUNES_AWARD = 35;
  /**
   * Maximum amount of runes dropped by Skeletal Bandit if defeated by player.
   */
  private static final int MAX_RUNES_AWARD = 892;

  /**
   * Constructor for Skeletal Bandit.
   * @see Scimitar
   * @see ResetManager#getInstance()
   * @see ResetManager#registerResettable(Resettable)
   */
  public SkeletalBandit() {
    super("Skeletal Bandit", 'b', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new Scimitar());
    ResetManager.getInstance().registerResettable(this);
  }

  /**
   * When the game is reset, this method will be executed and Skeletal Bandit will be removed from the game map.
   */
  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}
