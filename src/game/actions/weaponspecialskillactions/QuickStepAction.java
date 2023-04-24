package game.actions.weaponspecialskillactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.utils.RandomNumberGenerator;

import java.util.Random;

public class QuickStepAction extends AttackAction {

    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
    }

    public QuickStepAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        boolean hasMove = false;

        if (!(getRand().nextInt(100) <= getWeapon().chanceToHit())) {
            return actor + " misses " + getTarget() + ".";
        }

        int damage = getWeapon().damage();
        String result = actor + " " + getWeapon().verb() + " " + getTarget() + " for " + damage + " damage.";
        getTarget().hurt(damage);
        if (!getTarget().isConscious()) {
            result += new DeathAction(actor).execute(getTarget(), map);
        }

        // perform normal attack
        //super.execute(actor,map);
        //player move away
        while (!hasMove){
            int x = RandomNumberGenerator.getRandomInt(map.getXRange().min(),map.getXRange().max());
            int y = RandomNumberGenerator.getRandomInt(map.getYRange().min(),map.getYRange().max());
            if (!map.at(x,y).containsAnActor()){
                map.moveActor(actor,map.at(x,y));
                hasMove=true;
                System.out.println(actor + " moves away to (" + x + "," + y + ").");
            }
        }

        return result;
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attack " + getTarget() + " and moves away.";
    }
}
