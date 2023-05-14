package game;

import game.actors.playerarchetypes.Archetype;
import game.actors.playerarchetypes.Player;
import game.actors.enemies.crustaceans.GiantCrab;
import game.actors.enemies.skeletons.HeavySkeletalSwordsman;
import game.actors.enemies.canislupus.LoneWolf;
import game.actors.traders.MerchantKale;
import game.controllers.ArchetypeManager;
import game.environments.*;
import game.environments.spawninggrounds.*;
import game.gamemaps.*;
import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.environments.restinggrounds.TheFirstStep;
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
				new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater(), new Cliff(), new Cage(), new Barrack(),new TheFirstStep());
		//new Cage(), new Barrack(), new Cliff(), new Summon());


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
				"........+++++++...................________#................................",
				"..........+++.....................#________...U...+........................",
				"............+++...................#_______#................................",
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

		limgraveMap.at(51, 12).addActor(new LoneWolf());
		limgraveMap.at(52, 13).addActor(new HeavySkeletalSwordsman());
		limgraveMap.at(45, 13).addActor(new HeavySkeletalSwordsman());
		limgraveMap.at(53, 13).addActor(new GiantCrab());
		limgraveMap.at(54, 13).addActor(new LoneWolf());
		limgraveMap.at(41, 11).addActor(new MerchantKale());
		limgraveMap.at(43, 9).setGround(new TheFirstStep());


		// Create a Stormveil Castle Map
		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.............................",
				"........BBBBBB..............#................#.............................",
				"...........................................................................",
				"............................#................#.............................",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<<<<<<<...<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______..................................................................",
				"_____..._..____.....&............U.............................&...........",
				".........____...................................B..........................",
				"...._______................................................................",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");
		StormveilCastle stormveilCastleMap = new StormveilCastle(groundFactory, stormveilCastle);
		world.addGameMap(stormveilCastleMap);


		// Create a Roundtable Hold Map
		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");
		RoundtableHold roundtableHoleMap = new RoundtableHold(groundFactory, roundtableHold);
		world.addGameMap(roundtableHoleMap);


		// REMOVE SUMMON IN THIS GAMEMAP FOR REQUIREMENT 1,2
		// Create a boss room
		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");
		BossRoom bossRoomMap = new BossRoom(groundFactory, bossRoom);
		world.addGameMap(bossRoomMap);


		// setGround to GoldenFogDoor
		limgraveMap.getDoorToStormveilCastle().setGround(
				new GoldenFogDoor(stormveilCastleMap, stormveilCastleMap.getDoorToLimgrave().x(), stormveilCastleMap.getDoorToLimgrave().y()));
		limgraveMap.getDoorToRoundtableHold().setGround(
				new GoldenFogDoor(roundtableHoleMap, roundtableHoleMap.getDoorToLimgrave().x(), roundtableHoleMap.getDoorToLimgrave().y()));
		stormveilCastleMap.getDoorToLimgrave().setGround(
				new GoldenFogDoor(limgraveMap, limgraveMap.getDoorToStormveilCastle().x(), limgraveMap.getDoorToStormveilCastle().y()));
		stormveilCastleMap.getDoorToBossRoom().setGround(
				new GoldenFogDoor(bossRoomMap, bossRoomMap.getArrivalLocation().x(), bossRoomMap.getArrivalLocation().y()));
		roundtableHoleMap.getDoorToLimgrave().setGround(
				new GoldenFogDoor(limgraveMap, limgraveMap.getDoorToRoundtableHold().x(), limgraveMap.getDoorToRoundtableHold().y()));


		// Create a player
		Archetype archetype = ArchetypeManager.getInstance().run();
		Player player = new Player("Tarnished", '@', archetype);
		MerchantKale merchantKale = new MerchantKale();
		stormveilCastleMap.at(6,4).addActor(merchantKale);
		world.addPlayer(player, stormveilCastleMap.at(5, 4));

		world.run();
	}
}
