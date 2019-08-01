package mj.aastaar;

import mj.aastaar.map.MapCreator;

/**
 *
 * @author MJ
 */

public class App {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfig("default");
    }

    // read config file, or if no path given, attempt hard coded variables
    private static void initConfig(String configFilePath) {
        MapCreator mapCreator = new MapCreator();
        if (configFilePath.equals("default")) {
            mapCreator.createMapFromFile("mapdata/dao-map/arena.map");
        }
        char[][] grid = mapCreator.getGrid();
        if (grid != null) {
            // DEBUG: print
            // for (int i = 0; i < grid.length; i++) {
            // for (int j = 0; j < grid[i].length; j++) {
            // System.out.print(grid[i][j]);
            // }
            // System.out.println("");
            // }
        }
        // TODO:
        // probably a separate map collection class, where:
        // - read map paths from .cfg file to String[] mapFilePaths
        // - initialize an array of map objects Map[] maps
        // - for each line in mapFilePaths:
        // add to maps
    }
}
