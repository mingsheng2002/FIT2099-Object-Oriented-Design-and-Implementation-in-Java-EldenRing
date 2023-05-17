package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.nonplayercharacters.summonables.Summonable;
import game.resets.ResetManager;
import game.utils.RandomNumberGenerator;

import java.util.List;

public class SummonAction extends Action {

    private List<Summonable> summonables;
    private Location summonGroundLocation;
    private Location summonSpot;

    public SummonAction(Location summonGroundLocation, List<Summonable> summonables){
        this.summonGroundLocation = summonGroundLocation;
        this.summonables = summonables;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm";
    }

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
