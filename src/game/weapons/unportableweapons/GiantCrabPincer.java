package game.weapons.unportableweapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

/**
 * An intrinsic  weapon of GiantCrab.
 * It deals 208 damage with 90% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see WeaponItem
 */
public class GiantCrabPincer extends WeaponItem {

    /**
     * Damage caused by GiantCrabPincer
     */
    private static final int DAMAGE = 208;
    /**
     *  Attack accuracy of GiantCrabPincer
     */
    private static final int HIT_RATE = 90;

    /**
     * Constructor for GiantCrabPincer.
     * @see Status#AREA_ATTACK
     */
    public GiantCrabPincer() {
        super("Giant Crab Pincer", 'c', DAMAGE, "slams", HIT_RATE);
        this.togglePortability();
        this.addCapability(Status.AREA_ATTACK);
    }
}
