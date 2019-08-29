package mj.aastaar;

import java.util.Random;
import mj.aastaar.algorithms.AlgorithmVisualization;
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
    private Grid[] grids;
    private Node start;
    private Node goal;
    private AlgorithmVisualization[] algorithmVisuals;
    private int gridIndex;

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

    public Grid[] getGrids() {
        return grids;
    }

    public AlgorithmVisualization[] getAlgorithmVisuals() {
        return algorithmVisuals;
    }

    public void setAlgorithmVisuals(AlgorithmVisualization[] algorithmVisuals) {
        this.algorithmVisuals = algorithmVisuals;
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

    public void setNextGrid() {
        if (gridIndex >= grids.length - 1) {
            gridIndex = -1;
        }
        grid = grids[++gridIndex];
    }

    public void setGridIndex(int gridIndex) {
        this.gridIndex = gridIndex;
    }

    /**
     * Running the specified algorithm, adding the shortest path (array of
     * nodes) for visualization.
     *
     * @param algorithmVisual Composition of an algorithm, name and color etc.
     */
    public void runPathfindingAlgorithm(AlgorithmVisualization algorithmVisual) {
        PathfindingAlgorithm algorithm = algorithmVisual.getAlgorithm();
        int pathLength = algorithm.search(start, goal, 4);
        System.out.print(algorithmVisual.getName() + " shortest path length: " + pathLength);
        System.out.println(", cost: " + algorithm.getCost(goal) + "\n");
        algorithmVisual.setShortestPath(algorithm.getPath().shortestPath(goal, start, pathLength));
        algorithmVisual.setCameFrom(algorithm.getPath().cameFrom);
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

    public void initGrids(String[] mapPaths) {
        grids = new Grid[mapPaths.length];
        for (int i = 0; i < mapPaths.length; i++) {
            MapCreator mapCreator = new MapCreator();
            mapCreator.createMapFromFile(mapPaths[i]);
            if (mapCreator.getGrid() != null) {
                char[][] gridArray = mapCreator.getGrid();
                char[] impassable = {'T', 'W', '@'};
                double heavyEdgeWeight = 2.0;
                grids[i] = new Grid(gridArray, impassable, heavyEdgeWeight);
            }
        }
        setGrid(grids[0]);
        gridIndex = 0;
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
