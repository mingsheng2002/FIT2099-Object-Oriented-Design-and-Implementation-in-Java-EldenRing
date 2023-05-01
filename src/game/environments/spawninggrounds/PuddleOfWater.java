package game.environments.spawninggrounds;

import game.enums.Status;

public class PuddleOfWater extends SpawningGround {

  /**
   * Constructor.
   *
   */
  public PuddleOfWater() {
    super('~');
    this.addCapability(Status.SPAWN_CRUSTACEAN);
  }
}
