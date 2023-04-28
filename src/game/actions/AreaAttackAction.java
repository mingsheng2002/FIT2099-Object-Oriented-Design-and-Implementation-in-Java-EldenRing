package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.enemies.crustaceansenemies.GiantCrab;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

import java.awt.geom.Area;

public class AreaAttackAction extends Action {

    private Weapon weapon;
    private int weaponChanceToHit;
    private static final int ACTOR_CHANCE_TO_HIT = GiantCrab.HIT_RATE;
    private boolean hasAreaAttackCapableWeapon;
    private boolean hasAreaAttackCapability;


    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // check the factor of performing area attack action
        if (actor.hasCapability(Status.AREA_ATTACK)) {
            hasAreaAttackCapability = true;
        }
        else {
            hasAreaAttackCapableWeapon = true;
            weaponChanceToHit = this.weapon.chanceToHit();
        }

        String result = "";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                int damage = this.weapon.damage();
                Actor target = exit.getDestination().getActor();

                // if target is not yet defeated by other enemies AND
                // if target is not despawning from map in this round
                if(target.isConscious() && !target.hasCapability(Status.DESPAWNING)) {
                    // if actor has area attack capability and meet the chance - perform attack, or
                    // if the special skill weapon has area attack capability and meet the chance - perform attack
                    if((hasAreaAttackCapability && RandomNumberGenerator.getRandomInt(100) < ACTOR_CHANCE_TO_HIT)
                           || hasAreaAttackCapableWeapon && RandomNumberGenerator.getRandomInt(100) < weaponChanceToHit) {
                        target.hurt(damage);
                        result += System.lineSeparator() + actor + " performs area attack and " + this.weapon.verb() + " " + target + " for " + damage + " damage";
                        if (!target.isConscious()) {
                            result += new DeathAction(actor).execute(target, map);
                        }
                    }else{
                        result += actor + "performs area attack but misses " + target;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Area Attack.";
    }
}
