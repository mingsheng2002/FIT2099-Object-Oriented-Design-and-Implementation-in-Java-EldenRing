package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.controllers.ResetManager;


public class TheFirstStep extends SiteOfLostGrace{

    // should i just write it in parent class??
    public void tick(Location location){
        if (location.containsAnActor()) {
            // ResetManager perform reset
            ResetManager.getInstance().run();
        }
    }

}
