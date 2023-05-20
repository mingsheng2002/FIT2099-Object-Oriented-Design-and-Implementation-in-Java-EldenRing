package game.environments.summoninggrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.nonplayercharacters.summonables.Ally;
import game.actors.nonplayercharacters.summonables.Invader;
import game.enums.Status;

/**
 * A type of Summoning ground that can summon Summonables.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see SummoningGround
 */
public class SummonSign extends SummoningGround {

    /**
     * Constructor for SummonSign.
     */
    public SummonSign() {
        super('=');
    }

    /**
     * Returns a new collection of actions that contain the SummonAction and adds summonables.
     * @param actor The actor acting.
     * @param location The current location.
     * @param direction The direction of the ground from the actor.
     * @return A collection of actions.
     * @see SummoningGround#getSummonables()
     * @see Ally
     * @see Invader
     * @see SummoningGround#allowableActions(Actor, Location, String)
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        getSummonables().clear();
        getSummonables().add(new Ally());
        getSummonables().add(new Invader());
        return super.allowableActions(actor, location, direction);
    }

    /**
     * Checks if an actor can enter the SummonSign.
     * @param actor The actor to check.
     * @return True if the actor is hostile to enemies, false otherwise.
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
