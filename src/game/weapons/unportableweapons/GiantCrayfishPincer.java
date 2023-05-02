package game.weapons.unportableweapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

/**
 * An intrinsic  weapon of GiantCrayFish.
 * It deals 527 damage with 100% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see WeaponItem
 */
public class GiantCrayfishPincer extends WeaponItem {

    /**
     * Damage caused by GiantCrayfishPincer
     */
    private static final int DAMAGE = 527;
    /**
     * Attack accuracy of GiantCrayfishPincer
     */
    private static final int HIT_RATE = 100;

    /**
     * Constructor for GiantCrayfishPincer.
     * @see Status#AREA_ATTACK
     */
    public GiantCrayfishPincer() {
        super("Giant Crayfish Pincer", 'r', DAMAGE, "slams", HIT_RATE);
        this.togglePortability();
        this.addCapability(Status.AREA_ATTACK);
    }
}
