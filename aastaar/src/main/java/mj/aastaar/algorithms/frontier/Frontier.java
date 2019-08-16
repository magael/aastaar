package mj.aastaar.algorithms.frontier;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public interface Frontier {
    public void expandFrontier(Node current, Grid grid, Path path, double[][] cost, int directions);
    
    public CustomPriorityQueue getFrontier();
    
    public void setGoal(Node goal);
}
