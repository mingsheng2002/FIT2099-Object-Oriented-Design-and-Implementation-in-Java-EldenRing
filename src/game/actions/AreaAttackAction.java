package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Status;

public class AreaAttackAction extends Action {

    private Actor target;
    private Weapon weapon;

    public AreaAttackAction(Actor actor) {
        this.target = actor;
        this.weapon = this.target.getIntrinsicWeapon();
    }

    public AreaAttackAction(Actor actor, Weapon weapon) {
        this.target = actor;
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                int damage = weapon.damage();
                Actor target = exit.getDestination().getActor();
                //result = actor + " performs area attack on " + target + " for " + damage + " damage";
                target.hurt(damage);
                result += actor + " performs area attack and " + weapon.verb() + " " + target + " for " + damage + " damage\n";
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
