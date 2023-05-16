package game.actors.nonplayercharacters.enemies.canislupus;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.nonplayercharacters.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.enums.Status;

/**
 * A class representing the enemy type called Canis Lupus.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Enemy
 */
public abstract class CanisLupus extends Enemy {

    /**
     * Constructor for Canis Lupus.
     * @param name the name of the Enemy.
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints the Enemy's starting hit points.
     * @param spawnChance the Enemy's spawning chance from its specific ground.
     * @param despawnChance the Enemy's despawning chance from the game map.
     * @param minRunes the minimum runes dropped by Enemy if defeated by player.
     * @param maxRunes the maximum runes dropped by Enemy if defeated by player.
     * @see Status#FRIENDLY_TO_CANIS_LUPUS
     */
    public CanisLupus(String name, char displayChar, int hitPoints, int spawnChance, int despawnChance, int minRunes, int maxRunes) {
        super(name, displayChar, hitPoints, spawnChance, despawnChance, minRunes, maxRunes);
        this.addCapability(Status.FRIENDLY_TO_CANIS_LUPUS);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Enemy.
     * Also, Attack Behaviour will be put into the behaviours HashMap of this enemy, if it can attack the otherActor,
     * e.g. the otherActor is not of the same type of this enemy and otherActor is not having capability of PROTECTED.
     *
     * @param otherActor the Actor that might be performing attack on this Enemy.
     * @param direction  String representing the direction of the other Actor.
     * @param map        current GameMap
     * @return A collection of Actions.
     * @see Status#FRIENDLY_TO_CANIS_LUPUS
     * @see Status#PROTECTED
     * @see AttackBehaviour
     * @see Enemy#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // If adjacent actor can be attacked (not the same type) AND is not the trader
        if (!otherActor.hasCapability(Status.FRIENDLY_TO_CANIS_LUPUS) && !otherActor.hasCapability(Status.PROTECTED)) {
            if (this.getWeaponInventory().isEmpty()) {
                this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction));
            }
            else {
                this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
            }
        }
        return super.allowableActions(otherActor, direction, map);
    }
}
