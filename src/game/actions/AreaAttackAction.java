package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

/**
 * An attack action that targets all the actors in the surroundings of the attacker.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class AreaAttackAction extends Action {

    /**
     * The weapon used for attack.
     */
    private Weapon weapon;
    /**
     * The attack accuracy of the weapon.
     */
    private int weaponChanceToHit;

    /**
     * Constructor that initialises the weapon used for attack.
     * @param weapon the weapon used for attack.
     */
    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * 	When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * 	the actor will hit the all targets in his surroundings. If so, deal damage to the target and determine whether
     * 	the target is killed. The chance to hit of the weapon is computed independently for every target in the surroundings.
     * @param actor The actor performing the area attack action.
     * @param map The game map the actor is on.
     * @return the result of the area attack, e.g. whether the target is killed, etc.
     * @see DeathAction#execute(Actor, GameMap)
     * @see Status#PROTECTED
     * @see Status#DESPAWNING
     * @see RandomNumberGenerator#getRandomInt(int)
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        weaponChanceToHit = this.weapon.chanceToHit();

        String result = "";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                int damage = this.weapon.damage();
                Actor target = exit.getDestination().getActor();

                // if target is not yet defeated by other enemies AND
                // if target is not despawning from map in this round AND
                // if target is not performing Quickstep action in this round
                if (target.isConscious() && !target.hasCapability(Status.DESPAWNING) && !target.hasCapability(Status.PROTECTED)) {
                    // if actor has area attack capability and meet the chance - perform attack, or
                    // if the special skill weapon has area attack capability and meet the chance - perform attack
                    if (RandomNumberGenerator.getRandomInt(100) < weaponChanceToHit) {
                        target.hurt(damage);
                        result += System.lineSeparator() + actor + " performs area attack and " + this.weapon.verb() + " " + target + " for " + damage + " damage";
                        if (!target.isConscious()) {
                            result += System.lineSeparator() + new DeathAction(actor).execute(target, map);
                        }
                    } else {
                        result += actor + " performs area attack but misses " + target + "\n";
                    }
                }
            }
        }
        return result;
    }

    /**
     * Describes which target in his surroundings the actor is attacking with which weapon.
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Area Attack with " + weapon;
    }
}
