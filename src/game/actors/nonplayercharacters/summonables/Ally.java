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

public class Ally extends NonPlayerCharacter implements Resettable, Summonable {

    private Archetype archetype;
    private final static int SUMMON_CHANCE = 50;

    /**
     * Constructor for Ally.
     * @see Status#FRIENDLY_TO_PLAYER
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

    @Override
    public void reset() {
        // if player is not resting(dies and resetting) and in current map there is Ally, remove Ally
        if ( !ResetManager.getInstance().getPlayerIsResting() && this.getMap() != null) {
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
