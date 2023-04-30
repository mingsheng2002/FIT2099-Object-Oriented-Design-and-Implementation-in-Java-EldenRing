package game.actors.enemies.canislupus;

import game.resets.Resettable;
import game.resets.ResetManager;
import game.weapons.unportableweapons.GiantDogHead;

public class GiantDog extends CanisLupus implements Resettable {

  private static final int SPAWN_CHANCE = 4;
  private static final int DESPAWN_CHANCE = 10;
  private static final int HIT_POINTS = 693;
  private static final int MIN_RUNES_AWARD = 313;
  private static final int MAX_RUNES_AWARD = 1808;

  public GiantDog(){
    super("Giant Dog", 'G', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
    this.addWeaponToInventory(new GiantDogHead());
    ResetManager.getInstance().registerResettable(this);
  }

  @Override
  public void reset() {
    if (this.getMap() != null ){
      getMap().removeActor(this);
    }
  }
}
