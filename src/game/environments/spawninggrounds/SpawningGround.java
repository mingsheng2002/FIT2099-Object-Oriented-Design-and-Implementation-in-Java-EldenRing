package game.environments.spawninggrounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemyfactories.EastMapEnemyFactory;
import game.enemyfactories.EnemyFactory;
import game.enemyfactories.WestMapEnemyFactory;

public abstract class SpawningGround extends Ground {

    private EnemyFactory enemyFactory;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

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
