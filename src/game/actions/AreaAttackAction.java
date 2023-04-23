package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Status;

import java.awt.geom.Area;

public class AreaAttackAction extends Action {

    private Weapon weapon;

    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                int damage = this.weapon.damage();
                Actor target = exit.getDestination().getActor();
                //result = actor + " performs area attack on " + target + " for " + damage + " damage";
                target.hurt(damage);
                result += System.lineSeparator() + actor + " performs area attack and " + this.weapon.verb() + " " + target + " for " + damage + " damage";
                if (!target.isConscious()) {
                    result += new DeathAction(actor).execute(target, map);
                }
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Area Attack";
    }
}
