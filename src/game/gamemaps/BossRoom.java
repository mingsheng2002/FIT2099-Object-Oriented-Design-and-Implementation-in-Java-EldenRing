package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import java.util.List;

/**
 * A Gamemap which contain the ruler of Limgrave, Godrick the Grafted
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see GameMap
 */
public class BossRoom extends GameMap {

  /**
   * The name of this Gamemap
   */
  private String name;
  /**
   * The location where an Actor stands on when travelling from other Gamemap
   */
  private Location arrivalLocation;

  /**
   * Constructor for BossRoom that initialize the name of the Gamemap and
   * the location that the Actor stands on when Actor arrive
   * @param groundFactory Factory to create Ground objects
   * @param lines List of Strings representing rows of the map
   */
  public BossRoom(GroundFactory groundFactory, List<String> lines) {
    super(groundFactory, lines);
    this.name = "Godrick the Grafted";
    arrivalLocation = this.at(4, 6);
  }

  /**
   * Getter to get the location where an Actor stands on when travelling from other Gamemap
   * @return the location of the door to travel to RoundtableHold Gamemap
   */
  public Location getArrivalLocation() {
    return arrivalLocation;
  }

  /**
   * Returns string representation of the BossRoom
   * @return the name of the BossRoom
   */
  @Override
  public String toString() {
    return name;
  }

}
