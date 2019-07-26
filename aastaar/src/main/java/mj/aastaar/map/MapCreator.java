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
    
    public void createMapFromFile(String mapFilePath) {
        String mapDataLine = "";
        int i = 0;
        try {
            ArrayList<String> mapData = lvlReader.readFile(mapFilePath);
            while (i < mapData.size() && (mapDataLine = mapData.get(i)) != null) {
                handleMapRow(i, mapDataLine);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error when attempting to read the level data file:\n" + e);
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
        for (int j = 0; j < mapDataLine.length(); j++) {
//            switch (lvlDataLine.charAt(j)) {
//                case '0':
//                    addGround();
//                    break;
//                case '1':
//                    addObstacle();
//                    break;
//                case '2':
//                    addPlatform();
//                    break;
//                default:
//                    break;
//            }
        }
    }
}
