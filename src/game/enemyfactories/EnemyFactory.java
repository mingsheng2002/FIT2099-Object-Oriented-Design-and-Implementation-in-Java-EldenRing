package game.enemyfactories;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.utils.RandomNumberGenerator;

public abstract class EnemyFactory {

    private Ground spawningEnemyGround;

    public EnemyFactory(Ground ground){
        spawningEnemyGround = ground;
    }

    public void manufactureEnemy(Location location) {
        if (!location.containsAnActor()){
            Enemy spawnEnemy = getSpawnEnemy();
            if (RandomNumberGenerator.getRandomInt(100) < spawnEnemy.getSpawnChance()){
                location.addActor(spawnEnemy);
            }
        }
    }

    public Ground getSpawningEnemyGround() {
        return spawningEnemyGround;
    }

    public abstract Enemy getSpawnEnemy();
}
