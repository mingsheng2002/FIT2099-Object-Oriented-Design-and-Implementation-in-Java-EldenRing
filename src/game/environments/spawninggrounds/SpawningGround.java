package game.environments.spawninggrounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.enemyfactories.EastMapEnemyFactory;
import game.enemyfactories.EnemyFactory;
import game.enemyfactories.WestMapEnemyFactory;
import game.utils.RandomNumberGenerator;

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

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        int width = location.map().getXRange().max();
        int currLocationX = location.x();

        // if current location does not contain an actor
        if (!location.containsAnActor()) {
            if (currLocationX <= width / 2) {
                Enemy westEnemy = getWestEnemy();
                enemyFactory = new WestMapEnemyFactory(westEnemy);
                if (RandomNumberGenerator.getRandomInt(100) < westEnemy.getSpawnChance()) {
                    spawnEnemy(location);
                }
            } else {
                Enemy eastEnemy = getEastEnemy();
                enemyFactory = new EastMapEnemyFactory(eastEnemy);
                if (RandomNumberGenerator.getRandomInt(100) < eastEnemy.getSpawnChance()) {
                    spawnEnemy(location);
                }
            }
        }
    }

    public void spawnEnemy(Location location) {
        enemyFactory.manufactureEnemy(location);
    };

    public abstract Enemy getEastEnemy();

    public abstract Enemy getWestEnemy();

}
