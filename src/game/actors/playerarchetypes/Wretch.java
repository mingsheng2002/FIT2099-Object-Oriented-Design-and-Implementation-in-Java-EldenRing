package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.portableweapons.Club;

public class Wretch extends Archetype {
    public static final int HIT_POINT = 414;
    public static final WeaponItem WEAPON_ITEM = new Club();

    /**
     * Constructor.
     *
     */
    public Wretch() {
        super(HIT_POINT, WEAPON_ITEM);
    }
}
