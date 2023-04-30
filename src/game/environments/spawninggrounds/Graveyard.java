package game.environments.spawninggrounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.actors.enemies.canislupusenemies.GiantDog;
import game.actors.enemies.canislupusenemies.LoneWolf;
import game.actors.enemies.skeletalenemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;
import game.actors.enemies.skeletalenemies.HeavySkeletalSwordsman;

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

//  @Override
//  public int getEastSpawnChance() {
//    return eastEnemy.getSpawnChance();
//  }
//
//  @Override
//  public int getWestSpawnChance() {
//    return westEnemy.getSpawnChance();
//  }

}
