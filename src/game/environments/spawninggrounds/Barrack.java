package game.environments.spawninggrounds;

import game.enums.Status;

/**
 * A type of ground that can spawn Godrick Soldier in Stormveil Castle.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see SpawningGround
 */
public class Barrack extends SpawningGround{

    /**
     * Constructor for Barrack.
     * @see Status#SPAWN_STORMVEIL_CASTLE_SOLDIER
     */
    public Barrack() {
        super('B');
        this.addCapability(Status.SPAWN_STORMVEIL_CASTLE_SOLDIER);
    }
}
