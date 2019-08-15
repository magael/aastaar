package mj.aastaar.map;

import mj.aastaar.utils.CustomFileReader;

/**
 * Using Nathan Sturtevant's Moving AI Lab 2D pathfinding benchmark maps.
 * The maps have the following format:
 * All maps begin with the lines:
 * type octile
 * height x
 * width y
 * map
 * where x and y are the respective height and width of the map.
 * Normal ground is represented by a period (‘.’),
 * and shallow water is represented by the ‘S’ character.
 * These are the only passable types of terrain.
 * All other terrain is considered to be impassable,
 * including trees (‘T’), water (‘W’) and out of bounds (‘@’).
 * 
 * @author MJ
 */
public class MapCreator {

    CustomFileReader mapReader;
    private int mapHeight;
    private int mapWidth;
    private char[][] grid;

    /**
     *
     */
    public MapCreator() {
        mapReader = new CustomFileReader();
        mapHeight = 0;
        mapWidth = 0;
        grid = new char[0][0];
    }

    /**
     *
     * @return A 2D character array representation of the map grid
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     *
     * @param mapFilePath Map file path
     */
    public void createMapFromFile(String mapFilePath) {
        try {
            mapReader.readFile(mapFilePath);
            String[] mapData = mapReader.getDataArray();
            readMapData(mapData);
        } catch (Exception e) {
            System.out.println("Error when attempting to read the level data file:\n" + e);
        }
    }

    /**
     * Checking the file format from the first line,
     * starting "handling" from the second line.
     * 
     * @param mapData String array of map data file rows
     */
    public void readMapData(String[] mapData) {
        String mapDataLine = "";
        int row = 1;
        if (!mapData[0].equals("type octile")) {
            System.out.println("The map data format is incorrect");
        }
        while (row < mapData.length && (mapDataLine = mapData[row]) != null) {
            if (row > 3) {
                if (mapHeight < 1 || mapWidth < 1) {
                    System.out.println("Error reading map dimensions");
                    break;
                }
            }
            handleMapRow(row, mapDataLine);
            row++;
        }
    }
        
    /**
     * Adding characters to the grid.
     *
     * @param row Grid row index
     * @param mapDataLine Map data file row
     */
    public void objectsFromMapRow(int row, String mapDataLine) {
        for (int col = 0; col < mapDataLine.length(); col++) {
            grid[row][col] = mapDataLine.charAt(col);
        }
    }

    /**
     * Initializing grid height and width,
     * skipping "map" and handling map characters.
     * 
     * @param row Map data file row index
     * @param mapDataLine Map data file row
     */
    private void handleMapRow(int row, String mapDataLine) {
        if (row == 1) {
            mapHeight = Integer.parseInt(mapDataLine.split(" ")[1]);
        } else if (row == 2) {
            mapWidth = Integer.parseInt(mapDataLine.split(" ")[1]);
        } else if (row > 3) {
            if (grid.length < 1) {
                grid = new char[mapHeight][mapWidth];
            }
            objectsFromMapRow(row - 4, mapDataLine);
        }

    }
}
