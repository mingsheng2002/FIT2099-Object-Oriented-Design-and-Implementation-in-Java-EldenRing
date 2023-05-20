package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.nonplayercharacters.summonables.Summonable;
import game.resets.ResetManager;
import game.utils.RandomNumberGenerator;
import java.util.List;

/**
 * An Action for Ground to summon a guest from another realm at some location.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class SummonAction extends Action {

    /**
     * List of summonable actor
     */
    private List<Summonable> summonables;
    /**
     * Location of the summoning ground
     */
    private Location summonGroundLocation;
    /**
     * Location that the summonable actor summon
     */
    private Location summonSpot;

    /**
     * Constructor for SummonAction.
     * @param summonGroundLocation location of the summoning ground
     * @param summonables a list of summonable actor
     */
    public SummonAction(Location summonGroundLocation, List<Summonable> summonables){
        this.summonGroundLocation = summonGroundLocation;
        this.summonables = summonables;
    }

    /**
     * When execute, a summonable actor will choose by chance and find a surrounding spot to be summoned
     * If there's a spot to summon the actor, summon the actor; else do nothing
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The result of summoning the actor
     * @see ResetManager#getInstance()
     * @see ResetManager#isGameResetting()
     * @see Summonable#getSummonSpot(Location)
     * @see Summonable#summoned(GameMap, Location)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (!ResetManager.getInstance().isGameResetting()) {
            Summonable summonable = getSummonActor();
            summonSpot = summonable.getSummonSpot(summonGroundLocation);
            if (summonSpot == null) {
                result += actor + " failed to summon a guest from another realm";
            }
            else {
                summonable.summoned(map, summonSpot);
                result += "A hostile guest (" + summonable + ") has arrived. Prepare for battle!";
            }
        }
        return result;
    }

    /**
     * Describes a summonable actor has been summoned.
     * @param actor The actor performing the action.
     * @return  a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm";
    }

    /**
     * Returns a summonable actor by calculating the summon chance.
     * @return a summonable actor.
     * @see RandomNumberGenerator#getRandomInt(int)
     * @see Summonable#getSummonChance()
     */
    private Summonable getSummonActor() {
        Summonable aSummonable = null;
        int randChance = RandomNumberGenerator.getRandomInt(100);
        int lowerBound = 0;
        int upperBound = -1;
        for (Summonable summonable : summonables) {
            lowerBound = upperBound + 1;
            upperBound += summonable.getSummonChance();
            if (randChance >= lowerBound && randChance < upperBound) {
                aSummonable = summonable;
                break;
            }
        }
        return aSummonable;
    }
}
