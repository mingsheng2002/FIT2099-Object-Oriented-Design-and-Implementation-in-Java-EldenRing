package game.actors.nonplayercharacters.enemies.canislupus;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.resets.Resettable;
import game.resets.ResetManager;

/**
 * A Canis Lupus type of enemy called Lone Wolf.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see CanisLupus
 * @see Resettable
 */
public class LoneWolf extends CanisLupus implements Resettable {

    /**
     * The spawn chance of Lone Wolf from its specific ground.
     */
    private static final int SPAWN_CHANCE = 33;
    /**
     * The chance of Lone Wolf despawning from the game map.
     */
    private static final int DESPAWN_CHANCE = 10;
    /**
     * Health of Lone Wolf.
     */
    private static final int HIT_POINTS = 102;
    /**
     * Damage points dealt by Lone Wolf's attack.
     */
    private static final int DAMAGE = 97;
    /**
     * Attack accuracy of Lone Wolf.
     */
    private static final int HIT_RATE = 95;
    /**
     * Minimum amount of runes dropped by Lone Wolf if defeated by player.
     */
    private static final int MIN_RUNES_AWARD = 55;
    /**
     * Maximum amount of runes dropped by Lone Wolf if defeated by player.
     */
    private static final int MAX_RUNES_AWARD = 1470;

    /**
     * Constructor for Lone Wolf.
     * @see ResetManager#getInstance()
     * @see ResetManager#registerResettable(Resettable)
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Get the intrinsic weapon for Lone Wolf.
     * @return An instance of IntrinsicWeapon.
     * @see IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "bites", HIT_RATE);
    }

    /**
     * When the game is reset, this method will be executed and Lone Wolf will be removed from the game map.
     */
    @Override
    public void reset() {
        if (this.getMap() != null ){
            getMap().removeActor(this);
        }
    }
}
