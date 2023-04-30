package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.portableweapons.Uchigatana;

public class Samurai extends Archetype {

    public static final int HIT_POINT = 455;
    public static final WeaponItem WEAPON_ITEM = new Uchigatana();

    /**
     * Constructor.
     *
     */
    public Samurai() {
        super(HIT_POINT, WEAPON_ITEM);
    }
}
