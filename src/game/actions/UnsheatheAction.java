package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;

public class  UnsheatheAction extends AttackAction {

    private static final int WEAPON_HIT_CHANCE = 60;
    public UnsheatheAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
    }

    public UnsheatheAction(Actor target, String direction) {
        super(target, direction);
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        if (!(getRand().nextInt(100)<=WEAPON_HIT_CHANCE)){
            return actor + " misses " + getTarget() + ".";
        }


        int damage = (getWeapon().damage())*2;
        String result = actor + " unsheathes  " + getWeapon().verb() + " " + getTarget() + " for " + damage + " damage.";
        getTarget().hurt(damage);
        if (!getTarget().isConscious()) {
            result += new DeathAction(actor).execute(getTarget(), map);
        }

        return result;
    }
    @Override
    public String menuDescription(Actor actor) {

        return actor + " unsheathes Uchigatana on "+getTarget() ;
    }
}
