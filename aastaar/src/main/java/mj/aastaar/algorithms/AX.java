/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class AX extends UCS {
    
    public AX(Grid grid) {
        super(grid);
    }
    
    /**
     * Adding new nodes to the frontier (a.k.a. open set) and path
     *
     * @param current
     * @param goal
     * @param directions
     */
    @Override
    public void expandFrontier(Node current, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) {
                continue;
            }
            double newCost = cost[current.getX()][current.getY()] + grid.cost(current, next);
            if (newCost < cost[next.getX()][next.getY()]) {
                cost[next.getX()][next.getY()] = newCost;
                next.setPriority(newCost + grid.heuristic(next, goal));
                frontier.heapInsert(next);
                path.putCameFrom(next, current);
            }
        }
    }
}
