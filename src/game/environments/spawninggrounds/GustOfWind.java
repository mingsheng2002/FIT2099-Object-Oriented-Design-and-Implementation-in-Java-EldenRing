package game.environments.spawninggrounds;

import game.actors.enemies.Enemy;
import game.actors.enemies.canislupus.GiantDog;
import game.actors.enemies.canislupus.LoneWolf;

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
