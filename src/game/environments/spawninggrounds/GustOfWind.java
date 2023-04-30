package game.environments.spawninggrounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.actors.enemies.canislupusenemies.GiantDog;
import game.actors.enemies.skeletalenemies.HeavySkeletalSwordsman;
import game.actors.enemies.skeletalenemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;
import game.actors.enemies.canislupusenemies.LoneWolf;

public class GustOfWind extends SpawningGround {

  /**
   * Constructor.
   *
   */
  public GustOfWind() {
    super('&');
  }

  @Override
  public Enemy getEastEnemy() {
    return new GiantDog();
  }

  @Override
  public Enemy getWestEnemy() {
    return new LoneWolf();
  }

}
