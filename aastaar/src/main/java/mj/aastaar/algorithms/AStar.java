package mj.aastaar.algorithms;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Implementation of the A* algorithm.
 *
 * @author MJ
 */
public class AStar extends UniformCostSearch {

    public AStar(Grid grid) {
        super(grid);
    }

    @Override
    public void setPriority(Node next, double newCost) {
        next.setPriority(newCost + getGrid().heuristic(next, getGoal()));
    }
}
