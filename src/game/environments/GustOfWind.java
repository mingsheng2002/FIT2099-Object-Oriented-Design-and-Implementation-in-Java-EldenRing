package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.canislupusenemies.GiantDog;
import game.utils.RandomNumberGenerator;
import game.actors.enemies.canislupusenemies.LoneWolf;

public class GustOfWind extends Ground {

  public static final int LONE_WOLF_SPAWN_CHANCE = 90; //33;
  public static final int GIANT_DOG_SPAWN_CHANCE = 90; //4;

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
    int x = location.map().getXRange().max();
    int currLocation = location.x();

    if(currLocation <= x/2){
      if(RandomNumberGenerator.getRandomInt(100) < LONE_WOLF_SPAWN_CHANCE && !location.containsAnActor()){
        location.addActor(new LoneWolf());
      }
    }
    else{
      if(RandomNumberGenerator.getRandomInt(100) < GIANT_DOG_SPAWN_CHANCE && !location.containsAnActor()){
        location.addActor(new GiantDog());
      }
    }

  }
}
