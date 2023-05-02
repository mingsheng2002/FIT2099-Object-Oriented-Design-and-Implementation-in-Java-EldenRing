package game;

import game.actors.playerarchetypes.Archetype;
import game.actors.playerarchetypes.Player;
import game.actors.enemies.crustaceans.GiantCrab;
import game.actors.enemies.skeletons.HeavySkeletalSwordsman;
import game.actors.enemies.canislupus.LoneWolf;
import game.actors.traders.MerchantKale;
import game.controllers.ArchetypeManager;
import game.environments.*;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.environments.restinggrounds.TheFirstStep;
import game.environments.spawninggrounds.Graveyard;
import game.environments.spawninggrounds.GustOfWind;
import game.environments.spawninggrounds.PuddleOfWater;
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

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater(), new TheFirstStep());


		List<String> map = Arrays.asList(
				"..............n............................................~...............",
				"...~....&.............#####....######...........&.....................&....",
				"......................#..___....____#......................................",
				"..............~...................__#..................&...................",
				".......~...........n..._____........#........n........................n....",
				".n....................#............_#......................................",
				"......................#...........###......................~...............",
				"......&....................................................................",
				"...........................................................................",
				"..................................###___###................................",
				"..................................________#..U.............................",
				"..................................#________................................",
				"..................................#_______#................................",
				"..................................###___###................................",
				"....................................#___#..................................",
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"..####__##....................................................######..##...",
				"..#.....__....................................................#....____....",
				"..#___..........................................................__.....#...",
				"..####__###..................................................._.....__.#...",
				"..............................................................###..__###...",
				"...........................................................................");
//				"...........................................................................",
//				"......................#####....######......................................",
//				"......................#..___....____#......................................",
//				"..................................__#......................................",
//				"......................._____........#......................................",
//				"......................#............_#......................................",
//				"......................#...........###......................................",
//				"...........................................................................",
//				"...........................................................................",
//				"..................................###___###................................",
//				"..................................________#................................",
//				"..................................#________................................",
//				"..................................#_______#................................",
//				"..................................###___###................................",
//				"....................................#___#..................................",
//				"...........................................................................",
//				"...........................................................................",
//				"...........................................................................",
//				"..####__##...........U........................................######..##...",
//				"..#.....__....................................................#....____....",
//				"..#___..........................................................__.....#...",
//				"..####__###..................................................._.....__.#...",
//				"..............................................................###..__###...",
//				"...........................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);


		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}


//		gameMap.at(30, 10).addActor(new LoneWolf());

//		gameMap.at(29, 10).addActor(new GiantCrab());
//		gameMap.at(28, 15).addActor(new GiantCrab());
//		gameMap.at(29, 11).addActor(new GiantCrab());
//		gameMap.at(28, 12).addActor(new GiantCrab());
//		gameMap.at(29, 13).addActor(new GiantCrab());
//		gameMap.at(28, 14).addActor(new GiantCrab());
		gameMap.at(51, 12).addActor(new LoneWolf());
		gameMap.at(52, 13).addActor(new HeavySkeletalSwordsman());
		gameMap.at(45, 13).addActor(new HeavySkeletalSwordsman());
		gameMap.at(53, 13).addActor(new GiantCrab());
		gameMap.at(54, 13).addActor(new LoneWolf());

		gameMap.at(41, 11).addActor(new MerchantKale());
//
//		gameMap.at(27, 10).addActor(new HeavySkeletalSwordsman());
//		gameMap.at(26, 10).addActor(new HeavySkeletalSwordsman());
//		gameMap.at(50, 11).addActor(new HeavySkeletalSwordsman());
//		gameMap.at(49, 10).addActor(new LoneWolf());
//		gameMap.at(50, 9).addActor(new GiantCrab());

		// HINT: what does it mean to prefer composition to inheritance?
//		Player player = new Player("Tarnished", '@', 300);
//		world.addPlayer(player, gameMap.at(50, 12));
		Archetype archetype = ArchetypeManager.getInstance().run();
		Player player = new Player("Tarnished", '@', archetype);
		world.addPlayer(player, gameMap.at(45, 12));

		world.run();
	}
}
