package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;
import game.enums.Status;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour {
    private String direction;
    private Weapon weapon = null;
    private Actor target;

    public AttackBehaviour(Actor target, String direction, Weapon weapon) {
        this.direction = direction;
        this.weapon = weapon;
        this.target = target;
    }

    public AttackBehaviour(Actor target, String direction) {
        this.direction = direction;
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        if ((actor.hasCapability(Status.AREA_ATTACK) || (!actor.getWeaponInventory().isEmpty() && actor.getWeaponInventory().get(0).hasCapability(Status.AREA_ATTACK))) && RandomNumberGenerator.getRandomInt(100) < 50) {
            if (weapon == null) {
                return new AreaAttackAction(this.target);
            } else {
                return new AreaAttackAction(this.target, actor.getWeaponInventory().get(0));
            }
        }
        else {
            if (weapon == null) {
                return new AttackAction(this.target, this.direction);
            } else {
                return new AttackAction(this.target, this.direction, actor.getWeaponInventory().get(0));
            }
        }
    }
}
