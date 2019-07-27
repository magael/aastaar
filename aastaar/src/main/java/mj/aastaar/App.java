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
    }
}
