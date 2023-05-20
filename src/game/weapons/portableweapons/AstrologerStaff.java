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
     * Removes the current Astrologer's Staff from actor's inventory.
     * @param actor actor that the Astrologer's Staff will be removed from
     */
    @Override
    public void removeSellableFromInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    /**
     * Adds the Astrologer's Staff to the actor's inventory.
     * @param actor actor that the Atrologer's Staff will be added to
     */
    @Override
    public void addPurchasableToInventory(Actor actor) {
        actor.addWeaponToInventory(this);

    }

    /**
     * Performs an action on each tick of the game loop.
     * Checks if the Astrologer's Staff is ready to be sold and if there is an actor nearby with the capability to provide a sell service.
     * Adds a sell action to the Astrologer's Staff if the conditions are met and removes the "READY_TO_BE_SOLD" capability.
     *
     * @param currentLocation the location of the actor carrying this item
     * @param actor           the actor carrying this item
     * @see Status#READY_TO_BE_SOLD
     * @see Status#PROVIDE_SELL_SERVICE
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
