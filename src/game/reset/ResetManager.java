package game.reset;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance =null;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Factory method use to instantiate ResetManager
     */
    public static ResetManager getInstance(){
        if (instance == null){
            instance  = new ResetManager();
        }
        return instance;
    }

    /**
     * Reset all the resettable
     */
    public void run() {
        for (Resettable resettable : new ArrayList<Resettable>(resettables)){
            resettable.reset();
        }
    }

    /**
     * Register resettable (add resettable into list resettables)
     * @param resettable
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }
}
