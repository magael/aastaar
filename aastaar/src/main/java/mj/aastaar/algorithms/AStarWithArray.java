package mj.aastaar.algorithms;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Implementation of the A* algorithm.
 *
 * @author MJ
 */
public class AStarWithArray extends DijkstraWithArray {

    /**
     *
     * @param grid Pathfinding grid
     */
    public AStarWithArray(Grid grid) {
        super(grid);
    }

    @Override
    public void setPriority(Node node, double cost) {
        node.setPriority(cost + getGrid().heuristic(node, getGoal()));
    }
}
