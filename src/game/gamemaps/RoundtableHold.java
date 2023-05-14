package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import java.util.List;

/**
 * A Gamemap which is a safe place with no enemy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see GameMap
 */
public class RoundtableHold extends GameMap {

  /**
   * The name of this Gamemap
   */
  private String name;
  /**
   * The door location that allow an Actor to travel to Limgrave Gamemap
   */
  private Location doorToLimgrave;

  /**
   * Constructor for RoundtableHold that initialize the name of the Gamemap and the location of the door to travel to other Gamemap
   * @param groundFactory Factory to create Ground objects
   * @param lines List of Strings representing rows of the map
   */
  public RoundtableHold(GroundFactory groundFactory, List<String> lines) {
    super(groundFactory, lines);
    this.name = "Roundtable Hold";
    doorToLimgrave = this.at(3, 4);
  }

  /**
   * Getter to get the location of the door to travel to Limgrave Gamemap
   * @return the location of the door to travel to Limgrave Gamemap
   */
  public Location getDoorToLimgrave(){
    return doorToLimgrave;
  }

  /**
   * Returns string representation of the RoundtableHold
   * @return the name of the RoundtableHold
   */
  @Override
  public String toString() {
    return name;
  }

}
