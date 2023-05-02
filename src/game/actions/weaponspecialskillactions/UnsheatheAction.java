package game.actions.weaponspecialskillactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;

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
public class UnsheatheAction extends AttackAction {

    /**
     * Constructor that initialises the target, direction and target for the attack, as well as doubling the attack.
     * @param target the Actor to attack.
     * @param direction the direction where the attack should be performed (only used for display purposes).
     * @param weapon the weapon used for attack
     */
    public UnsheatheAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
        activateDoubleAttackDamage();
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal doubled damage to the target and determine whether the target is killed.
     * @param actor The actor performing the unsheathe action.
     * @param map The game map the actor is on.
     * @return the result of quickstep action.
     * @see AttackAction#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return super.execute(actor, map);
    }

    /**
     * Describes which actor performs the unsheathe action on which target.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs \"Unsheathe\" on " + getTarget();
    }
}
