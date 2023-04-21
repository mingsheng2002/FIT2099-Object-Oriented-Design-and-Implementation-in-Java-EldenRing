package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;
import game.actors.enemies.Enemy;
import game.utils.RandomNumberGenerator;

public class Runes extends Item {

  private int totalAmount = 0;

  /***
   * Constructor.
   */
  public Runes() {
    super("Runes", '$', false);
  }

  public int getTotalAmount(){
    return this.totalAmount;
  }

  public void setTotalAmount(int amount){totalAmount = amount;}
}
