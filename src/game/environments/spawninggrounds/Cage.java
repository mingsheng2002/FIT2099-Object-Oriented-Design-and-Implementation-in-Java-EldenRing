package game.environments.spawninggrounds;

import game.enums.Status;

/**
 * A type of ground that can spawn Stormveil Castle Dog.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see SpawningGround
 */
public class Cage extends SpawningGround{

    /**
     * Constructor for Cage.
     * @see Status#SPAWN_STORMVEIL_CASTLE_DOG
     */
    public Cage() {
        super('<');
        this.addCapability(Status.SPAWN_STORMVEIL_CASTLE_DOG);
    }
}
