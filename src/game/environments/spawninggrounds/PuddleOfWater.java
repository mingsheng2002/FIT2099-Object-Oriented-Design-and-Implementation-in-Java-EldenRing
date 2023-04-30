package game.environments.spawninggrounds;

import game.actors.enemies.Enemy;
import game.actors.enemies.crustaceans.GiantCrayfish;
import game.actors.enemies.crustaceans.GiantCrab;

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
