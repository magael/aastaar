/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms.frontier;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class UCSFrontierNoClosed implements Frontier {
    
    private CustomPriorityQueue frontier;

    public UCSFrontierNoClosed(CustomPriorityQueue frontier) {
        this.frontier = frontier;
    }
    
    @Override
    public void expandFrontier(Node current, Grid grid, Path path, double[][] cost, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
            double newCost = cost[current.getX()][current.getY()] + grid.cost(current, next);
            if (newCost < cost[next.getX()][next.getY()]) {
                cost[next.getX()][next.getY()] = newCost;
                next.setPriority(newCost);
                frontier.heapInsert(next);
                path.putCameFrom(next, current);
            }
        }
    }

    @Override
    public CustomPriorityQueue getFrontier() {
        return frontier;
    }
}
