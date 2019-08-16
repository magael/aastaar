package mj.aastaar.algorithms;

import mj.aastaar.map.Node;

/**
 * Implementation of the A* algorithm.
 *
 * @author MJ
 */
public class AStar extends UniformCostSearch {

    @Override
    public void setPriority(Node next, double newCost) {
        next.setPriority(newCost + getGrid().heuristic(next, getGoal()));
    }
}
