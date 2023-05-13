package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Action for Actor to travel from one location to another location.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class TravelAction extends Action {

  /**
   * The location that the Actor travel to
   */
  private Location destination;

  /**
   * Constructor for TravelAction
   * @param destination the location that the Actor travel to
   */
  public TravelAction(Location destination){
    this.destination = destination;
  }

  /**
   * When execute, the actor will be removed from the current map and added to the amp that the actor want to reach
   * @param actor The actor performing the travel action
   * @param map The map the actor is on
   * @return the result of actor travelling to other location
   */
  @Override
  public String execute(Actor actor, GameMap map){
    map.removeActor(actor);
    map.addActor(actor, destination);
    return menuDescription(actor);
  }

  /**
   * Describe which actor travel to which map.
   * @param actor The actor performing the action.
   * @return a description used for the menu UI.
   */
  @Override
  public String menuDescription(Actor actor){
    return actor + " travel to " + destination.map();
  }
}
