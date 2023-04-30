package game.actors.enemies.crustaceansenemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.enums.Status;

public abstract class Crustacean extends Enemy {
    /**
     * Constructor.
     *
     * @param name          the name of the Actor
     * @param displayChar   the character that will represent the Actor in the display
     * @param hitPoints     the Actor's starting hit points
     * @param despawnChance
     * @param minRunes
     * @param maxRunes
     */
    public Crustacean(String name, char displayChar, int hitPoints, int spawnChance, int despawnChance, int minRunes, int maxRunes) {
        super(name, displayChar, hitPoints, spawnChance, despawnChance, minRunes, maxRunes);
        this.addCapability(Status.FRIENDLY_TO_CRUSTACEANS_ENEMY);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // If adjacent actor can be attacked (not the same type) AND is not the trader
        if (!otherActor.hasCapability(Status.FRIENDLY_TO_CRUSTACEANS_ENEMY) && !otherActor.hasCapability(Status.PROTECTED)) {
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
