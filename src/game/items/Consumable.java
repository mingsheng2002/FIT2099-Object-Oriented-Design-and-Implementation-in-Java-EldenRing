package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Consumable interface represents the item that can be consumed by the Actor in the Elden Ring game to achieve some goals.
 * It gives promises to every item that implementing this interface should have also implemented the method in this interface.
 *
 * This interface is crucial so that if the item is consumed by the player, then some functionalities should be be carried out
 * towards the Actor to achieve something like increasing hitpoint or any other possible effects.
 * This can be done with implementation of the specific method below.
 *
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public interface Consumable {

    /**
     * Allow the Actor actor to consume the item and causes some effects.
     * @param actor the actor that consume the item.
     */
    void consumedBy(Actor actor);
}
