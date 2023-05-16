package game.actors.nonplayercharacters.enemies.canislupus;

import game.resets.Resettable;
import game.resets.ResetManager;
import game.weapons.unportableweapons.GiantDogHead;

/**
 * A Canis Lupus type of enemy called Giant Dog.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see CanisLupus
 * @see Resettable
 */
public class GiantDog extends CanisLupus implements Resettable {

  /**
   * The spawn chance of Giant Dog from its specific ground.
   */
  private static final int SPAWN_CHANCE = 4;
  /**
   * The chance of Giant Dog despawning from the game map.
   */
  private static final int DESPAWN_CHANCE = 10;
  /**
   * Health of Giant Dog.
   */
  private static final int HIT_POINTS = 693;
  /**
   * Minimum amount of runes dropped by Giant Dog if defeated by player.
   */
  private static final int MIN_RUNES_AWARD = 313;
  /**
   * Maximum amount of runes dropped by Giant Dog if defeated by player.
   */
  private static final int MAX_RUNES_AWARD = 1808;

  /**
   * Constructor for Giant Dog.
   * @see GiantDogHead
   * @see ResetManager#getInstance()
   * @see ResetManager#registerResettable(Resettable)
   */
  public GiantDog(){
    super("Giant Dog", 'G', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new GiantDogHead());
    ResetManager.getInstance().registerResettable(this);
  }

  /**
   * When the game is reset, this method will be executed and Giant Dog will be removed from the game map.
   */
  @Override
  public void reset() {
    if (this.getMap() != null ){
      getMap().removeActor(this);
    }
  }
}
