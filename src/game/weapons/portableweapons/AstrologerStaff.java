package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.items.Purchasable;
import game.items.Sellable;
import game.utils.SurroundingChecker;
import game.weapons.weapontypes.Staff;

/**
 * A weapon that is carried by Astrologer.
 * It deals 274 damage with 50% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Staff
 * @see Purchasable
 * @see Sellable
 */
public class AstrologerStaff extends Staff implements Purchasable, Sellable {

    /**
     * Damage caused by Astrologer's Staff
     */
    private static final int DAMAGE = 274;
    /**
     * Attack accuracy of Astrologer's Staff
     */
    private static final int HIT_RATE = 50;
    /**
     * Purchase price of Astrologer's Staff
     */
    private static final int PURCHASE_PRICE = 800;
    /**
     * Sell price of Astrologer's Staff
     */
    private static final int SELL_PRICE = 100;
    /**
     * Sell action provided by Astrologer's Staff
     */
    private SellAction sellAction;

    /**
     * Constructor for Astrologer's Staff.
     * @see SellAction
     */
    public AstrologerStaff() {
        super("Astrologer's Staff", 'f', DAMAGE, "shoot", HIT_RATE);
        this.sellAction = new SellAction(this);
    }

    /**
     * Returns the purchase price of Astrologer's Staff.
     * @return int that representing the purchase price of Astrologer's Staff.
     */
    @Override
    public int getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    /**
     * Returns an instance of PurchaseAction when Astrologer's Staff is purchased by the player.
     * @param purchasable The weapon that is being purchased.
     * @return an instance of PurchaseAction.
     * @see PurchaseAction
     */
    @Override
    public Action getPurchaseAction(Purchasable purchasable) {
        return new PurchaseAction(purchasable);
    }

    /**
     * Returns the sell price of Astrologer's Staff.
     * @return int that representing the sell price of Astrologer's Staff.
     */
    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    /**
     * TBC
     * @param actor
     */
    @Override
    public void removeSellableFromInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    /**
     * TBC
     * @param actor
     */
    @Override
    public void addPurchasableToInventory(Actor actor) {
        actor.addWeaponToInventory(this);

    }

    /**
     *TBC
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.removeAction(sellAction);
        if (this.hasCapability(Status.READY_TO_BE_SOLD) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_SELL_SERVICE)) {
            this.addAction(sellAction);
            this.removeCapability(Status.READY_TO_BE_SOLD);
        }
    }

    @Override
    public DropAction getDropAction(Actor actor) {
        this.removeAction(sellAction);
        return super.getDropAction(actor);
    }
}
