package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public abstract class Archetype {

    private String name;
    private char displayChar;
    private int hitPoints;
    private WeaponItem weaponItem;

    /**
     * Constructor.
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Archetype(int hitPoints, WeaponItem weaponItem) {
        this.hitPoints = hitPoints;
        this.weaponItem = weaponItem;
    }

    public String getName() {
        return name;
    }

    public char getDisplayChar() {
        return displayChar;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public WeaponItem getWeaponItem() {
        return weaponItem;
    }
}
