package game.actors.playerclasses;

import game.weapons.Uchigatana;

public class Samurai extends Player {

    public static final int HIT_POINT = 455;
    /**
     * Constructor.
     *
     */
    public Samurai() {
        super("Samurai", '@', HIT_POINT);
        this.addWeaponToInventory(new Uchigatana());
    }
}
