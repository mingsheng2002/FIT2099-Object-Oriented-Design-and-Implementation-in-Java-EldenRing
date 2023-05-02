package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class representing the combat archetypes or the starting classes of the Actor.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public abstract class Archetype {

    /**
     * Starting health of specific archetype.
     */
    private int hitPoints;
    /**
     * Starting weapon item of specific archetype.
     */
    private WeaponItem weaponItem;

    /**
     * Constructor for Archetype.
     * @param hitPoints Archetype's starting number of hitpoints (health).
     * @param weaponItem Archetype's starting weapon item.
     */
    public Archetype(int hitPoints, WeaponItem weaponItem) {
        this.hitPoints = hitPoints;
        this.weaponItem = weaponItem;
    }

    /**
     * Getter to get the current hitpoints (health) of the archetype.
     * @return the amount of current hitpoints (health) of the archetype.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Getter to get the weapon item of the archetype.
     * @return the weapon item of the archetype.
     */
    public WeaponItem getWeaponItem() {
        return weaponItem;
    }
}
