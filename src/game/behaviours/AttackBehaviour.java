package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;
import game.enums.Status;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;

/**
 * A behaviour that allow NPC to attack to other actor.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Behaviour
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Direction that the attack perform to.
     */
    private String direction;
    /**
     * Weapon that the NPC used to attack.
     */
    private Weapon weapon = null;
    /**
     * Actor that get the attack
     */
    private Actor target;

    /**
     * Constructor for AttackBehaviour with weapon.
     * @param target the actor that get the attack
     * @param direction the direction that the attack perform to
     * @param weapon the weapon that NPC used to attack
     */
    public AttackBehaviour(Actor target, String direction, Weapon weapon) {
        this.direction = direction;
        this.weapon = weapon;
        this.target = target;
    }

    /**
     * Constructor for AttackBehaviour without a weapon.
     * @param target the actor that get the attack
     * @param direction the direction that the attack perform to
     */
    public AttackBehaviour(Actor target, String direction) {
        this.direction = direction;
        this.target = target;
    }

    /**
     * Return the appropriate AttackAction after checking the conditions
     * @param actor the Actor that has the Attack Behaviour
     * @param map the GameMap containing the Actor
     * @return AreaAttackAction, AttackAction with weapon or AttackAction without weapon
     * @see RandomNumberGenerator
     * @see AreaAttackAction
     * @see AttackAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!actor.getWeaponInventory().isEmpty() && actor.getWeaponInventory().get(0).hasCapability(Status.AREA_ATTACK) && RandomNumberGenerator.getRandomInt(100) < 50) {
            return new AreaAttackAction(actor.getWeaponInventory().get(0));
        }
        else {
            if (weapon == null) {
                return new AttackAction(this.target, this.direction);
            } else {
                return new AttackAction(this.target, this.direction, actor.getWeaponInventory().get(0));
            }
        }
    }
}
