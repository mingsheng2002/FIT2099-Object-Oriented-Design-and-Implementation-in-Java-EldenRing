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
 * A weapon that is carried by the Wretch.
 * It deals 103 damage with 80% attack accuracy.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Hammer
 * @see Purchasable
 * @see Sellable
 */
public class Club extends Hammer implements Purchasable, Sellable {

    /**
     * Damage caused by Club
     */
    private static final int DAMAGE = 103;
    /**
     * Attack accuracy of Club
     */
    private static final int HIT_RATE = 80;
    /**
     * Purchase price of Club
     */
    private static final int PURCHASE_PRICE = 600;
    /**
     * Sell price of Club
     */
    private static final int SELL_PRICE = 100;

    /**
     * Constructor for Club.
     */
    public Club() {
        super("Club", '!', DAMAGE, "bonks", HIT_RATE);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    /**
     * Returns the purchase price of Club.
     * @return int that representing the purchase price of Club.
     */
    @Override
    public int getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    /**
     * Returns an instance of PurchaseAction when Club is purchased by the player.
     * @param purchaser The actor that is purchasing the weapon.
     * @param purchasable The weapon that is being purchased.
     * @return an instance of PurchaseAction.
     * @see PurchaseAction
     */
    @Override
    public Action getPurchaseAction(Actor purchaser, Purchasable purchasable) {
        return new PurchaseAction(purchaser, purchasable);
    }

    /**
     * Returns the sell price of Club.
     * @return int that representing the sell price of Club.
     */
    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    /**
     * Returns an instance of SellAction when the Club is sold by the player.
     * @param customer The actor that wants to sell the weapon.
     * @param sellable The weapon that is being sold.
     * @return an instance of SellAction.
     * @see SellAction
     */
    @Override
    public Action getSellAction(Actor customer, Sellable sellable) {
        return new SellAction(customer, sellable);
    }

    /**
     * Returns an current instance of Club.
     * @return an instance of Club.
     */
    @Override
    public WeaponItem getPurchasableInstance() {
        return this;
    }
}
