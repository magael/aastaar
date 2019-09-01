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

    /**
     *
     * @return The pathfinding grids
     */
    public Grid[] getGrids() {
        return grids;
    }

    /**
     *
     * @return
     */
    public AlgorithmVisualization[] getAlgorithmVisuals() {
        return algorithmVisuals;
    }

    /**
     *
     * @param algorithmVisuals
     */
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

    /**
     * Set the next grid in the array of Grids
     */
    public void setNextGrid() {
        if (gridIndex >= grids.length - 1) {
            gridIndex = -1;
        }
        grid = grids[++gridIndex];
    }
    
    /**
     * Set the next grid in the array of Grids
     */
    public void setPreviousGrid() {
        if (gridIndex <= 0) {
            gridIndex = grids.length;
        }
        grid = grids[--gridIndex];
    }

    /**
     * Set the index for the Grid array
     * 
     * @param gridIndex index for the Grid array
     */
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
     *  Initialize Grids for maps based on map file data.
     * 
     * @param mapPaths Map data file paths
     * @param impassable Characters representing impassable terrain
     * @param heavyEdgeWeight Edge weight for passing through heavier terrain
     */
    public void initGrids(String[] mapPaths, char[] impassable, double heavyEdgeWeight) {
        grids = new Grid[mapPaths.length];
        for (int i = 0; i < mapPaths.length; i++) {
            MapCreator mapCreator = new MapCreator();
            mapCreator.createMapFromFile(mapPaths[i]);
            if (mapCreator.getGrid() != null) {
                char[][] gridArray = mapCreator.getGrid();
                grids[i] = new Grid(gridArray, impassable, heavyEdgeWeight);
            }
        }
        setGrid(grids[0]);
        gridIndex = 0;
    }
}
