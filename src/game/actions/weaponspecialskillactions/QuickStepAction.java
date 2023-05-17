package game.actions.weaponspecialskillactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.enums.Status;

/**
 * A special skill action that is provided by Great Knife, such that the actor can move away from the target after the attack,
 * and evade the attack from target.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see AttackAction
 */
public class QuickStepAction extends AttackAction {

    /**
     * Constructor that initialises the target, direction and target for the attack.
     * @param target the Actor to attack.
     * @param direction the direction where the attack should be performed (only used for display purposes).
     * @param weapon the weapon used for attack
     */
    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
    }

    /**
     * When executed, the actor will be moved to one of the valid exits in his surroundings, and then the player will be
     * added capability of PROTECTED in order to evade the attack from the target.
     * @param actor The actor performing the quickstep action.
     * @param map The game map the actor is on.
     * @return the result of quickstep action.
     * @see QuickStepAction#getValidExit(Actor, GameMap)
     * @see AttackAction#execute(Actor, GameMap)
     * @see Status#PROTECTED
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location locationToMove = getValidExit(actor, map);
        map.moveActor(actor, locationToMove);
        actor.addCapability(Status.PROTECTED);
        return super.execute(actor,map);
    }

    /**
     * Describes which actor performs the quickstep action on which target.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs \"Quickstep\" on " + getTarget();
    }

    /**
     * Helper method to get a valid exit in the surroundings that contains no other actors and the actor can enter this ground.
     * @param actor the actor to be moved.
     * @param map the game map that the actor is currently on.
     * @return a location in the surroundings of actor that contains no other actors
     */
    private Location getValidExit(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (!exit.getDestination().containsAnActor() && exit.getDestination().canActorEnter(actor)) {
                return exit.getDestination();
            }
        }
        // If every location in surrounding contains an actor or cannot be entered, stand still
        return here;
    }
}
