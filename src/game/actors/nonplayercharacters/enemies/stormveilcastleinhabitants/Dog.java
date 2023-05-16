package game.actors.nonplayercharacters.enemies.stormveilcastleinhabitants;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.resets.ResetManager;
import game.resets.Resettable;

/**
 * A Stormveil Castle inhabitant enemy called Dog.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see StormveilCastleInhabitant
 * @see Resettable
 */
public class Dog extends StormveilCastleInhabitant implements Resettable {

    /**
     * The spawn chance of Dog from its specific ground.
     */
    private static final int SPAWN_CHANCE = 37;
    /**
     * The chance of Dog despawning from the game map.
     */
    private static final int DESPAWN_CHANCE = 10;
    /**
     * Health of Dog.
     */
    private static final int HIT_POINTS = 104;
    /**
     * Damage points dealt by Dog's attack.
     */
    private static final int DAMAGE = 101;
    /**
     * Attack accuracy of Dog.
     */
    private static final int HIT_RATE = 93;
    /**
     * Minimum amount of runes dropped by Dog if defeated by player.
     */
    private static final int MIN_RUNES_AWARD = 52;
    /**
     * Maximum amount of runes dropped by Dog if defeated by player.
     */
    private static final int MAX_RUNES_AWARD = 1390;

    /**
     * Constructor for Dog.
     * @see ResetManager#getInstance()
     * @see ResetManager#registerResettable(Resettable)
     */
    public Dog() {
        super("Dog", 'a',HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Get the intrinsic weapon for Dog.
     * @return An instance of IntrinsicWeapon.
     * @see IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "bites", HIT_RATE);
    }

    /**
     * When the game is reset, this method will be executed and Dog will be removed from the game map.
     */
    @Override
    public void reset() {
        if (this.getMap() != null){
            getMap().removeActor(this);
        }
    }
}
