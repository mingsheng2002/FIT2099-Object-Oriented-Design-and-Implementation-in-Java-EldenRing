package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.weapons.Purchasable;
import game.weapons.Sellable;
import game.weapons.weapontypes.Hammer;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends Hammer implements Purchasable, Sellable {

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
    public Action getPurchaseAction(Actor purchaser, Purchasable purchasable) {
        return new PurchaseAction(purchaser, purchasable);
    }

    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    @Override
    public Action getSellAction(Actor customer, Sellable sellable) {
        return new SellAction(customer, sellable);
    }

    @Override
    public WeaponItem getPurchasableInstance() {
        return this;
    }
}
