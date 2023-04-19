package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.LoneWolf;
import game.RandomNumberGenerator;

public class GustOfWind extends Ground {

  public static final int SPAWN_CHANCE = 33;

  /**
   * Constructor.
   *
   */
  public GustOfWind() {
    super('&');
  }

  /**
   * Ground can also experience the joy of time.
   * @param location The location of the Ground
   */
  public void tick(Location location) {
    if(RandomNumberGenerator.getRandomInt(100) <= SPAWN_CHANCE && !location.containsAnActor()){
      location.addActor(new LoneWolf());
    }
  }
}
