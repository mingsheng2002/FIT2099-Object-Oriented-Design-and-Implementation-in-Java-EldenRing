package game.actors.nonplayercharacters.summonables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.archetypes.Archetype;
import game.actors.nonplayercharacters.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.controllers.ArchetypeManager;
import game.enums.Status;
import game.resets.ResetManager;
import game.resets.Resettable;

public class Invader extends Enemy implements Resettable, Summonable {
    /**
     * The spawn chance of Invader from its specific ground.
     */
    private static final int SPAWN_CHANCE = 0;
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
    private final static int SUMMON_CHANCE = 50;
    private Archetype archetype;

    /**
     * Constructor for Invader.
     */
    public Invader() {
        super("Invader",'ඞ', 0, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
        this.archetype = ArchetypeManager.getInstance().chooseArchetypeAtRandom();
        this.hitPoints = archetype.getHitPoints();
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

    @Override
    public int getSummonChance() {
        return SUMMON_CHANCE;
    }

    @Override
    public void summoned(GameMap map, Location location) {
        map.addActor(this, location);
        this.setMap(map);
    }

    @Override
    public Location getSummonSpot(Location summonSignLocation) {
        for (Exit exit : summonSignLocation.getExits()) {
            if (!exit.getDestination().containsAnActor() && exit.getDestination().canActorEnter(this)) {
                return exit.getDestination();
            }
        }
        return null;
    }
}

