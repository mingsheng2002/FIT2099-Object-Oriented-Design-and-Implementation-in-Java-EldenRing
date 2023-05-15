package game.utils;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

public class SurroundingChecker {

    public static boolean surroundingHasActorWithCapability(Location currentLocation, Status capability) {
        boolean flag = false;
        for (Exit exit : currentLocation.getExits()) {
            Location here = exit.getDestination();
            if (here.containsAnActor() && here.getActor().hasCapability(capability)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
