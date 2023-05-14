package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.weapons.Sellable;
import game.weapons.weapontypes.CurvedSword;

/**
 * A weapon that is carried by the Heavy Skeletal Swordsman.
 * It deals 115 damage with 85% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see CurvedSword
 * @see Sellable
 */
public class Grossmesser extends CurvedSword implements Sellable {

  /**
   * Damage caused by Grossmesser
   */
  private static final int DAMAGE = 115;
  /**
   * Attack accuracy of Grossmesser
   */
  private static final int HIT_RATE = 85;
  /**
   * Sell price of Grossmesser
   */
  private static final int SELL_PRICE = 100;

  /**
   * Constructor of Grossmesser.
   * @see Status#AREA_ATTACK
   */
  public Grossmesser() {
    super("Grossmesser", '?', DAMAGE, "hits", HIT_RATE);
    this.addCapability(Status.AREA_ATTACK);
  }

  /**
   * Returns the sell price of Grossmesser.
   * @return int that representing the sell price of Grossmesser.
   */
  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }

  /**
   * Returns an instance of SellAction when the Grossmesser is sold by the player.
   * @param sellable The weapon that is being sold.
   * @return an instance of SellAction.
   * @see SellAction
   */
  @Override
  public Action getSellAction(Sellable sellable) {
    return new SellAction(sellable);
  }

}
