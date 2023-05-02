package game.environments.spawninggrounds;

import game.enums.Status;

/**
 * A type of ground that can spawn canis lupus enemies.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see SpawningGround
 */
public class GustOfWind extends SpawningGround {

  /**
   * Constructor for GustOfWind.
   * @see Status#SPAWN_CANIS_LUPUS
   */
  public GustOfWind() {
    super('&');
    this.addCapability(Status.SPAWN_CANIS_LUPUS);
  }
}
