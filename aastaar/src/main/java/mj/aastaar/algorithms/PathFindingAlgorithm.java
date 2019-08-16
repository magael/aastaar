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
     * @param grid The pathfinding grid
     * @param start The start node
     * @param goal The goal node
     * @param directions The amount of allowed directions for valid moves
     * @return The length of the shortest path between two positions
     */
    public int search(Node start, Node goal, int directions);

    /**
     * Retrieving the found shortest path.
     *
     * @return The shortest path
     */
    public Path getPath();

    /**
     * Retrieving the cost of the found shortest path.
     *
     * @param goal The goal node
     * @return The cost of the shortest path, or -1 if not found
     */
    public double getCost(Node goal);
}
