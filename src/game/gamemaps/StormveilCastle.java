package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import java.util.List;

/**
 * A Gamemap which is a sub-region of the Limgrave area and the stronghold of the Godrick the Grafted.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see GameMap
 */
public class StormveilCastle extends GameMap {

  /**
   * The name of this Gamemap
   */
  private String name;
  /**
   * The door location that allow an Actor to travel to Limgrave Gamemap
   */
  private Location doorToLimgrave;
  /**
   * The door location that allow an Actor to travel to Boss Room
   */
  private Location doorToBossRoom;

  /**
   * Constructor for StormveilCastle that initialize the name of the Gamemap and the location of the doors to travel to other Gamemap
   * @param groundFactory Factory to create Ground objects
   * @param lines List of Strings representing rows of the map
   */
  public StormveilCastle(GroundFactory groundFactory, List<String> lines) {
    super(groundFactory, lines);
    this.name = "Stormveil Castle";
    doorToLimgrave = this.at(38, 22);
    doorToBossRoom = this.at(55, 2);
  }

  /**
   * Getter to get the location of the door to travel to Limgrave Gamemap
   * @return the location of the door to travel to Limgrave Gamemap
   */
  public Location getDoorToLimgrave(){
    return doorToLimgrave;
  }

  /**
   * Getter to get the the location of the door to travel to Boss Room Gamemap
   * @return the location of the door to travel to Boss Room Gamemap
   */
  public Location getDoorToBossRoom(){
    return doorToBossRoom;
  }

  /**
   * Returns string representation of the StormveilCastle
   * @return the name of the StormveilCastle
   */
  @Override
  public String toString() {
    return name;
  }
}
