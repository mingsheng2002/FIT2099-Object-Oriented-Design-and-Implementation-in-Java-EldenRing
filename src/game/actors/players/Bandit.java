package game.actors.players;

import game.actors.Player;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

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
