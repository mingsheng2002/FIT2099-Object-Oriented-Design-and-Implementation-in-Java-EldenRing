package game.environments.summoninggrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.SummonAction;
import game.actors.nonplayercharacters.summonables.Summonable;
import game.enums.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that is extended by the grounds that can summon Summonables.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public abstract class SummoningGround extends Ground {

    /**
     * A list of summonables
     */
    private List<Summonable> summonables = new ArrayList<>();

    /**
     * Constructor for SummoningGround.
     * @param displayChar character to display for this type of terrain
     */
    public SummoningGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Returns a new collection of the Actions that contain the SummonAction if the actor is player.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions
     * @see SummonAction
     * @see Status#HOSTILE_TO_ENEMY
     * @see ActionList
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new SummonAction(location, summonables));
        }
        return actions;
    }

    /**
     * Getter to get a list of summonables
     * @return a list of summonables
     */
    public List<Summonable> getSummonables() {
        return summonables;
    }
}
