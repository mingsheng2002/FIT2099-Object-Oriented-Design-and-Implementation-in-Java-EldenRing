package game.actions.weaponspecialskillactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.enums.Status;

public class QuickStepAction extends AttackAction {

    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location locationToMove = getValidExit(actor, map);
        map.moveActor(actor, locationToMove);
        actor.addCapability(Status.PROTECTED);
        return super.execute(actor,map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs \"Quickstep\" on " + getTarget();
    }

    private Location getValidExit(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (!exit.getDestination().containsAnActor()) {
                return exit.getDestination();
            }
        }
        // If every location in surrounding contains an actor, stand still
        return map.locationOf(actor);
    }
}
