package mj.aastaar.algorithms;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public interface PathFindingAlgorithm {
    
        // the main method for running the pathfinding algorithm
    public int search(Grid grid, Node start, Node goal, int directions);
    
    public Path getPath();
    
    public double getCost(Node goal);
}
