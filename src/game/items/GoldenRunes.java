package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.ConsumeAction;
import game.controllers.RunesManager;
import game.enums.Status;
import game.utils.RandomNumberGenerator;


public class GoldenRunes extends Item implements Consumable {

    private static final int MIN_RUNES_AWARD = 200;
    private static final int MAX_RUNES_AWARD = 10000;
    ConsumeAction consumeAction;

    /***
     * Constructor.
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true);
        consumeAction = new ConsumeAction(this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            this.addAction(consumeAction);
        }
    }

    @Override
    public DropAction getDropAction(Actor actor) {
        this.removeAction(consumeAction);
        return super.getDropAction(actor);
    }

    @Override
    public void consumedBy(Actor actor) {
        int amount = RandomNumberGenerator.getRandomInt(MIN_RUNES_AWARD, MAX_RUNES_AWARD);
        RunesManager.getInstance().incrementPlayerRunes(amount);
        actor.removeItemFromInventory(this);
    }
}