package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import java.util.List;

/**
 * A Gamemap which is the first area of Elden Ring.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see GameMap
 */
public class Limgrave extends GameMap {

  /**
   * The name of this Gamemap
   */
  private String name;
  /**
   * The door location that allow an Actor to travel to RoundtableHold Gamemap
   */
  private Location doorToRoundtableHold;
  /**
   * The door location that allow an Actor to travel to StormveilCastle Gamemap
   */
  private Location doorToStormveilCastle;

  /**
   * Constructor for Limgrave that initialize the name of the Gamemap and the location of the doors to travel to other Gamemap
   * @param groundFactory Factory to create Ground objects
   * @param lines List of Strings representing rows of the map
   */
  public Limgrave(GroundFactory groundFactory, List<String> lines) {
    super(groundFactory, lines);
    this.name = "Limgrave";
    doorToRoundtableHold = this.at(45,6);
    doorToStormveilCastle = this.at(42, 14);
  }

  /**
   * Getter to get the location of the door to travel to RoundtableHold Gamemap
   * @return the location of the door to travel to RoundtableHold Gamemap
   */
  public Location getDoorToRoundtableHold(){
    return doorToRoundtableHold;
  }

  /**
   * Getter to get the location of the door to travel to StormveilCastle Gamemap
   * @return the location of the door to travel to StormveilCastle Gamemap
   */
  public Location getDoorToStormveilCastle(){
    return doorToStormveilCastle;
  }

  /**
   * Returns string representation of the Limgrave
   * @return the name of the Limgrave
   */
  public String toString() {
    return name;
  }

}
