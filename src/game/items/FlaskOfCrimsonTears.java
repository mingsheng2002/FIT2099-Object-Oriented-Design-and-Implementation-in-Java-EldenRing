package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.resets.Resettable;
import game.actions.playeractions.ConsumeAction;
import game.resets.ResetManager;

import java.util.ArrayList;
import java.util.List;

public class FlaskOfCrimsonTears extends Item implements Consumable, Resettable {

    private static final int MAX_USAGE = 2 ;
    private int numOfUsage;

    /***
     * Constructor.
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", '.', false);
        this.numOfUsage = 0;
        ResetManager.getInstance().registerResettable(this);
    }

    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        // if flask usage is not Max yet add consumeAction
        if (getNumOfUsage() < MAX_USAGE) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    public int getNumOfUsage() {
        return numOfUsage;
    }

    public int getNumOfUsageLeft(){
        return MAX_USAGE - getNumOfUsage();
    }

    @Override
    public void reset() {
        this.numOfUsage = 0;
        System.out.println("Number of uses the " + this + " has left: " + getNumOfUsageLeft());
    }

    @Override
    public void consumedBy(Actor actor) {
        // increase player's hitpoints by 250
        actor.heal(250);
        this.numOfUsage = numOfUsage + 1;
    }
}
