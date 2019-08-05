package mj.aastaar.map;

import mj.aastaar.utils.CustomFileReader;

/**
 *
 * @author MJ
 */
// Using Nathan Sturtevant's Moving AI Lab 2D pathfinding benchmark maps.
// The maps have the following format:
//
// All maps begin with the lines:
// type octile
// height x
// width y
// map
//
// where x and y are the repsective height and width of the map.
// Normal ground is represented by a period (‘.’),
// and shallow water is represented by the ‘S’ character.
// These are the only passable types of terrain.
// All other terrain is considered to be impassable,
// including trees (‘T’), water (‘W’) and out of bounds (‘@’).
public class MapCreator {

    CustomFileReader mapReader;
    private int mapHeight;
    private int mapWidth;
    private char[][] grid;

    public MapCreator() {
        mapReader = new CustomFileReader();
        mapHeight = 0;
        mapWidth = 0;
        grid = new char[0][0];
    }

    public char[][] getGrid() {
        return grid;
    }

    public void createMapFromFile(String mapFilePath) {
        try {
            mapReader.readFile(mapFilePath);
            String[] mapData = mapReader.getDataArray();
            readMapData(mapData);
        } catch (Exception e) {
            System.out.println("Error when attempting to read the level data file:\n" + e);
        }
    }

    // check file format from the first line, start "handling" from the second line
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
                } else if (mapHeight != 512 || mapWidth != 512) {
                    //System.out.println("Map needs to be of size 512 * 512");
                    //break;
                }
            }
            handleMapRow(row, mapDataLine);
            row++;
        }
    }

    // initializing grid height and width,
    // skipping "map" and handling map characters
    // TODO: If the map height/width do not match the file, it should be scaled to that size
    private void handleMapRow(int row, String mapDataLine) {
        if (row == 1) {
            mapHeight = Integer.parseInt(mapDataLine.split(" ")[1]);
            // DEBUG: print
            // System.out.println("height: " + mapHeight);
        } else if (row == 2) {
            mapWidth = Integer.parseInt(mapDataLine.split(" ")[1]);
            // DEBUG: print
            // System.out.println("width: " + mapWidth);
        } else if (row > 3) {
            if (grid.length < 1) {
                grid = new char[mapHeight][mapWidth];
            }
            objectsFromMapRow(row - 4, mapDataLine);
        }

    }

// adding characters to the grid
    public void objectsFromMapRow(int row, String mapDataLine) {
        // DEBUG: print
        // System.out.println(mapDataLine);
        for (int col = 0; col < mapDataLine.length(); col++) {
            grid[row][col] = mapDataLine.charAt(col);
        }
    }
}
