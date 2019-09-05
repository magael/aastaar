package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.map.Node;

/**
 * Interface for pathfinding algorithms, such as Breadth-First search,
 * Dijkstra's algorithm and A*.
 *
 * @author MJ
 */
public interface PathfindingAlgorithm {

    /**
     * The main method for running the pathfinding algorithm,
     * returns the amount of steps in a shortest path or -1 if not found.
     * Assumes that the start node has been checked as in bounds and passable.
     * 
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
