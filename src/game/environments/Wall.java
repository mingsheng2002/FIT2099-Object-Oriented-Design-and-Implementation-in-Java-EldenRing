package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public class Wall extends Ground {

	/**
	 * Constructor for Wall.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Check if an Actor can enter the floor.
	 * @param actor the Actor to check
	 * @return true if the actor has capability of HOSTILE_TO_ENEMEY; false otherwise
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Wall can block objects.
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
