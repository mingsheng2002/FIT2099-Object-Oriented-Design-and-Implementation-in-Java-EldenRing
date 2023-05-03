package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public class Floor extends Ground {

	/**
	 * Constructor for Floor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Check if an Actor can enter the floor
	 * @param actor the Actor to check
	 * @return true if the actor has capability of HOSTILE_TO_ENEMEY; false otherwise
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
	}
}
