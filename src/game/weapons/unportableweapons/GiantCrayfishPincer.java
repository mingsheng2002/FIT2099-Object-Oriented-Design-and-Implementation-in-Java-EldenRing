package game.weapons.unportableweapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

public class GiantCrayfishPincer extends WeaponItem {

    private static final int DAMAGE = 527;
    private static final int HIT_RATE = 100;
    /**
     * Constructor.
     */
    public GiantCrayfishPincer() {
        super("Giant Crayfish Pincer", 'r', DAMAGE, "slams", HIT_RATE);
        this.togglePortability();
        this.addCapability(Status.AREA_ATTACK);
    }
}
