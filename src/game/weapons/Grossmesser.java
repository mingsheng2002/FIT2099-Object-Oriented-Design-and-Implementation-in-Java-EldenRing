package game.weapons;

import game.enums.Status;

public class Grossmesser extends CurvedSword {

  private static final int DAMAGE = 115;
  private static final int HIT_RATE = 85;

  /**
   * Constructor.
   *
   */
  public Grossmesser() {
    super("Grossmesser", '?', DAMAGE, "uses Grossmesser to attack", HIT_RATE);
    this.addCapability(Status.AREA_ATTACK);
  }
}
