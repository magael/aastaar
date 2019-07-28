package mj.aastaar.map;

import java.util.ArrayList;
import mj.aastaar.utils.CustomFileReader;

/**
 *
 * @author MJ
 */

public class MapCreator {

    CustomFileReader mapReader;

    public MapCreator() {
        mapReader = new CustomFileReader();
    }

    // returns true, if map created succesfully
    // check file format from the first line, start "handling" from the second line
    public boolean createMapFromFile(String mapFilePath) {
        try {
            mapReader.readFile(mapFilePath);
            String[] mapData = mapReader.getDataArray();
            return readMapData(mapData);
        } catch (Exception e) {
            System.out.println("Error when attempting to read the level data file:\n" + e);
            return false;
        }
    }

    public boolean readMapData(String[] mapData) {
        String mapDataLine = "";
        int i = 1;
        if (!mapData[0].equals("type octile")) {
            System.out.println("The map file is not in the correct format");
            return false;
        }
        while (i < mapData.length && (mapDataLine = mapData[i]) != null) {
            handleMapRow(i, mapDataLine);
            i++;
        }
        return true;
    }

    // NOTE: this ok or better to just use mapdata length and maprow length?
    private void handleMapRow(int i, String mapDataLine) {
        if (i == 1) {
            // init height
            // DEBUG: print
            System.out.println("height: " + mapDataLine.split(" ")[1]);
        } else if (i == 2) {
            // init width
            // DEBUG: print
            System.out.println(mapDataLine);
        } else if (i > 3) {
            objectsFromMapRow(mapDataLine);
        }
    }

    public void objectsFromMapRow(String mapDataLine) {
        // DEBUG: print
        System.out.println(mapDataLine);
        for (int j = 0; j < mapDataLine.length(); j++) {
            // add characters to Grid
        }
    }
}
