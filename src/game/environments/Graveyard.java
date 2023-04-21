package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.RandomNumberGenerator;
import game.actors.enemies.HeavySkeletalSwordsman;

public class Graveyard extends Ground {

  public static final int SPAWN_CHANCE = 27;

  /**
   * Constructor.
   *
   */
  public Graveyard() {
    super('n');
  }

  /**
   * Ground can also experience the joy of time.
   * @param location The location of the Ground
   */
  public void tick(Location location) {
    if(RandomNumberGenerator.getRandomInt(100) < SPAWN_CHANCE && !location.containsAnActor()){
      location.addActor(new HeavySkeletalSwordsman());
    }
  }

}