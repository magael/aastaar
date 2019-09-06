package mj.aastaar.algorithms;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Implementation of the A* algorithm. Inheriting DijkstraWithHashMap.
 * 
 * @author MJ
 */
public class AStarWithHashMap extends DijkstraWithHashMap {
    
    /**
     *
     * @param grid Pathfinding grid
     */
    public AStarWithHashMap(Grid grid) {
        super(grid);
    }
    
    @Override
    public void setPriority(Node node, double cost) {
        node.setPriority(cost + getGrid().heuristic(node, getGoal()));
    }
}
