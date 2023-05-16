package game.actors.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.portableweapons.GreatKnife;

/**
 * A class representing the Bandit combat archetype of the Actor.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Archetype
 */
public class Bandit extends Archetype {

    /**
     * Starting health of Bandit archetype.
     */
    public static final int HIT_POINT = 414;
    /**
     * Starting weapon item of Bandit archetype.
     */
    public static final WeaponItem WEAPON_ITEM = new GreatKnife();

    /**
     * Constructor for Bandit archetype.
     */
    public Bandit() {
        super(HIT_POINT, WEAPON_ITEM);
    }
}
