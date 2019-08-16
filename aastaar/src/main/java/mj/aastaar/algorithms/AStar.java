package mj.aastaar.algorithms;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Implementation of the A* algorithm.
 *
 * @author MJ
 */
public class AStar extends UniformCostSearch {

    /**
     *
     * @param grid Pathfinding grid
     */
    public AStar(Grid grid) {
        super(grid);
    }

    @Override
    public void setPriority(Node node, double cost) {
        node.setPriority(cost + getGrid().heuristic(node, getGoal()));
    }
}
