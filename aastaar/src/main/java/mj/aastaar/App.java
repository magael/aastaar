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
            // TODO:
            // use the returned boolean to determine if map creation was succesful
            mapCreator.createMapFromFile("mapdata/dao-map/arena.map");
        }
        // TODO:
        // probably a separate map collection class, where:
        // - read map paths from .cfg file to String[] mapFilePaths
        // - initialize an array of map objects Map[] maps
        // - for each line in mapFilePaths:
        // add to maps the returned value of
        // mapCreator.createMapFromFile(x)
        // or maybe that is still boolean or void and add something like
        // mapCreator.getMap
    }
}
