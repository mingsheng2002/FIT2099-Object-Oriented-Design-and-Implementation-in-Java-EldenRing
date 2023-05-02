package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.portableweapons.Club;

/**
 * A class representing the Wretch combat archetype of the Actor.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Archetype
 */
public class Wretch extends Archetype {
    /**
     * Starting health of Wretch archetype.
     */
    public static final int HIT_POINT = 414;
    /**
     * Starting weapon item of Wretch archetype.
     */
    public static final WeaponItem WEAPON_ITEM = new Club();

    /**
     * Constructor for Wretch archetype.
     */
    public Wretch() {
        super(HIT_POINT, WEAPON_ITEM);
    }
}
