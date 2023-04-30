package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.ResetAction;
import game.controllers.RunesManager;
import game.enums.Status;
import game.actors.enemies.harmlessenemies.PileOfBones;
import game.utils.FancyMessage;

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

        // if player dies, reset whole game
        if(target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            System.out.println(FancyMessage.YOU_DIED);
            return new ResetAction().execute(target, map);
        }

        // if the dying enemy is revivable, turns into pile of bones
        if (target.hasCapability(Status.REVIVABLE)) {
            int[] targetMinMaxRewardRunes = RunesManager.getInstance().getMinMaxRunesOf(target);
            PileOfBones pileOfBones = new PileOfBones(target, targetMinMaxRewardRunes[0], targetMinMaxRewardRunes[1]);
            Location here = map.locationOf(target);
            map.removeActor(target);
            here.addActor(pileOfBones);
            return System.lineSeparator() + target + " is killed and turns into " + pileOfBones;
        }

        // if dying enemy is not revivable AND enemy is defeated by player, reward player with runes
        if (this.attacker.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            String retVal = RunesManager.getInstance().rewardRunes(target, attacker);
            result += System.lineSeparator() + retVal;
        }

        // only drop items if defeated by player
        if (this.attacker.hasCapability(Status.HOSTILE_TO_ENEMY)) {
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
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed";
    }
}
