package game.actors.nonplayercharacters.summonables;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A Summonable interface represents an actor that can be summoned from another realm. It gives promises to every
 * actor that can be summoned from another realm such that their classes implementing this interface should have also
 * implemented these methods listed below.
 *
 * This interface is crucial when summoning an actor from another realm to calculate the summon chance, the get the summon
 * location and to place itself to the specific location in a game map via the implementation of the specific methods.
 *
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public interface Summonable {

    /**
     * Getter that get the summon chance of the summonable actor.
     * @return int that representing summon chance
     */
    int getSummonChance();

    /**
     * Adds the summonable actor to the game map when summoned.
     * @param map the GameMap to add the actor to
     * @param location The location to summon the actor
     */
    void summoned(GameMap map, Location location);

    /**
     * Returns the location where the summonable actor can be summoned.
     * @param summonGroundLocation the location of the summon ground
     * @return the location where the actor can be summoned, null when there is no available location
     * that Ally can be summoned
     */
    Location getSummonSpot(Location summonGroundLocation);
}
