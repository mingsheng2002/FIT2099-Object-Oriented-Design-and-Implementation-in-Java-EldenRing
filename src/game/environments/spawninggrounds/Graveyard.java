package game.environments.spawninggrounds;

import game.actors.enemies.Enemy;
import game.actors.enemies.skeletons.SkeletalBandit;
import game.actors.enemies.skeletons.HeavySkeletalSwordsman;

public class Graveyard extends SpawningGround {

  /**
   * Constructor.
   *
   */
  public Graveyard() {
    super('n');
  }

  @Override
  public Enemy getEastEnemy() {
    return new SkeletalBandit();
  }

  @Override
  public Enemy getWestEnemy() {
    return new HeavySkeletalSwordsman();
  }

}
