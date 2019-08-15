package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public interface PFA {
    
    /**
     * The main method for running the pathfinding algorithm.
     * Only one search can be performed with the same object.
     *
     * @param start
     * @param goal
     * @param directions
     * @return The amount of steps in a shortest path or -1 if not found
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
     * @param goal
     * @return The cost of the shortest path
     */
    public double getCost(Node goal);
}
