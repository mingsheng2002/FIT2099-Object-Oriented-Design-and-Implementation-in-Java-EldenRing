package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.actions.ConsumeAction;
import game.controllers.ResetManager;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class FlaskOfCrimsonTears extends Item implements Resettable {


    private static final int MAX_USAGE = 2 ;
    private int numOfUsage;
    /***
     * Constructor.
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", '.', false);
        this.numOfUsage = 0;
        this.addCapability(Status.HEALING);
        ResetManager.getInstance().registerResettable(this);
    }

    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        // if flask usage is not Max yet add consumeAction
        if (getNumOfUsage() < MAX_USAGE) {
            actions.add(new ConsumeAction());
        }
        return actions;
    }

    public int getNumOfUsage() {
        return numOfUsage;
    }

    @Override
    public void tick(Location currentLocation) {
        this.numOfUsage = numOfUsage + 1;
    }

    public int getNumOfUsageLeft(){
        return MAX_USAGE - getNumOfUsage();
    }

    @Override
    public void reset() {
        this.numOfUsage = 0;
        System.out.println("Flask of Crimson Tears reset");
        System.out.println("Number of usage left: "+ getNumOfUsageLeft());

    }
}
