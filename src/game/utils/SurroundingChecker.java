package game.utils;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.items.Sellable;

/**
 * The SurroundingChecker class provides utility methods to check the presence of actors with specific capabilities
 * in the surrounding locations.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public class SurroundingChecker {

    /**
     * Checks if there is an actor with the specified capability in the surrounding locations of the current location.
     * @param currentLocation the current location
     * @param capability the capability to check for
     * @return true if there is an actor with the specified capability in the surrounding locations, false otherwise
     */
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
