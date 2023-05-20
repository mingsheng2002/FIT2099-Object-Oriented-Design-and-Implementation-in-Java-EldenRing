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
     * Sell action provided by HeavyCrossbow
     */
    private SellAction sellAction;

    /**
     * Constructor for HeavyCrossbow。
     * @see SellAction
     */
    public HeavyCrossbow(){
        super("Heavy Crossbow", '}', DAMAGE, "shoot", HIT_RATE);
        this.sellAction = new SellAction(this);
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
     * Returns the sell price of HeavyCrossbow.
     * @return int that representing the sell price of HeavyCrossbow.
     */
    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    /**
     * Removes the current HeavyCrossbow from actor's inventory.
     * @param actor actor that the HeavyCrossbow will be removed from
     */
    @Override
    public void removeSellableFromInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    /**
     * Adds the Heavy Crossbow to the actor's inventory.
     * @param actor actor that the Heavy Crossbow will be added to
     */
    @Override
    public void addPurchasableToInventory(Actor actor) {
        actor.addWeaponToInventory(this);
    }

    /**
     * Performs an action on each tick of the game loop.
     * Checks if the HeavyCrossbow is ready to be sold and if there is an actor nearby with the capability to provide a sell service.
     * Adds a sell action to the HeavyCrossbow if the conditions are met and removes the "READY_TO_BE_SOLD" capability.
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