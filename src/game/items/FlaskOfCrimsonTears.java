package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.resets.Resettable;
import game.actions.playeractions.ConsumeAction;
import game.resets.ResetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * An item that can be consumed by the player to restore their health by 250 points.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Item
 * @see Consumable
 * @see Resettable
 */
public class FlaskOfCrimsonTears extends Item implements Consumable, Resettable {

    /**
     * Maximum usage of FlaskOfCrimsonTears
     */
    private static final int MAX_USAGE = 2 ;
    /**
     * Number of uses of FlaskOfCrimsonTears
     */
    private int numOfUsage;

    /***
     * Constructor for FlaskOfCrimsonTears.
     * @see ResetManager#getInstance()
     * @see ResetManager#registerResettable(Resettable)
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", '.', false);
        this.numOfUsage = 0;
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Returns a list of allowable actions that the player can perform on FlaskOfCrimsonTears.
     * @return a list of allowable actions
     * @see ConsumeAction
     * @see Action
     */
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        // if flask usage is not Max yet add consumeAction
        if (getNumOfUsage() < MAX_USAGE) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * Returns the number of usage of FlaskOfCrimsonTears.
     * @return int that representing the number of usage.
     */
    public int getNumOfUsage() {
        return numOfUsage;
    }

    /**
     * Returns the number of usage left of FlaskOfCrimsonTears.
     * @return int that representing the number of usage left.
     */
    public int getNumOfUsageLeft(){
        return MAX_USAGE - getNumOfUsage();
    }

    /**
     *  A reset method that reset number of uses of FlaskOfCrimsonTears to zero.
     */
    @Override
    public void reset() {
        this.numOfUsage = 0;
        System.out.println("Number of uses the " + this + " has left: " + getNumOfUsageLeft());
    }

    /**
     * This method increase the actor's hit point by 250 when the actor consume FlaskOfCrimsonTears
     * and increase the number of usage of FlaskOfCrimsonTears by 1 .
     * @param actor The actor that consume FlaskOfCrimsonTears.
     * @see Actor#heal(int)
     */
    @Override
    public void consumedBy(Actor actor) {
        // increase player's hitpoints by 250
        actor.heal(250);
        this.numOfUsage = numOfUsage + 1;
    }
}
