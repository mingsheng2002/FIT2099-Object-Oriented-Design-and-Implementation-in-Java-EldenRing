package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.utils.SurroundingChecker;
import game.items.Sellable;

public class GraftedDragon extends WeaponItem implements Sellable {

    /**
     * Damage caused by Grafted Dragon
     */
    private static final int DAMAGE = 89;
    /**
     * Attack accuracy of Grafted Dragon
     */
    private static final int HIT_RATE = 90;
    /**
     * Sell price of Grafted Dragon
     */
    private static final int SELL_PRICE = 200;
    /**
     * Sell action provided by Grafted Dragon
     */
    private SellAction sellAction;

    public GraftedDragon() {
        super("Grafted Dragon", 'N', DAMAGE, "hits", HIT_RATE);
        this.sellAction = new SellAction(this);
    }

    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    @Override
    public void removeSellableFromInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    /**
     *
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
