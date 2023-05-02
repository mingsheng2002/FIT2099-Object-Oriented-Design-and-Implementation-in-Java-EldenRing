package game.environments.spawninggrounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemyfactories.EastMapEnemyFactory;
import game.enemyfactories.EnemyFactory;
import game.enemyfactories.WestMapEnemyFactory;

/**
 * An abstract class that is extended by the grounds that can spawn enemies.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public abstract class SpawningGround extends Ground {

    /**
     * An instance of EnemyFactory to spawn enemy.
     */
    private EnemyFactory enemyFactory;

    /**
     * Constructor for SpawningGround.
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Check the position of current location and initialize enemyFactory with WestMapEnemyFactory
     * or EastMapEnemyFactory.
     * @param location The current location of the Ground
     * @see WestMapEnemyFactory
     * @see EastMapEnemyFactory
     */
    public void tick(Location location) {
        int width = location.map().getXRange().max();
        int currLocationX = location.x();

        // check current location is at East or West
        if (currLocationX <= width / 2) {
            enemyFactory = new WestMapEnemyFactory(this);
        } else {
            enemyFactory = new EastMapEnemyFactory(this);
        }
        enemyFactory.manufactureEnemy(location);
    }
}
