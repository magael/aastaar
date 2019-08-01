package mj.aastaar;

import mj.aastaar.map.MapCreator;
import mj.aastaar.algorithms.BreadthFirstSearch;

/**
 *
 * @author MJ
 */

public class App {

    private static char[][] grid;
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
        if (grid != null && grid.length > 0) {
            // DEBUG: print
            // for (int i = 0; i < grid.length; i++) {
            // for (int j = 0; j < grid[i].length; j++) {
            // System.out.print(grid[i][j]);
            // }
            // System.out.println("");
            // }

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
        grid = mapCreator.getGrid();
    }
}
