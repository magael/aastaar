package mj.aastaar.map;

import java.util.ArrayList;
import mj.aastaar.utils.CustomFileReader;

/**
 *
 * @author MJ
 */

public class MapCreator {

    CustomFileReader lvlReader;

    public MapCreator() {
        lvlReader = new CustomFileReader();
    }

    // returns true, if map created succesfully
    public boolean createMapFromFile(String mapFilePath) {
        String mapDataLine = "";
        int i = 0;
        try {
            ArrayList<String> mapData = lvlReader.readFile(mapFilePath);
            if (!mapData.get(0).equals("type octile")) {
                System.out.println("The map file is not in the correct format");
                return false;
            }
            while (i < mapData.size() && (mapDataLine = mapData.get(i)) != null) {
                handleMapRow(i, mapDataLine);
                i++;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error when attempting to read the level data file:\n" + e);
            return false;
        }
    }

    private void handleMapRow(int i, String mapDataLine) {
        if (i == 1) {
            // init height
        } else if (i == 2) {
            // init width
        } else if (i > 3) {
            objectsFromMapRow(mapDataLine);
        }
    }

    public void objectsFromMapRow(String mapDataLine) {
        // print for debugging
        System.out.println(mapDataLine);
        for (int j = 0; j < mapDataLine.length(); j++) {
            // add characters to Grid
        }
    }
}
