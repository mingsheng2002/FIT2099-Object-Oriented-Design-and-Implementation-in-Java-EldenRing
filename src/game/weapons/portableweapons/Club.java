package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.utils.SurroundingChecker;
import game.items.Purchasable;
import game.items.Sellable;
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
     * Sell action provided by Club
     */
    private SellAction sellAction;

    /**
     * Constructor for Club.
     * @see SellAction
     */
    public Club() {
        super("Club", '!', DAMAGE, "bonks", HIT_RATE);
        this.sellAction = new SellAction(this);
    }

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
     * @param purchasable The weapon that is being purchased.
     * @return an instance of PurchaseAction.
     * @see PurchaseAction
     */
    @Override
    public Action getPurchaseAction(Purchasable purchasable) {
        return new PurchaseAction(purchasable);
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
     * Removes the current Club from actor's inventory.
     * @param actor actor that the Club will be removed from
     */
    @Override
    public void removeSellableFromInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    /**
     * Adds the Club to the actor's inventory.
     * @param actor actor that the Club will be added to
     */
    @Override
    public void addPurchasableToInventory(Actor actor) {
        actor.addWeaponToInventory(this);
    }

    /**
     * Performs an action on each tick of the game loop.
     * Checks if the Club is ready to be sold and if there is an actor nearby with the capability to provide a sell service.
     * Adds a sell action to the Club if the conditions are met and removes the "READY_TO_BE_SOLD" capability.
     * @param currentLocation the location of the actor carrying this item
     * @param actor           the actor carrying this item
     * @see Status#READY_TO_BE_SOLD
     * @see Status#PROVIDE_SELL_SERVICE
     * @see SurroundingChecker#surroundingHasActorWithCapability(Location, Status)
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.removeAction(sellAction);
        if (this.hasCapability(Status.READY_TO_BE_SOLD) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_SELL_SERVICE)) {
            this.addAction(sellAction);
            this.removeCapability(Status.READY_TO_BE_SOLD);
        }
    }

    /**
     * Removes SellAction from weapon allowableActions.Create and return an action to drop this WeaponItem.
     * If this WeaponItem is not portable, returns null.
     * @param actor actor that performs drop action
     * @return a new DropWeaponAction if this WeaponItem is portable, null otherwise.
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        this.removeAction(sellAction);
        return super.getDropAction(actor);
    }
}
