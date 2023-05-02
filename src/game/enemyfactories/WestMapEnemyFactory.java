package game.enemyfactories;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.enemies.Enemy;
import game.actors.enemies.canislupus.LoneWolf;
import game.actors.enemies.crustaceans.GiantCrab;
import game.actors.enemies.skeletons.HeavySkeletalSwordsman;
import game.enums.Status;


/**
 * A class spawn enemies on West side of the map.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see EnemyFactory
 */
public class WestMapEnemyFactory extends EnemyFactory {

    /**
     * Constructor of WestMapEnemyFactory
     * @param ground ground that spawn enemy
     */
    public WestMapEnemyFactory(Ground ground) {
        super(ground);
    }

    /**
     * This method determine which enemy to spawn on West side of the map based on the ground capability
     * @return spawnEnemy an enemy that has spawned from the ground
     * @see Enemy
     * @see HeavySkeletalSwordsman
     * @see LoneWolf
     * @see GiantCrab
     * @see Status#SPAWN_SKELETON
     * @see Status#SPAWN_CANIS_LUPUS
     * @see Status#SPAWN_CRUSTACEAN
     * @see WestMapEnemyFactory#getSpawningEnemyGround()
     */
    @Override
    public Enemy getSpawnEnemy(){
        Enemy spawnEnemy = null;
        if(this.getSpawningEnemyGround().hasCapability(Status.SPAWN_SKELETON)){
            spawnEnemy = new HeavySkeletalSwordsman();
        }
        else if(this.getSpawningEnemyGround().hasCapability(Status.SPAWN_CANIS_LUPUS)){
            spawnEnemy = new LoneWolf();
        }
        else if(this.getSpawningEnemyGround().hasCapability(Status.SPAWN_CRUSTACEAN)){
            spawnEnemy = new GiantCrab();
        }
        return spawnEnemy;
    }
}
