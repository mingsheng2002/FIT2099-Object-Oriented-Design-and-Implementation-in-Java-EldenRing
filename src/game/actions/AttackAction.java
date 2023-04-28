package game.actions;

import game.enums.Status;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Random number generator
	 */
	private Random rand = new Random();

	/**
	 * Weapon used for the attack
	 */
	private Weapon weapon;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
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
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// if target is not yet defeated by other enemies AND
		// if target is not despawning from map in this round
		if(target.isConscious() && !target.hasCapability(Status.DESPAWNING)) {
			if (weapon == null) {
				weapon = actor.getIntrinsicWeapon();
			}

			if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
				return actor + " misses " + target + ".";
			}

			int damage = weapon.damage();
			String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
			if (!target.isConscious()) {
				result += new DeathAction(actor).execute(target, map);
			}

			return result;
		}
		return "";
	}

	/**
	 * Describes which target the actor is attacking with which weapon
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon") + ".";
	}

	public Actor getTarget() {
		return target;
	}

	public void setTarget(Actor target) {
		this.target = target;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
