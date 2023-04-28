package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.skeletalspeciesenemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;
import game.actors.enemies.skeletalspeciesenemies.HeavySkeletalSwordsman;

public class Graveyard extends Ground {

  public static final int HEAVY_SKELETAL_SWORDSMAN_SPAWN_CHANCE = 27;
  public static final int SKELETAL_BANDIT_SPAWN_CHANCE = 27;

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
    int x = location.map().getXRange().max();
    int currLocation = location.x();

    if(currLocation <= x/2){
      if(RandomNumberGenerator.getRandomInt(100) < HEAVY_SKELETAL_SWORDSMAN_SPAWN_CHANCE && !location.containsAnActor()){
        location.addActor(new HeavySkeletalSwordsman());
      }
    }
    else{
      if(RandomNumberGenerator.getRandomInt(100) < SKELETAL_BANDIT_SPAWN_CHANCE && !location.containsAnActor()){
        location.addActor(new SkeletalBandit());
      }
    }
  }

}
