package game.environments.spawninggrounds;

import game.enums.Status;

/**
 * A type of ground that can spawn crustacean enemies.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see SpawningGround
 */
public class PuddleOfWater extends SpawningGround {

  /**
   * Constructor for PuddleOfWater.
   * @see Status#SPAWN_CRUSTACEAN
   */
  public PuddleOfWater() {
    super('~');
    this.addCapability(Status.SPAWN_CRUSTACEAN);
  }
}
