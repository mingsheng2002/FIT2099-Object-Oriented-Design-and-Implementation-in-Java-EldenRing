package game.actions;

import game.enums.Status;
import java.util.Random;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked.
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Random number generator.
	 */
	private Random rand = new Random();

	/**
	 * Weapon used for the attack.
	 */
	private Weapon weapon;

	/**
	 * Specifies whether or not to double the damage dealt by weapon.
	 */
	private boolean doubleAttackDamage = false;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack.
	 * @param direction the direction where the attack should be performed (only used for display purposes).
	 * @param weapon the weapon used for attack.
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack.
	 * @param direction the direction where the attack should be performed (only used for display purposes).
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The game map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction#execute(Actor, GameMap) 
	 * @see Status#DESPAWNING
	 * @see Status#PROTECTED
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";

		// if target is not yet defeated by other enemies AND
		// if target is not despawning from map in this round AND
		// if target is not performing Quickstep action in this round
		if (target.isConscious() && !target.hasCapability(Status.DESPAWNING) && !target.hasCapability(Status.PROTECTED)) {
			if (weapon == null) {
				weapon = actor.getIntrinsicWeapon();
			}

			if (actor.hasCapability(Status.DOUBLE_ATTACK_DAMAGE)){
				doubleAttackDamage = true;
			}

			// not meeting the chance
			if (RandomNumberGenerator.getRandomInt(100) >= weapon.chanceToHit()) {
				return actor + " misses " + target;
			}

			int damage = doubleAttackDamage ? (weapon.damage() * 2) : weapon.damage();
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage\n";
			target.hurt(damage);

			if (!target.isConscious()) {
				result += new DeathAction(actor).execute(target, map);
			}
		}
		return result;
	}

	/**
	 * Describes which target the actor is attacking with which weapon.
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
	}

	/**
	 * Doubling the damage dealt by weapon.
	 */
	public void activateDoubleAttackDamage() {
		this.doubleAttackDamage = true;
	}

	/**
	 * Getter to retrieve the target of attack.
	 */
	public Actor getTarget() {
		return target;
	}

	/**
	 * Setter to set the target of attack.
	 * @param target the target of attack.
	 */
	public void setTarget(Actor target) {
		this.target = target;
	}

	/**
	 * Getter to get the direction of attack.
	 * @return the direction of attack.
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Setter to set the direction of attack.
	 * @param direction the direction of attack
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * Getter to get the weapon used for attack.
	 * @return the weapon used for attack.
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Setter to set the weapon used for attack.
	 * @param weapon the weapon used for attack.
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
