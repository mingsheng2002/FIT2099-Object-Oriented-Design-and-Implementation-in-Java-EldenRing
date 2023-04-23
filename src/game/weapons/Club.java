package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Sellable;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends WeaponItem implements Purchasable, Sellable {

    private static final int DAMAGE = 103;
    private static final int HIT_RATE = 80;
    private static final int PURCHASE_PRICE = 600;
    private static final int SELL_PRICE = 100;

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', DAMAGE, "bonks", HIT_RATE);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public int getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    @Override
    public WeaponItem getInstance() {
        return this;
    }
}
