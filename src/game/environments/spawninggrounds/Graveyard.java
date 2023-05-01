package game.environments.spawninggrounds;

import game.enums.Status;

public class Graveyard extends SpawningGround {

  /**
   * Constructor.
   *
   */
  public Graveyard() {
    super('n');
    this.addCapability(Status.SPAWN_SKELETON);
  }
}
