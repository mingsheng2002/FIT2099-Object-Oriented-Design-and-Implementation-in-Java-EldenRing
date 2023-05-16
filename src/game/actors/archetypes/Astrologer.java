package game.actors.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.portableweapons.AstrologerStaff;

/**
 * A class representing the Astrologer combat archetype of the Actor.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Archetype
 */
public class Astrologer extends Archetype {

    /**
     * Starting health of Astrologer archetype.
     */
    private static final int HIT_POINT = 396;
    /**
     * Starting weapon item of Astrologer archetype.
     */
    private static final WeaponItem WEAPON_ITEM =  new AstrologerStaff();

    /**
     * Constructor for Astrologer Archetype.
     */
    public Astrologer() {
        super(HIT_POINT, WEAPON_ITEM);
    }
}
