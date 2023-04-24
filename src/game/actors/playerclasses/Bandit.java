package game.actors.playerclasses;

import game.weapons.GreatKnife;

public class Bandit extends Player {
    public static final int HIT_POINT = 414;
    /**
     * Constructor.
     *
     */
    public Bandit() {
        super("Bandit", '@', HIT_POINT);
        this.addWeaponToInventory(new GreatKnife());
    }
}
