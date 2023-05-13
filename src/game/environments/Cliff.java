package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.enums.Status;

/**
 * A type of ground that the player will fall off and get killed instantly.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public class Cliff extends Ground {

  /**
   * Constructor for Cliff
   */
  public Cliff(){
    super('+');
  }

  /**
   * Returns true if an Actor can enter this location.
   * @param actor the Actor to check
   * @return true if the Actor can enter this location
   * @see Status#HOSTILE_TO_ENEMY
   */
  public boolean canActorEnter(Actor actor) {
    return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
  }

  /**
   * Called once per turn to check if player is on this cliff.
   * The player will instantly get killed when steps on a cliff.
   * @param location The location of the Ground
   * @see Status#HOSTILE_TO_ENEMY
   * @see DeathAction#execute(Actor, GameMap)
   */
  @Override
  public void tick(Location location){
    if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
      Actor actor = location.getActor();
      actor.resetMaxHp(0);
      new DeathAction(null).execute(actor, location.map());
    }
  }
}
