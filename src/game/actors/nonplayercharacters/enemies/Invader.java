package game.actors.nonplayercharacters.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.archetypes.Archetype;
import game.behaviours.AttackBehaviour;
import game.enums.Status;
import game.resets.ResetManager;
import game.resets.Resettable;

public class Invader extends Enemy implements Resettable {
    /**
     * The spawn chance of Invader from its specific ground.
     */
    private static final int SPAWN_CHANCE = 50;
    /**
     * The chance of Invader despawning from the game map.
     */
    private static final int DESPAWN_CHANCE = 0;
    /**
     * Minimum amount of runes dropped by Invader if defeated by player.
     */
    private static final int MIN_RUNES_AWARD = 1358;
    /**
     * Maximum amount of runes dropped by Invader if defeated by player.
     */
    private static final int MAX_RUNES_AWARD = 5578;
    private Archetype archetype;

    /**
     * Constructor for Invader.
     */
    public Invader(Archetype archetype) {
        super("Invader",'ඞ', archetype.getHitPoints(), SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
        this.archetype = archetype;
        this.addWeaponToInventory(archetype.getWeaponItem());
        this.addCapability(Status.FRIENDLY_TO_INVADER);
        ResetManager.getInstance().registerResettable(this);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // If adjacent actor can be attacked (not the same type) AND is not the trader
        if (!otherActor.hasCapability(Status.FRIENDLY_TO_INVADER) && !otherActor.hasCapability(Status.PROTECTED)) {
            if (this.getWeaponInventory().isEmpty()) {
                this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction));
            }
            else {
                this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
            }
        }
        return super.allowableActions(otherActor, direction, map);
    }

    @Override
    public void reset() {
        // if player is not resting(dies and resetting) and in current map there is Ally, remove Ally
        if (!ResetManager.getInstance().getPlayerIsResting() && this.getMap() != null) {
            getMap().removeActor(this);
        }
    }

}

