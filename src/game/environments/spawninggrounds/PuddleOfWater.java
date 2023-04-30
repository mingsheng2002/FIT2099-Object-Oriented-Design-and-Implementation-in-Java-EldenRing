package game.environments.spawninggrounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.actors.enemies.canislupusenemies.GiantDog;
import game.actors.enemies.canislupusenemies.LoneWolf;
import game.actors.enemies.crustaceansenemies.GiantCrayfish;
import game.actors.enemies.skeletalenemies.HeavySkeletalSwordsman;
import game.actors.enemies.skeletalenemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;
import game.actors.enemies.crustaceansenemies.GiantCrab;

public class PuddleOfWater extends SpawningGround {

  /**
   * Constructor.
   *
   */
  public PuddleOfWater() {
    super('~');
  }

  @Override
  public Enemy getEastEnemy() {
    return new GiantCrayfish();
  }

  @Override
  public Enemy getWestEnemy() {
    return new GiantCrab();
  }
}
