package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.portableweapons.GreatKnife;

public class Bandit extends Archetype {
    public static final int HIT_POINT = 414;
    public static final WeaponItem WEAPON_ITEM = new GreatKnife();

    /**
     * Constructor.
     *
     */
    public Bandit() {
        super(HIT_POINT, WEAPON_ITEM);
    }
}
