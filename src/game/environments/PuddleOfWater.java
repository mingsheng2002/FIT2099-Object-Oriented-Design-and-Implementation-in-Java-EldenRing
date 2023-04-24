package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.crustaceansenemies.GiantCrayfish;
import game.utils.RandomNumberGenerator;
import game.actors.enemies.crustaceansenemies.GiantCrab;

public class PuddleOfWater extends Ground {

  private final static int GIANT_CRAB_SPAWN_CHANCE = 90;//2;
  private final static int GIANT_CRAYFISH_SPAWN_CHANCE = 90;//1;

  /**
   * Constructor.
   *
   */
  public PuddleOfWater() {
    super('~');
  }

  /**
   * Ground can also experience the joy of time.
   * @param location The location of the Ground
   */
  public void tick(Location location) {
    int x = location.map().getXRange().max();
    int currLocation = location.x();

    if(currLocation <= x/2){
      if(RandomNumberGenerator.getRandomInt(100) < GIANT_CRAB_SPAWN_CHANCE && !location.containsAnActor()){
        location.addActor(new GiantCrab());
      }
    }
    else{
      if(RandomNumberGenerator.getRandomInt(100) < GIANT_CRAYFISH_SPAWN_CHANCE && !location.containsAnActor()){
        location.addActor(new GiantCrayfish());
      }
    }

  }
}
