package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.TravelAction;
import game.enums.Status;

/**
 * A type of ground that allow the player to travel between maps.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public class GoldenFogDoor extends Ground {

  /**
   * The destination map that the Actor travel to
   */
  private GameMap destinationMap;
  /**
   * The x coordinate of the destination location
   */
  private int xDestination;
  /**
   * The y coordinate of the destination location
   */
  private int yDestination;

  /**
   * Constructor for GoldenFogDoor
   * @param destinationMap The destination map to travel to
   * @param x x-coordinate of door location in the destination map
   * @param y y-coordinate of door location in the destination map
   */
  public GoldenFogDoor(GameMap destinationMap, int x, int y){
    super('D');
    this.destinationMap = destinationMap;
    this.xDestination = x;
    this.yDestination = y;
  }

  /**
   * Returns true if an Actor can enter this location.
   * @param actor the Actor to check
   * @return true if the Actor can enter this location
   * @see Status#HOSTILE_TO_ENEMY
   */
  @Override
  public boolean canActorEnter(Actor actor) {
    return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
  }

  /**
   * Getter to get the door location in the destination map
   * @return the door location in the destination map
   */
  public Location getDestinationLocation(){
    return destinationMap.at(xDestination, yDestination);
  }

  /**
   * Give Actor, specifically the player, a TravelAction to travel from the door in current map
   * to the door in the destination map.
   * @param actor the Actor that is going to travel to other map
   * @param location the current Location
   * @param direction the direction of the Ground from the Actor
   * @return a list of actions that contain TravelAction to be performed by the Actor
   * @see Status#HOSTILE_TO_ENEMY
   * @see TravelAction
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();

    if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
      actions.add(new TravelAction(getDestinationLocation()));
    }

    return actions;
  }
}
