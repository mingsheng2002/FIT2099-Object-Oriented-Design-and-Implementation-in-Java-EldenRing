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
import game.actors.nonplayercharacters.enemies.harmlessenemies.PileOfBones;
import game.items.Runes;
import game.resets.ResetManager;
import game.utils.FancyMessage;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class DeathAction extends Action {

    /**
     * The attacker who causes the actor to die.
     */
    private Actor attacker;

    /**
     * Constructor to initialises the attacker.
     * @param actor the attacker causing the actor to die.
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * If the target being killed is the player, reset the game.
     * If the target being killed has the capability of REVIVABLE, they will turn into Pile Of Bones.
     * If the target being killed is one of the Runes Rewarders and is defeated by player, then the player will be
     * rewarded with certain runes.
     * When the target is killed by the player, the items & weapons carried by target will be dropped to the location
     * in the game map where the target was.
     *
     * @param target The actor performing the action.
     * @param map The game map the actor is on.
     * @return result of the action to be displayed on the UI
     * @see ResetAction#execute(Actor, GameMap)
     * @see FancyMessage#YOU_DIED
     * @see PileOfBones
     * @see RunesManager#getInstance()
     * @see RunesManager#getMinMaxRunesOf(Actor)
     * @see RunesManager#rewardRunes(Actor, Actor)
     * @see Status#HOSTILE_TO_ENEMY
     * @see Status#REVIVABLE
     * @see ResetManager#getInstance()
     * @see ResetManager#isGameResetting()
     * @see ActionList
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        // if player dies, reset whole game
        if(target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            System.out.println("\n\n\n" + FancyMessage.YOU_DIED + "\n\n\n");
            return new ResetAction().execute(target, map);
        }

        // if the dying enemy is revivable AND current game round is not being reset, turns into pile of bones
        if (target.hasCapability(Status.REVIVABLE) && !ResetManager.getInstance().isGameResetting()) {
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

    /**
     * Describes which actor is killed.
     * @param actor The actor who is being killed.
     * @return a description used for display in the menu UI to show the message.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed";
    }
}
