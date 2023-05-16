package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.RestAction;
import game.actions.playeractions.SummonAction;
import game.enums.Status;

/**
 * TBC
 * A type of ground that the player will fall off and get killed instantly.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public class Summon extends Ground {

    /**
     * Constructor for Summon.
     */
    public Summon() {
        super('=');
    }

    /**
     * Returns a new collection of the Actions that contain the SummonAction.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     * @see RestAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new SummonAction(location));
        }
        return actions;
    }
}
