package game.actors.nonplayercharacters.summonables;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public interface Summonable {

    int getSummonChance();

    void summoned(GameMap map, Location location);

    Location getSummonSpot(Location summonGroundLocation);
}
