package game.actors.nonplayercharacters.summonables;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.archetypes.Archetype;
import game.actors.nonplayercharacters.NonPlayerCharacter;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.controllers.ArchetypeManager;
import game.enums.Status;
import game.resets.ResetManager;
import game.resets.Resettable;

/**
 * Class representing the Ally. It implements the Resettable, Summonable interfaces.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see NonPlayerCharacter
 * @see Resettable
 * @see Summonable
 */
public class Ally extends NonPlayerCharacter implements Resettable, Summonable {

    /**
     * An instance of Archetype
     */
    private Archetype archetype;
    /**
     * The summon chance of Ally from summon sign
     */
    private final static int SUMMON_CHANCE = 50;

    /**
     * Constructor for Ally.
     * @see Status#FRIENDLY_TO_PLAYER
     * @see ResetManager#getInstance()
     * @see ResetManager#registerResettable(Resettable)
     * @see ArchetypeManager#getInstance()
     * @see ArchetypeManager#chooseArchetypeAtRandom()
     * @see WanderBehaviour
     */
    public Ally() {
        super("Ally", 'A', 0);
        this.archetype = ArchetypeManager.getInstance().chooseArchetypeAtRandom();
        this.hitPoints = archetype.getHitPoints();
        this.addWeaponToInventory(archetype.getWeaponItem());
        this.addCapability(Status.FRIENDLY_TO_PLAYER);
        ResetManager.getInstance().registerResettable(this);
        this.getBehaviours().put(1, new WanderBehaviour());
    }

    /**
     * If the game is being reset during this round, return DoNothingAction for Ally; else
     * Select and return an action to perform for Ally on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     * @see DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (int key : getBehaviours().keySet()) {
            Behaviour behaviour = getBehaviours().get(key);
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                // If it is an Attack Action
                if (key == 0) {
                    getBehaviours().remove(0);
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to Ally.
     * Also, Attack Behaviour will be put into the behaviours HashMap of Ally, if it can attack the otherActor,
     * e.g. the otherActor is not Player, Ally, Trader and otherActor is not having capability of PROTECTED.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions
     * @see Status#FRIENDLY_TO_PLAYER
     * @see Status#HOSTILE_TO_ENEMY
     * @see Status#PROTECTED
     * @see AttackBehaviour
     * @see NonPlayerCharacter#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        // If adjacent actor can be attacked (not the same type) AND is not the trader
        if (!otherActor.hasCapability(Status.FRIENDLY_TO_PLAYER) && !otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(Status.PROTECTED)) {
            if (this.getWeaponInventory().isEmpty()) {
                this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction));
            } else {
                this.getBehaviours().put(0, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
            }
        }

        return super.allowableActions(otherActor, direction, map);
    }

    /**
     * When the game is reset, this method will be executed and Ally will be removed from the game map.
     * @see ResetManager#getInstance()
     * @see ResetManager#getPlayerIsResting()
     */
    @Override
    public void reset() {
        // if player is not resting(dies and resetting) and in current map there is Ally, remove Ally
        if ( !ResetManager.getInstance().getPlayerIsResting() && this.getMap() != null) {
            getMap().removeActor(this);
        }
    }

    /**
     * Getter that get the summon chance of Ally.
     * @return int that representing summon chance of Ally
     */
    @Override
    public int getSummonChance() {
        return SUMMON_CHANCE;
    }

    /**
     * Adds the Ally to the game map when summoned.
     * @param map the GameMap to add the Ally to
     * @param location The location to summon Ally
     */
    @Override
    public void summoned(GameMap map, Location location) {
        map.addActor(this, location);
        this.setMap(map);
    }

    /**
     * Returns the location where the Ally can be summoned.
     * @param summonGroundLocation the location of the summon sign
     * @return the location where the Ally can be summoned, null when there is no available location
     * that Ally can be summoned
     * @see Exit
     */
    @Override
    public Location getSummonSpot(Location summonGroundLocation) {
        for (Exit exit : summonGroundLocation.getExits()) {
            if (!exit.getDestination().containsAnActor() && exit.getDestination().canActorEnter(this)) {
                return exit.getDestination();
            }
        }
        return null;
    }
}
