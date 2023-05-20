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

/**
 * Class representing the Invader. It implements the Resettable, Summonable interfaces.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Enemy
 * @see Resettable
 * @see Summonable
 */
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
    /**
     * The summon chance of Invader from summon sign
     */
    private final static int SUMMON_CHANCE = 50;
    /**
     * An instance of Archetype
     */
    private Archetype archetype;

    /**
     * Constructor for Invader.
     * @see ArchetypeManager#getInstance()
     * @see ArchetypeManager#chooseArchetypeAtRandom()
     * @see Status#FRIENDLY_TO_INVADER
     * @see ResetManager#getInstance()
     * @see ResetManager#registerResettable(Resettable)
     */
    public Invader() {
        super("Invader",'ඞ', 0, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
        this.archetype = ArchetypeManager.getInstance().chooseArchetypeAtRandom();
        this.hitPoints = archetype.getHitPoints();
        this.addWeaponToInventory(archetype.getWeaponItem());
        this.addCapability(Status.FRIENDLY_TO_INVADER);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to Invader.
     * Also, Attack Behaviour will be put into the behaviours HashMap of Invader, if it can attack the otherActor,
     * e.g. the otherActor is not of the same type of Invader and otherActor is not having capability of PROTECTED.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions
     * @see Status#FRIENDLY_TO_INVADER
     * @see Status#PROTECTED
     * @see AttackBehaviour
     * @see Enemy#allowableActions(Actor, String, GameMap)
     */
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

    /**
     * When the game is reset, this method will be executed and Invader will be removed from the game map.
     * @see ResetManager#getInstance()
     * @see ResetManager#getPlayerIsResting()
     */
    @Override
    public void reset() {
        // if player is not resting(dies and resetting) and in current map there is Ally, remove Ally
        if (!ResetManager.getInstance().getPlayerIsResting() && this.getMap() != null) {
            getMap().removeActor(this);
        }
    }

    /**
     * Getter that get the summon chance of Invader.
     * @return int that representing summon chance of Invader
     */
    @Override
    public int getSummonChance() {
        return SUMMON_CHANCE;
    }

    /**
     * Adds the Invader to the game map when summoned.
     * @param map the GameMap to add the Invader to
     * @param location The location to summon Invader
     */
    @Override
    public void summoned(GameMap map, Location location) {
        map.addActor(this, location);
        this.setMap(map);
    }

    /**
     * Returns the location where the Invader can be summoned.
     * @param summonSignLocation the location of the summon sign
     * @return the location where the Invader can be summoned, null when there is no available location
     * that Invader can be summoned
     * @see Exit
     */
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

