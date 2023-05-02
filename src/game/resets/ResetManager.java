package game.resets;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public class ResetManager {

    /**
     * List of Resettable
     */
    private List<Resettable> resettables;
    /**
     *  Instance of ResetManager
     */
    private static ResetManager instance =null;

    /**
     * Private constructor of ResetManager
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Factory method use to instantiate ResetManager if instance is null.
     * @return  instance an instance of ResetManager
     */
    public static ResetManager getInstance(){
        if (instance == null){
            instance  = new ResetManager();
        }
        return instance;
    }

    /**
     * Reset all the resettable
     * @see Resettable#reset()
     */
    public void run() {
        for (Resettable resettable : new ArrayList<Resettable>(resettables)){
            resettable.reset();
        }
    }

    /**
     * Register resettable (add resettable into list resettables)
     * @param resettable Resettable instance to be added.
     * @see Resettable
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     * Remove resettable from list resettables.
     * @param resettable Resettable instance to be removed.
     * @see Resettable
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }
}
