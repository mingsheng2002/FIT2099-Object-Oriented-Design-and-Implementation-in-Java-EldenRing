package game.actions.weaponspecialskillactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.utils.RandomNumberGenerator;

public class  UnsheatheAction extends AttackAction {

    public UnsheatheAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
        activateDoubleAttackDamage();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs \"Unsheathe\" on " + getTarget();
    }
}
