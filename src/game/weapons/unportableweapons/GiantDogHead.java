package game.weapons.unportableweapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

/**
 * An intrinsic  weapon of GiantDog.
 * It deals 314 damage with 90% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see WeaponItem
 */
public class GiantDogHead extends WeaponItem {

    /**
     * Damage caused by GiantDogHead
     */
    private static final int DAMAGE = 314;
    /**
     * Attack Accuracy of GiantDogHead
     */
    private static final int HIT_RATE = 90;

    /**
     * Constructor for GiantDogHead
     * @see Status#AREA_ATTACK
     */
    public GiantDogHead() {
        super("Giant Dog Head", 'g', DAMAGE, "slams", HIT_RATE);
        this.togglePortability();
        this.addCapability(Status.AREA_ATTACK);
    }
}
