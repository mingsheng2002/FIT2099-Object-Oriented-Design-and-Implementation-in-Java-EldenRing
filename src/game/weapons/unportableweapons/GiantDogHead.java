package game.weapons.unportableweapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

public class GiantDogHead extends WeaponItem {

    private static final int DAMAGE = 314;
    private static final int HIT_RATE = 90;

    /**
     * Constructor.
     */
    public GiantDogHead() {
        super("Giant Dog Head", 'g', DAMAGE, "slams", HIT_RATE);
        this.togglePortability();
        this.addCapability(Status.AREA_ATTACK);
    }
}
