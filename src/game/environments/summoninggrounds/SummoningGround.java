package game.environments.summoninggrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.RestAction;
import game.actions.playeractions.SummonAction;
import game.actors.nonplayercharacters.summonables.Summonable;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

public abstract class SummoningGround extends Ground {

    private List<Summonable> summonables = new ArrayList<>();
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SummoningGround(char displayChar) {
        super(displayChar);
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
            actions.add(new SummonAction(location, summonables));
        }
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    public List<Summonable> getSummonables() {
        return summonables;
    }
}
