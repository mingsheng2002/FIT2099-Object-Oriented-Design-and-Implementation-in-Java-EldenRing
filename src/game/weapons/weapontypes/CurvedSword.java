package game.weapons.weapontypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class representing Curved Sword type weapon.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see WeaponItem
 */
public abstract class CurvedSword extends WeaponItem {

  /**
   * Constructor for CurvedSword.
   * @param name        name of the item
   * @param displayChar character to use for display when item is on the ground
   * @param damage      amount of damage this weapon does
   * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
   * @param hitRate     the probability/chance to hit the target.
   */
  public CurvedSword(String name, char displayChar, int damage, String verb, int hitRate) {
    super(name, displayChar, damage, verb, hitRate);
  }
}
