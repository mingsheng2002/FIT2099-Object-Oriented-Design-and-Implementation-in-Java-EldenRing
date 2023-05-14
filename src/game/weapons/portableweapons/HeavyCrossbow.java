package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.weapons.Purchasable;
import game.weapons.Sellable;
import game.weapons.weapontypes.Crossbow;

/**
 * A weapon that is carried by Godrick Soldier.
 * It deals 64 damage with 57% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Crossbow
 * @see Purchasable
 * @see Sellable
 */
public class HeavyCrossbow extends Crossbow implements Purchasable, Sellable {

    /**
     * Damage caused by HeavyCrossbow
     */
    private static final int DAMAGE = 64;
    /**
     * Attack accuracy of HeavyCrossbow
     */
    private static final int HIT_RATE = 57;
    /**
     * Purchase price of HeavyCrossbow
     */
    private static final int PURCHASE_PRICE = 1500;
    /**
     * Sell price of HeavyCrossbow
     */
    private static final int SELL_PRICE = 100;

    /**
     * Constructor for HeavyCrossbow
     */
    public HeavyCrossbow(){
        super("Heavy Crossbow", '}', DAMAGE, "shoot", HIT_RATE);
        //this.addCapability(Status.AREA_ATTACK);
    }

    /**
     * Returns the purchase price of HeavyCrossbow.
     * @return int that representing the purchase price of HeavyCrossbow.
     */
    @Override
    public int getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    /**
     * Returns an instance of PurchaseAction when HeavyCrossbow is purchased by the player.
     * @param purchasable The weapon that is being purchased.
     * @return an instance of PurchaseAction.
     * @see PurchaseAction
     */
    @Override
    public Action getPurchaseAction(Purchasable purchasable) {
        return new PurchaseAction( purchasable);
    }

    /**
     * Returns current instance of HeavyCrossbow.
     * @return an instance of HeavyCrossbow.
     */
    @Override
    public WeaponItem getPurchasableInstance() {
        return this;
    }

    /**
     * Returns the sell price of HeavyCrossbow.
     * @return int that representing the sell price of HeavyCrossbow.
     */
    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    /**
     * Returns an instance of SellAction when the HeavyCrossbow is sold by the player.
     * @param sellable The weapon that is being sold.
     * @return an instance of SellAction.
     * @see SellAction
     */
    @Override
    public Action getSellAction(Sellable sellable) {
        return new SellAction(sellable);
    }
}