package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Interface for pathfinding algorithms, such as Breadth-First search,
 * Dijkstra's algorithm and A*.
 *
 * @author MJ
 */
public interface PathFindingAlgorithm {

    /**
     * The main method for running the pathfinding algorithm,
     * returns the amount of steps in a shortest path or -1 if not found.
     * Only one search can be performed with the same object.
     *
     * @param grid
     * @param start
     * @param goal
     * @param directions
     * @return
     */
    public int search(Grid grid, Node start, Node goal, int directions);

    /**
     * Retrieving the found shortest path.
     *
     * @return The shortest path
     */
    public Path getPath();

    /**
     * Retrieving the cost of the found shortest path.
     *
     * @param goal
     * @return The cost of the shortest path
     */
    public double getCost(Node goal);
}
