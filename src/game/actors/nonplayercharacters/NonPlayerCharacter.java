package game.actors.nonplayercharacters;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing the Non Player Character in the game.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Actor
 */
public abstract class NonPlayerCharacter extends Actor {

    /**
     * A HashMap of behaviours for every Non Player Character, used to prioritise their behaviour to be performed in every round of the game.
     */
    private Map<Integer, Behaviour> behaviours;
    /**
     * Health of Non Player Character
     */
    private int npcHitPoints;
    /**
     * TBC
     */
    private GameMap map = null;

    /**
     * Constructor for NonPlayerCharacter.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public NonPlayerCharacter(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        behaviours = new HashMap<>();
        this.npcHitPoints = hitPoints;
    }

    /**
     * Getter to get the HashMap of behaviours.
     * @return a HashMap of behaviour
     * TBC
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Getter to get the Game Map that the Non Player Character is currently on.
     * @return the Game Map that the Non Player Character is currently on.
     */
    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
