package game.enemyfactories;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.utils.RandomNumberGenerator;

/**
 * An abstract Enemy Factory class.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public abstract class EnemyFactory {

    /**
     * instance of Ground object to know what enemy should be spawn
     */
    private Ground spawningEnemyGround;

    /**
     * Constructor of EnemyFactory
     * @param ground ground that spawn enemy
     */
    public EnemyFactory(Ground ground){
        spawningEnemyGround = ground;
    }

    /**
     * This method check whether the location contains an actor and
     * spawn the enemy if generated random number is smaller than their spawn chance.
     * @param location location to add the spawn enemy
     * @see Location#containsAnActor()
     * @see Location#addActor(Actor)
     * @see Enemy#getSpawnChance()
     * @see EnemyFactory#getSpawnEnemy()
     * @see RandomNumberGenerator#getRandomInt(int)
     */
    public void manufactureEnemy(Location location) {
        if (!location.containsAnActor()){
            Enemy spawnEnemy = getSpawnEnemy();
            if (RandomNumberGenerator.getRandomInt(100) < spawnEnemy.getSpawnChance()){
                location.addActor(spawnEnemy);
            }
        }
    }

    /**
     * Returns the spawning ground.
     * @return spawningEnemyGround a ground that spawn enemy
     */
    public Ground getSpawningEnemyGround() {
        return spawningEnemyGround;
    }

    /**
     * An abstract method that get the spawning enemy
     * @return Enemy
     */
    public abstract Enemy getSpawnEnemy();
}
