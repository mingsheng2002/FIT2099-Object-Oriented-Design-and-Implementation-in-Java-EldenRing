package game.actions.enemyactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;


/**
 * An Action for enemies to despawn from the game map.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class DespawnAction extends Action {

    /**
     * Constructor that adds capability of DESPAWNING to the enemies to be despawned.
     * @param target the actor to be despawned.
     * @see Status#DESPAWNING
     */
    public DespawnAction(Actor target) {
        target.addCapability(Status.DESPAWNING);
    }

    /**
     * When executed, the actor will be removed (despawned) from the game map.
     * @param actor The actor performing the despawn action.
     * @param map The game map the actor is on.
     * @return the result of enemy despawning.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // actor is currently despawning from map in this round
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Describes which enemy is despawning from the game map.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has despawned from the map";
    }
}