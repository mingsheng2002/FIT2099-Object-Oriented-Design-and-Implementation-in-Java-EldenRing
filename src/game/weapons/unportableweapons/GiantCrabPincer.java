package game.weapons.unportableweapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

public class GiantCrabPincer extends WeaponItem {

    private static final int DAMAGE = 208;
    private static final int HIT_RATE = 90;
    /**
     * Constructor.
     */
    public GiantCrabPincer() {
        super("Giant Crab Pincer", 'c', DAMAGE, "slams", HIT_RATE);
        this.togglePortability();
        this.addCapability(Status.AREA_ATTACK);
    }
}
