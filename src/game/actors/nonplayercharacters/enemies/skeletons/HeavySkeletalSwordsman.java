package game.actors.nonplayercharacters.enemies.skeletons;

import game.resets.Resettable;
import game.resets.ResetManager;
import game.weapons.portableweapons.Grossmesser;

/**
 * A Skeleton type of enemy called Heavy Skeleton Swordsman.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Skeleton
 * @see Resettable
 */
public class HeavySkeletalSwordsman extends Skeleton implements Resettable {

  /**
   * The spawn chance of Heavy Skeleton Swordsman from its specific ground.
   */
  private static final int SPAWN_CHANCE = 27;
  /**
   * The chance of Heavy Skeleton Swordsman despawning from the game map.
   */
  private static final int DESPAWN_CHANCE = 10;
  /**
   * Health of Heavy Skeleton Swordsman.
   */
  private static final int HIT_POINTS = 153;
  /**
   * Minimum amount of runes dropped by Heavy Skeleton Swordsman if defeated by player.
   */
  private static final int MIN_RUNES_AWARD = 35;
  /**
   * Maximum amount of runes dropped by Heavy Skeleton Swordsman if defeated by player.
   */
  private static final int MAX_RUNES_AWARD = 892;

  /**
   * Constructor for Heavy Skeleton Swordsman.
   * @see Grossmesser
   * @see ResetManager#getInstance()
   * @see ResetManager#registerResettable(Resettable)
   */
  public HeavySkeletalSwordsman(){
    super("Heavy Skeletal Swordsman", 'q', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new Grossmesser());
    ResetManager.getInstance().registerResettable(this);
  }

  /**
   * When the game is reset, this method will be executed and Heavy Skeleton Swordsman will be removed from the game map.
   */
  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}
