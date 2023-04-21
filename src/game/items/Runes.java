package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;
import game.actors.enemies.Enemy;
import game.utils.RandomNumberGenerator;

public class Runes extends Item {

  private int totalAmount;

  /***
   * Constructor.
   */
  public Runes() {
    super("Runes", '$', true);
  }

  public int getTotalAmount(){
    return this.totalAmount;
  }

  public void setTotalAmount(int amount){totalAmount = amount;}


}
