package game.items.remembrances;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.items.Sellable;
import game.utils.SurroundingChecker;
import game.weapons.portableweapons.AxeOfGodrick;
import game.weapons.portableweapons.GraftedDragon;
import edu.monash.fit2099.engine.items.Item;

/**
 * A specific item of type Remembrance that can be exchanged with specified things from any traders that can accept it.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Remembrance
 * @see Sellable
 */
public class RemembranceOfTheGrafted extends Remembrance implements Sellable {

    /**
     * Sell price of RemembranceOfTheGrafted
     */
    private static final int SELL_PRICE = 20000;
    /**
     * A sell action provided by RemembranceOfTheGrafted to the actor holding this item
     */
    private SellAction sellAction;

    /**
     * Constructor for RemembranceOfTheGrafted
     * @see Remembrance#getWeaponItemsToBeExchanged()
     * @see AxeOfGodrick
     * @see GraftedDragon
     * @see Remembrance#initialiseExchangeActions()
     * @see SellAction
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        this.getWeaponItemsToBeExchanged().add(new AxeOfGodrick());
        this.getWeaponItemsToBeExchanged().add(new GraftedDragon());
        this.initialiseExchangeActions();
        this.sellAction = new SellAction(this);
    }

    /**
     * Returns the sell price of RemembranceOfTheGrafted.
     * @return int that representing the sell price of RemembranceOfTheGrafted.
     */
    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    /**
     * Removes the current RemembranceOfTheGrafted from actor's inventory.
     * @param actor actor that the RemembranceOfTheGrafted will be removed from
     */
    @Override
    public void removeSellableFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    /**
     * Performs an action on each tick of the game loop.
     * Checks if the RemembranceOfTheGrafted is ready to be sold and if there is an actor nearby with the capability to provide a sell service.
     * Adds a sell action to the RemembranceOfTheGrafted if the conditions are met and removes the "READY_TO_BE_SOLD" capability.
     * @param currentLocation the location of the actor carrying RemembranceOfTheGrafted
     * @param actor           the actor carrying RemembranceOfTheGrafted
     * @see Status#READY_TO_BE_SOLD
     * @see Status#PROVIDE_SELL_SERVICE
     * @see SurroundingChecker#surroundingHasActorWithCapability(Location, Status)
     * @see Remembrance#tick(Location, Actor)
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.removeAction(sellAction);
        if (this.hasCapability(Status.READY_TO_BE_SOLD) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_SELL_SERVICE)) {
            this.addAction(sellAction);
            this.removeCapability(Status.READY_TO_BE_SOLD);
        }
        super.tick(currentLocation, actor);
    }

    /**
     * Removes SellAction from RemembranceOfTheGrafted's allowableActions before allowing the actor to drop this item.
     * @param actor actor that performs drop action
     * @return a new DropItemAction if this item is portable, null otherwise.
     * @see Item#removeAction(Action)
     * @see Remembrance#getDropAction(Actor)
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        this.removeAction(sellAction);
        return super.getDropAction(actor);
    }
}
