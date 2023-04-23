package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Player;
import game.controllers.ResetManager;
import game.controllers.RunesManager;
import game.enums.Status;
import game.actors.enemies.Enemy;
import game.actors.enemies.PileOfBones;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        if(target.hasCapability(Status.RESTING)){
            System.out.println("YOU DIED");

            // pass lastlocation in runes
            ((Player) target).getRunes().setDropLocation(((Player) target).getLastLocation());
            ((Player) target).setHasDeath(true);
            // change it to portable
            ((Player) target).getRunes().togglePortability();

            target.removeItemFromInventory(((Player) target).getRunes());

            ResetManager.getInstance().run();

            return result;
        }

        if(this.attacker.hasCapability(Status.RESTING) && target.hasCapability(Status.REVIVABLE)){
            PileOfBones pob = new PileOfBones(target);
            Location here = map.locationOf(target);
            map.removeActor(target);
            here.addActor(pob);
            return result;
        }

        // only drop items if defeated by player
        if (this.attacker.hasCapability(Status.RESTING)) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
        }
        // remove actor
        map.removeActor(target);
        result += System.lineSeparator() + menuDescription(target);

        if(this.attacker.hasCapability(Status.RESTING) && target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            String retVal = RunesManager.getInstance().awardRunes(target, attacker);
            result += System.lineSeparator() + retVal;
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
