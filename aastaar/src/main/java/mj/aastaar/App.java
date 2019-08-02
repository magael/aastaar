package mj.aastaar;

import mj.aastaar.algorithms.BreadthFirstSearch;
import mj.aastaar.map.Grid;
import mj.aastaar.map.MapCreator;

/**
 *
 * @author MJ
 */
public class App {

    private static Grid grid;
    // TODO: no magic numbers
    private static int startX = 4;
    private static int startY = 32;
    private static int goalX = 47;
    private static int goalY = 19;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfig("default");
        if (grid.getGrid() != null && grid.getLength() > 0) {
            // BFS
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            System.out.println(bfs.shortestPath(grid, startX, startY, goalX, goalY));
        }
    }

    // read config file
    // TODO:
    // map list / collection (probably a separate class), where:
    // - read map paths from .cfg file to String[] mapFilePaths
    // - initialize an array of maps (maybe Map objects)
    // - for each line in mapFilePaths: add to maps
    private static void initConfig(String configFilePath) {
        MapCreator mapCreator = new MapCreator();
        if (configFilePath.equals("default")) {
            mapCreator.createMapFromFile("mapdata/dao-map/arena.map");
        }
        char[][] gridArray = mapCreator.getGrid();
        char[] impassable = {'T', 'W', '@'};
        grid = new Grid(gridArray, impassable);
    }
}
