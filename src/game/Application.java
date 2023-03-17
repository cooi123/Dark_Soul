package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.enemy.AldrichTheDevourer;
import game.enemy.Skeleton;
import game.enemy.YhormTheGiant;
import game.ground.*;
import game.nonEnemy.Chest;
import game.nonEnemy.Player;
import game.nonEnemy.Vendor;
import game.weapons.StormRuler;

/**
 * The main class for the Jurassic World game.
 */
public class Application {

    /**
     * This method creates the game maps, adds ground and characters to them then runs the game.
     * @param args
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery());

        // Profane capital map
        List<String> profaneCap = Arrays.asList(
                "..++++++..+++...........................++++......+++.................+++.......",
                "........+++++..............................+++++++.................+++++........",
                "...........+++..............................................c........+++++......",
                "........................................................................++......",
                "....................................c....................................+++....",
                "............................+.............................................+++...",
                "..............c..............+++.......++++.....................................",
                ".............................++.......+......................++++...............",
                ".............................................................+++++++............",
                "..................................###___###...................+++...............",
                "..................................#_______#......................+++............",
                "...........++.....................#_______#.......................+.............",
                ".........+++......................#_______#........................++...........",
                "............+++...................####_####..........................+..........",
                "..............+......................................................++.........",
                "..............++......................................c..........++++++.........",
                "............+++...................................................++++..........",
                "+..................................................................++...........",
                "++...+++.........................................................++++...........",
                "+++......................................+++........................+.++........",
                "++++.......++++.........................++.........................+....++......",
                "#####___#####++++......................+...............................+..+.....",
                "_..._....._._#.++......................+...................................+....",
                "...+.__..+...#+++...................................c.......................+...",
                "...+.....+._.#.+.....+++++...++..............................................++.",
                "___.......___#.++++++++++++++.+++.............................................++");
        GameMap profaneCapMap = new GameMap(groundFactory, profaneCap);
        world.addGameMap(profaneCapMap);

        Actor player = new Player("Unkindled (Player)", '@', 99990, profaneCapMap.at(38, 12));

        Actor vendor = new Vendor();

        world.addPlayer(vendor, profaneCapMap.at(39, 11));

        // Place Yhorm the Giant/boss in the map
        profaneCapMap.at(6, 25).addActor(new YhormTheGiant(profaneCapMap.at(6, 25)));
        profaneCapMap.at(5, 25).addItem(new StormRuler());


        profaneCapMap.at(41, 7).addActor(new Skeleton("Skeleton", profaneCapMap.at(41, 7)));
        profaneCapMap.at(20, 7).addActor(new Skeleton("Skeleton", profaneCapMap.at(20, 7)));
        profaneCapMap.at(38, 17).addActor(new Skeleton("Skeleton", profaneCapMap.at(38, 17)));
        profaneCapMap.at(39, 18).addActor(new Skeleton("Skeleton", profaneCapMap.at(39, 18)));
        profaneCapMap.at(41, 20).addActor(new Skeleton("Skeleton", profaneCapMap.at(41, 20)));

        world.addPlayer(player, profaneCapMap.at(38, 12));

        profaneCapMap.at(37, 25).setGround(new FogDoor(profaneCapMap.at(37, 25), "Profane Capital Fog Door"));
        profaneCapMap.at(38, 11).setGround(new BonfireGround("Firelink Shrine's Bonfire", profaneCapMap.at(38, 11), true));

        profaneCapMap.at(38, 15).addActor(new Chest(profaneCapMap.at(38, 10)));


        // Anor Londo GameMap
        List<String> anorLondo = Arrays.asList(
                ".....+..............+++...............c.................+++.....",
                "..................................++............................",
                "................................................................",
                ".................###___###........................++++..........",
                ".................#_______#.....................+++..............",
                "........c........#_______#......................................",
                ".................#_______#..........++..........................",
                "......++++.......###___###...........++++.......................",
                ".....+.................................++++.....................",
                "....++....................................+.....................",
                ".....+++...................####__######____#....................",
                "....++.....................#...............#.........c..........",
                "...+++++...................#...__...#...#.++....................",
                "....+++++..................#.........__....#....................",
                "........+++................#.....++..._....#....................",
                "...........................############++++#...........+........",
                "...................+.............c.....................++.......",
                "...................+...............................+++++........");


        GameMap anorLondoMap = new GameMap(groundFactory, anorLondo);
        world.addGameMap(anorLondoMap);

        anorLondoMap.at(30, 13).addActor(new AldrichTheDevourer(anorLondoMap.at(30, 13)));

        anorLondoMap.at(30, 0).setGround(new FogDoor(anorLondoMap.at(30, 0), "Anor Londo Fog Door"));
        anorLondoMap.at(28, 14).setGround(new FogDoor(anorLondoMap.at(28, 14), "Aldrich's Lair Fog Door"));
        anorLondoMap.at(0, 17).setGround(new FogDoor(anorLondoMap.at(0, 17), "Test - placeholder representing next level door"));
        anorLondoMap.at(22, 5).setGround(new BonfireGround("Anor Londo's Bonfire", anorLondoMap.at(22, 5), false));
        anorLondoMap.at(20, 15).addActor(new Chest(anorLondoMap.at(20, 15)));
        anorLondoMap.at(30, 5).addActor(new Chest(anorLondoMap.at(30, 5)));
        anorLondoMap.at(40, 17).addActor(new Chest(anorLondoMap.at(40, 17)));

        world.run();

    }
}
