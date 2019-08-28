package mj.aastaar;

import java.util.Random;
import mj.aastaar.algorithms.PathfindingAlgorithm;
import mj.aastaar.map.Grid;
import mj.aastaar.map.MapCreator;
import mj.aastaar.map.Node;

/**
 * Pathfinding scenario, containing starting and goal positions, pathfinding
 * grid based on a game map and a method for running pathfinding algorithms and
 * retrieving their shortest paths.
 *
 * @author MJ
 */
public class Scenario {

    private Grid grid;
    private Node start;
    private Node goal;
    private Node[][] shortestPaths;
    private Node[][][] cameFrom;
    private String[] pathColors;
    private PathfindingAlgorithm[] algorithms;
    private String[] algoNames;

    /**
     * @return Grid The pathfinding grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @return Grid as character array
     */
    public char[][] getGrid2D() {
        return grid.getGrid2D();
    }

    /**
     * @return Start node
     */
    public Node getStart() {
        return start;
    }

    /**
     * @return Goal node
     */
    public Node getGoal() {
        return goal;
    }

    /**
     * @return Array of shortest paths (as node arrays)
     */
    public Node[][] getShortestPaths() {
        return shortestPaths;
    }

    /**
     * @return Color hexadecimal representations for shortest path visualization
     */
    public String[] getPathColors() {
        return pathColors;
    }

    /**
     *
     * @param algoName Name of the algorithm which has explored the requested
     * nodes
     * @return The nodes explored by a pathfinding algorithm
     */
    public Node[][] getCameFrom(String algoName) {
        int index = 0;
        for (int i = 0; i < algoNames.length; i++) {
            if (algoNames[i].equals(algoName)) {
                index = i;
            }
        }
        return cameFrom[index];
    }

    /**
     *
     * @return Names of algorithms
     */
    public String[] getAlgoNames() {
        return algoNames;
    }

    /**
     *
     * @return Pathfinding algorithms
     */
    public PathfindingAlgorithm[] getAlgorithms() {
        return algorithms;
    }

    /**
     * @param grid Grid object
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * @param start Start node
     */
    public void setStart(Node start) {
        this.start = start;
    }

    /**
     * @param goal Goal node
     */
    public void setGoal(Node goal) {
        this.goal = goal;
    }

    /**
     * @param shortestPaths Array of shortest paths (as node arrays)
     */
    public void setShortestPaths(Node[][] shortestPaths) {
        this.shortestPaths = shortestPaths;
    }

    /**
     * @param pathColors Colors as hex strings for shortest path visualization
     */
    public void setPathColors(String[] pathColors) {
        this.pathColors = pathColors;
    }

    /**
     *
     * @param algoNames Names of algorithms
     */
    public void setAlgoNames(String[] algoNames) {
        this.algoNames = algoNames;
    }

    /**
     *
     * @param algorithms Pathfinding algorithms
     */
    public void setAlgorithms(PathfindingAlgorithm[] algorithms) {
        setCameFrom(new Node[algorithms.length][][]);
        this.algorithms = algorithms;
    }

    /**
     *
     * @param cameFrom The nodes explored by the different pathfinding
     * algorithms
     */
    public void setCameFrom(Node[][][] cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * Running the specified algorithm, printing the shortest path length and
     * cost and adding the shortest path (array of nodes) for visualization.
     *
     * @param algorithm Object implementing the PathFindingAlgorithm interface
     * @param name What the algorithm is called
     * @param i Index for the shortestPaths array
     */
    public void runPathfindingAlgorithm(PathfindingAlgorithm algorithm, String name, int i) {
        int pathLength = algorithm.search(start, goal, 4);
        System.out.print(name + " shortest path length: " + pathLength);
        System.out.println(", cost: " + algorithm.getCost(goal) + "\n");
        shortestPaths[i] = algorithm.getPath().shortestPath(goal, start, pathLength);
        cameFrom[i] = algorithm.getPath().cameFrom;
    }

    /**
     * Initializing random valid starting and goal coordinates.
     */
    public void initRandomPositions() {
        Random random = new Random();
        Node randomStart = new Node(-1, -1, 0.0);
        Node randomGoal = new Node(-1, -1, 0.0);

        while (!grid.nodeIsValid(randomStart)) {
            randomStart.setX(random.nextInt(grid.getLength()));
            randomStart.setY(random.nextInt(grid.getRowLength()));
        }
        while (!grid.nodeIsValid(randomGoal)) {
            randomGoal.setX(random.nextInt(grid.getLength()));
            randomGoal.setY(random.nextInt(grid.getRowLength()));
        }

        start = randomStart;
        goal = randomGoal;
    }

    /**
     * Create the pathfinding grid based on a specific map.
     */
    public void initConfig() {
        initDefaultGrid();
        initRandomPositions();
    }

    /**
     * Initializing an example map and a grid representation for it, along with
     * character representations of map nodes that are marked impassable for
     * pathfinding, and the penalty for moving through heavier terrain, in this
     * case "shallow water". The Warcraft 3 maps (wc3maps512-map/*) contain
     * shallow water.
     */
    private void initDefaultGrid() {
        MapCreator mapCreator = new MapCreator();
//        mapCreator.createMapFromFile("mapdata/dao-map/ost003d.map");
//        mapCreator.createMapFromFile("mapdata/sc1-map/Aftershock.map");
        mapCreator.createMapFromFile("mapdata/wc3maps512-map/divideandconquer.map");
//        mapCreator.createMapFromFile("mapdata/wc3maps512-map/bootybay.map");
//        mapCreator.createMapFromFile("mapdata/wc3maps512-map/timbermawhold.map");
        if (mapCreator.getGrid() != null) {
            char[][] gridArray = mapCreator.getGrid();
            char[] impassable = {'T', 'W', '@'};
            double heavyEdgeWeight = 2.0;
            grid = new Grid(gridArray, impassable, heavyEdgeWeight);
        }
    }
}
