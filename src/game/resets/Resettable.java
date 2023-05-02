package game.resets;

/**
 * A Resettable interface represents the object (includes Actor, Item and so on) that can be reset in the Elden Ring game
 * when the player is dead / the player rest at Site of Lost Grace. It gives promises to all objects that can be reset in
 * which their classes implementing this interface should have also implemented the method below.
 *
 * This interface is crucial so that if the player is dead, all objects that can be reset should carry out necessary acts.
 * This can be done by execute the specific method below in their own classes.
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public interface Resettable {

    /**
     * Carry out necessary acts by each Resettable object when the player dies or rest at the Site of Lost Grace.
     */
    void reset();
}
