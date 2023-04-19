package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import game.Behaviour;
import game.Status;
import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor {

  // XY - move the behaviour from LoneWolf to Enemy and change the access modifier from private to protected ///////////////////////
  protected Map<Integer, Behaviour> behaviours = new HashMap<>();

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Enemy(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.HOSTILE_TO_ENEMY);
  }
}
