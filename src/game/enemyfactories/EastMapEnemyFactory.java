package game.enemyfactories;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.enemies.Enemy;
import game.actors.enemies.canislupus.GiantDog;
import game.actors.enemies.crustaceans.GiantCrayfish;
import game.actors.enemies.skeletons.SkeletalBandit;
import game.enums.Status;

public class EastMapEnemyFactory extends EnemyFactory {

    public EastMapEnemyFactory(Ground ground){
        super(ground);
    }

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
        return spawnEnemy;
    }
}
