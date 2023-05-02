package game.environments.restinggrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.RestAction;
import game.enums.Status;

/**
 * An unique ground that allows player to rest on it.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public abstract class SiteOfLostGrace extends Ground {

    /**
     * Constructor for SiteOfLostGrace.
     * @param displayChar the displace character of SiteOfLostGrace
     */
    public SiteOfLostGrace(char displayChar) {
        super(displayChar);
    }

    /**
     * Check if an Actor can enter the SiteOfLostGrace.
     * @param actor the Actor to check
     * @return true if actor has capability of HOSTILE_TO_ENEMY; false otherwise
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Returns a new collection of the Actions that contain the RestAction.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     * @see RestAction
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.containsAnActor()) {
            actions.add(new RestAction());
        }
        return actions;
    }
}
