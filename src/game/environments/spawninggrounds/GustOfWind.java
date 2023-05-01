package game.environments.spawninggrounds;

import game.enums.Status;

public class GustOfWind extends SpawningGround {

  /**
   * Constructor.
   *
   */
  public GustOfWind() {
    super('&');
    this.addCapability(Status.SPAWN_CANIS_LUPUS);
  }
}
