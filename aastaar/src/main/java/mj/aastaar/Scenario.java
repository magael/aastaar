package mj.aastaar;

import mj.aastaar.algorithms.PathFindingAlgorithm;
import mj.aastaar.map.Grid;
import mj.aastaar.map.MapCreator;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class Scenario {

    private Grid grid;
    private Node start;
    private Node goal;
    private Node[][] shortestPaths;
    private String[] pathColors;

    public Grid getGrid() {
        return grid;
    }

    public char[][] getGrid2D() {
        return grid.getGrid2D();
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }

    public Node[][] getShortestPaths() {
        return shortestPaths;
    }

    public String[] getPathColors() {
        return pathColors;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public void setShortestPaths(Node[][] shortestPaths) {
        this.shortestPaths = shortestPaths;
    }

    public void setPathColors(String[] pathColors) {
        this.pathColors = pathColors;
    }

    public void runPathfindingAlgorithm(PathFindingAlgorithm algorithm, String name, int i, String color) {
        System.out.println("Starting " + name);
        int pathLength = algorithm.search(grid, start, goal, 4);
        System.out.println(name + " shortest path "
                + "length: " + pathLength
                + ", cost: " + algorithm.getCost(goal));
        shortestPaths[i] = algorithm.getPath().shortestPath(goal, start, pathLength);
        System.out.println("Retrieved " + name + " shortest path as array \n");
        pathColors[i] = color;
    }

    // create the pathfinding grid based on a specific map
    // TODO:
    // read config file
    // map list / collection (probably a separate class), where:
    // - read map paths from .cfg file to String[] mapFilePaths
    // - initialize an array of Grids
    // - for each line in mapFilePaths: add to grids
    // - or mayne init one grid at a time when user chooses a map
    public void initConfig(String configFilePath) {
        if (configFilePath == null) {
            initDefaultGrid();
            initDefaultPositions();
        }
    }

    private void initDefaultGrid() {
        MapCreator mapCreator = new MapCreator();
        // example maps
        //mapCreator.createMapFromFile("mapdata/dao-map/arena2.map");
        //mapCreator.createMapFromFile("mapdata/sc1-map/Aftershock.map");
        //mapCreator.createMapFromFile("mapdata/wc3maps512-map/tranquilpaths.map");
        // timbermawhold contains shallow water
        mapCreator.createMapFromFile("mapdata/wc3maps512-map/timbermawhold.map");
        if (mapCreator.getGrid() != null) {
            char[][] gridArray = mapCreator.getGrid();
            char[] impassable = {'T', 'W', '@'};
            grid = new Grid(gridArray, impassable);
        }
    }

    // TODO: no magic numbers
    private void initDefaultPositions() {
        // example coordinates for different maps
        // Aftershock
//        int startX = 82;
//        int startY = 203;
//        int goalX = 400;
//        int goalY = 390;
        // arena
//        int startX = 4;
//        int startY = 32;
//        int goalX = 47;
//        int goalY = 19;
        // arena2
//        int startX = 84;
//        int startY = 106;
//        int goalX = 77;
//        int goalY = 85;
        // arena2 other
//        int startX = 104;
//        int startY = 44;
//        int goalX = 70;
//        int goalY = 190;
        // timbermawhold
        int startX = 70;
        int startY = 380;
        int goalX = 394;
        int goalY = 101;
        // tranquilpaths
//        int startX = 250;
//        int startY = 50;
//        int goalX = 250;
//        int goalY = 375;

        start = new Node(startX, startY, 0.0);
        goal = new Node(goalX, goalY, 0.0);
    }
}
