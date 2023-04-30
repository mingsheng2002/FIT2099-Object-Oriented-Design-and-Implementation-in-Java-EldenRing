package game.enemyfactories;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;

public abstract class EnemyFactory {

    private Enemy enemyToSpawn;

    public EnemyFactory(Enemy enemyToSpawn) {
        this.enemyToSpawn = enemyToSpawn;
    }

    public void manufactureEnemy(Location location) {
        location.addActor(enemyToSpawn);
    }
}
