package game.environments.spawninggrounds;

import game.enums.Status;

/**
 * A type of ground that can spawn skeleton enemies.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see SpawningGround
 */
public class Graveyard extends SpawningGround {

  /**
   * Constructor for Graveyard.
   * @see Status#SPAWN_SKELETON
   */
  public Graveyard() {
    super('n');
    this.addCapability(Status.SPAWN_SKELETON);
  }
}
