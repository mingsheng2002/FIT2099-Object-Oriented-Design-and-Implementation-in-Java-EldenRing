package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.portableweapons.Uchigatana;

/**
 * A class representing the Samurai combat archetype of the Actor.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Archetype
 */
public class Samurai extends Archetype {

    /**
     * Starting health of Samurai archetype.
     */
    public static final int HIT_POINT = 455;
    /**
     * Starting weapon item of Samurai archetype.
     */
    public static final WeaponItem WEAPON_ITEM = new Uchigatana();

    /**
     * Constructor for Samurai archetype.
     */
    public Samurai() {
        super(HIT_POINT, WEAPON_ITEM);
    }
}
