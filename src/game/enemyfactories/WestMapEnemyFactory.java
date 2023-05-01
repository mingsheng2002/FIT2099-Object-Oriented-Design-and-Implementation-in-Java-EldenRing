package game.enemyfactories;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.enemies.Enemy;
import game.actors.enemies.canislupus.LoneWolf;
import game.actors.enemies.crustaceans.GiantCrab;
import game.actors.enemies.skeletons.HeavySkeletalSwordsman;
import game.enums.Status;

public class WestMapEnemyFactory extends EnemyFactory {

    public WestMapEnemyFactory(Ground ground) {
        super(ground);
    }

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
