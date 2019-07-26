package mj.aastaar;

import mj.aastaar.map.MapCreator;

public class App {

    private String mapFilePath;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfig("default");
    }

    // read config file, or if no path given, initialize hard coded variables
    private static void initConfig(String configFilePath) {
        MapCreator mapCreator = new MapCreator();
        if (configFilePath.equals("default")) {
            mapCreator.createMapFromFile("mapdata/dao-map/arena.map");
        }
    }
}
