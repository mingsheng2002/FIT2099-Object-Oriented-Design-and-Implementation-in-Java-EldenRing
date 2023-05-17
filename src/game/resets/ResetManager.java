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
    private static ResetManager instance = null;
    /**
     * Flag indicating whether the game is resetting
     */
    private boolean isGameResetting;
    /**
     *Flag indicating whether the player is resting
     */
    private static boolean isPlayerResting;

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
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Reset all the resettable
     * @see Resettable#reset()
     */
    public void run() {
        // player is resetting the game
        isGameResetting = true;

        for (Resettable resettable : new ArrayList<Resettable>(resettables)){
            resettable.reset();
        }
        // player done resetting the game
        isGameResetting = false;
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

    /**
     * Track if the game is doing resetting.
     * @return true if the game is currently resetting; false otherwise
     */
    public boolean isGameResetting() {
        return isGameResetting;
    }

    /**
     * Set the flag isGameResetting to false once the game is done resetting.
     */
    public void gameHasDoneResetting(){
        isGameResetting = false;
    }

    /**
     * Set the flag isPlayerResting to true once the player is resting.
     */
    public void playerIsResting(){ResetManager.isPlayerResting = true;}

    /**
     * Set the flag isPlayerResting to false once the player is done resting.
     */
    public void playerDoneResting(){ResetManager.isPlayerResting = false;}

    /**
     * Getter that return the flag isPlayerResting.
     * @return true if the player is resting ,false otherwise
     */
    public boolean getPlayerIsResting(){return isPlayerResting;}
}
