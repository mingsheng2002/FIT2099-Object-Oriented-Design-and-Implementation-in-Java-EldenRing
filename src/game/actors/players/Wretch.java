package game.actors.players;

import game.actors.Player;
import game.weapons.Club;

public class Wretch extends Player {
    public static final int HIT_POINT = 414;
    /**
     * Constructor.
     *
     */
    public Wretch() {
        super("Wretch", '@', HIT_POINT);
        this.addWeaponToInventory(new Club());
    }
}
