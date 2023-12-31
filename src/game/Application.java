package game;

import game.actors.Player;
import game.actors.archetypes.Archetype;
import game.actors.nonplayercharacters.traders.FingerReaderEnia;
import game.actors.nonplayercharacters.traders.MerchantKale;
import game.controllers.ArchetypeManager;
import game.environments.*;
import game.environments.spawninggrounds.*;
import game.environments.summoninggrounds.SummonSign;
import game.gamemaps.*;
import game.items.GoldenPotion;
import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.environments.restinggrounds.TheFirstStep;
import game.items.GoldenRunes;
import game.utils.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public class Application {
	public static void main(String[] args) {

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(
				new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater(),
				new Cliff(), new Cage(), new Barrack(),new TheFirstStep(), new SummonSign());


		// Create a Limgrave Map
		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................................................................",
				"........++++......................###___###................................",
				"........+++++++...................________#..............nn&&..............",
				"..........+++.....................#________...U...+........................",
				"............+++...................#_______#......=.........................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+............................................................",
				"..............++...........................................................",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");
		Limgrave limgraveMap = new Limgrave(groundFactory, limgrave);
		world.addGameMap(limgraveMap);
		// add Merchant Kale on the Limgrave game map
		limgraveMap.at(41, 11).addActor(new MerchantKale());
		limgraveMap.at(43, 9).setGround(new TheFirstStep());
		// add GoldenRunes to 10 random locations on the Limgrave game map
		limgraveMap.at(46,13).addItem(new GoldenRunes());
		limgraveMap.at(5,5).addItem(new GoldenRunes());
		limgraveMap.at(45,5).addItem(new GoldenRunes());
		limgraveMap.at(32,20).addItem(new GoldenRunes());
		limgraveMap.at(10,23).addItem(new GoldenRunes());
		limgraveMap.at(23,17).addItem(new GoldenRunes());
		limgraveMap.at(28,2).addItem(new GoldenRunes());
		limgraveMap.at(6,15).addItem(new GoldenRunes());
		limgraveMap.at(4,1).addItem(new GoldenRunes());
		limgraveMap.at(50,23).addItem(new GoldenRunes());
		// add GoldenPotion on 10 random locations on the Limgrave game map
		limgraveMap.at(50, 15).addItem(new GoldenPotion());
		limgraveMap.at(51, 15).addItem(new GoldenPotion());
		limgraveMap.at(47, 13).addItem(new GoldenPotion());
		limgraveMap.at(6, 6).addItem(new GoldenPotion());
		limgraveMap.at(46, 6).addItem(new GoldenPotion());
		limgraveMap.at(33, 20).addItem(new GoldenPotion());
		limgraveMap.at(11, 23).addItem(new GoldenPotion());
		limgraveMap.at(23, 17).addItem(new GoldenPotion());
		limgraveMap.at(29, 2).addItem(new GoldenPotion());
		limgraveMap.at(7, 15).addItem(new GoldenPotion());


		// Create a Stormveil Castle Map
		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"##############################################...##########################",
				".........=..................#................#.................=...........",
				"........BBBBBB..............#................#.............................",
				"...........................................................................",
				"............................#................#.............................",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++.........=........++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______..................................................................",
				"_____..._..____.....&............U.............................&...........",
				".........____...................................B..........................",
				"...._______................................................................",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#..............=.....+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");
		StormveilCastle stormveilCastleMap = new StormveilCastle(groundFactory, stormveilCastle);
		world.addGameMap(stormveilCastleMap);
		// add GoldenRunes on 10 random locations on the Stormveil Castle game map
		stormveilCastleMap.at(23,6).addItem(new GoldenRunes());
		stormveilCastleMap.at(52,7).addItem(new GoldenRunes());
		stormveilCastleMap.at(60,12).addItem(new GoldenRunes());
		stormveilCastleMap.at(40,12).addItem(new GoldenRunes());
		stormveilCastleMap.at(1,12).addItem(new GoldenRunes());
		stormveilCastleMap.at(15,15).addItem(new GoldenRunes());
		stormveilCastleMap.at(25,1).addItem(new GoldenRunes());
		stormveilCastleMap.at(35,20).addItem(new GoldenRunes());
		stormveilCastleMap.at(30,0).addItem(new GoldenRunes());
		stormveilCastleMap.at(68,15).addItem(new GoldenRunes());
		// add GoldenPotion on 10 random locations on the Stormveil Castle game map
		stormveilCastleMap.at(7, 11).addItem(new GoldenPotion());
		stormveilCastleMap.at(68, 11).addItem(new GoldenPotion());
		stormveilCastleMap.at(24, 6).addItem(new GoldenPotion());
		stormveilCastleMap.at(53, 7).addItem(new GoldenPotion());
		stormveilCastleMap.at(61, 12).addItem(new GoldenPotion());
		stormveilCastleMap.at(41, 12).addItem(new GoldenPotion());
		stormveilCastleMap.at(2, 12).addItem(new GoldenPotion());
		stormveilCastleMap.at(16, 15).addItem(new GoldenPotion());
		stormveilCastleMap.at(26, 1).addItem(new GoldenPotion());
		stormveilCastleMap.at(31, 0).addItem(new GoldenPotion());


		// Create a Roundtable Hold Map
		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#____=___________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");
		RoundtableHold roundtableHoldMap = new RoundtableHold(groundFactory, roundtableHold);
		world.addGameMap(roundtableHoldMap);
		// add FingerReaderEnia on the Roundtable Hold game map
		roundtableHoldMap.at(8, 5).addActor(new FingerReaderEnia());
		// add GoldenPotion on 7 random locations on the Roundtable Hold game map
		roundtableHoldMap.at(6,6).addItem(new GoldenPotion());
		roundtableHoldMap.at(1,1).addItem(new GoldenPotion());
		roundtableHoldMap.at(9,9).addItem(new GoldenPotion());
		roundtableHoldMap.at(5,5).addItem(new GoldenPotion());
		roundtableHoldMap.at(7,7).addItem(new GoldenPotion());

		// Create a boss room
		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"....=....................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");
		BossRoom bossRoomMap = new BossRoom(groundFactory, bossRoom);
		// add GoldenPotion on 7 random locations on the Boss Room game map
		bossRoomMap.at(7,7).addItem(new GoldenPotion());
		world.addGameMap(bossRoomMap);


		// setGround to GoldenFogDoor
		limgraveMap.getDoorToStormveilCastle().setGround(
				new GoldenFogDoor(stormveilCastleMap, stormveilCastleMap.getDoorToLimgrave().x(), stormveilCastleMap.getDoorToLimgrave().y()));
		limgraveMap.getDoorToRoundtableHold().setGround(
				new GoldenFogDoor(roundtableHoldMap, roundtableHoldMap.getDoorToLimgrave().x(), roundtableHoldMap.getDoorToLimgrave().y()));
		stormveilCastleMap.getDoorToLimgrave().setGround(
				new GoldenFogDoor(limgraveMap, limgraveMap.getDoorToStormveilCastle().x(), limgraveMap.getDoorToStormveilCastle().y()));
		stormveilCastleMap.getDoorToBossRoom().setGround(
				new GoldenFogDoor(bossRoomMap, bossRoomMap.getArrivalLocation().x(), bossRoomMap.getArrivalLocation().y()));
		roundtableHoldMap.getDoorToLimgrave().setGround(
				new GoldenFogDoor(limgraveMap, limgraveMap.getDoorToRoundtableHold().x(), limgraveMap.getDoorToRoundtableHold().y()));


		// Create a player
		Archetype archetype = ArchetypeManager.getInstance().run();
		Player player = new Player("Tarnished", '@',archetype);
		world.addPlayer(player, limgraveMap.at(45, 12));

		world.run();
	}
}
