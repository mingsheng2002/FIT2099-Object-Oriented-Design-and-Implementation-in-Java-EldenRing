package game.enemyfactories;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.nonplayercharacters.enemies.Enemy;
import game.actors.nonplayercharacters.enemies.canislupus.GiantDog;
import game.actors.nonplayercharacters.enemies.crustaceans.GiantCrayfish;
import game.actors.nonplayercharacters.enemies.skeletons.SkeletalBandit;
import game.actors.nonplayercharacters.enemies.stormveilcastleinhabitants.Dog;
import game.actors.nonplayercharacters.enemies.stormveilcastleinhabitants.GodrickSoldier;
import game.enums.Status;

/**
 * A class spawn enemies on East side of the map.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see EnemyFactory
 */
public class EastMapEnemyFactory extends EnemyFactory {

    /**
     * Constructor of EastMapEnemyFactory.
     * @param ground ground that spawn enemy
     */
    public EastMapEnemyFactory(Ground ground){
        super(ground);
    }

    /**
     * This method determine which enemy to spawn on East side of the map based on the ground capability.
     * @return spawnEnemy an enemy that has spawned from the ground
     * @see Enemy
     * @see SkeletalBandit
     * @see GiantDog
     * @see GiantCrayfish
     * @see Dog
     * @see GodrickSoldier
     * @see Status#SPAWN_SKELETON
     * @see Status#SPAWN_CANIS_LUPUS
     * @see Status#SPAWN_CRUSTACEAN
     * @see Status#SPAWN_STORMVEIL_CASTLE_DOG
     * @see Status#SPAWN_STORMVEIL_CASTLE_SOLDIER
     * @see EastMapEnemyFactory#getSpawningEnemyGround()
     */
    @Override
    public Enemy getSpawnEnemy(){
        Enemy spawnEnemy = null;
        if(this.getSpawningEnemyGround().hasCapability(Status.SPAWN_SKELETON)){
            spawnEnemy = new SkeletalBandit();
        }
        else if(this.getSpawningEnemyGround().hasCapability(Status.SPAWN_CANIS_LUPUS)){
            spawnEnemy = new GiantDog();
        }
        else if(this.getSpawningEnemyGround().hasCapability(Status.SPAWN_CRUSTACEAN)){
            spawnEnemy = new GiantCrayfish();
        }
        else if (this.getSpawningEnemyGround().hasCapability(Status.SPAWN_STORMVEIL_CASTLE_DOG)){
            spawnEnemy =  new Dog();
        }
        else if (this.getSpawningEnemyGround().hasCapability(Status.SPAWN_STORMVEIL_CASTLE_SOLDIER)){
            spawnEnemy = new GodrickSoldier();
        }
        return spawnEnemy;
    }
}
