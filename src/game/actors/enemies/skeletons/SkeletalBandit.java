package game.actors.enemies.skeletons;

import game.resets.Resettable;
import game.resets.ResetManager;
import game.weapons.portableweapons.Scimitar;

public class SkeletalBandit extends Skeleton implements Resettable {

  private static final int SPAWN_CHANCE = 27;
  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 184;
  private static final int MIN_RUNES_AWARD = 35;
  private static final int MAX_RUNES_AWARD = 892;

  /**
   * Constructor.
   *
   */
  public SkeletalBandit() {
    super("Skeletal Bandit", 'b', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new Scimitar());
    ResetManager.getInstance().registerResettable(this);
  }

  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }
}
