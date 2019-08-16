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
public class AStarFrontier implements Frontier {

    private CustomPriorityQueue frontier;
    private boolean[][] visited;
    private Node goal;

    public AStarFrontier(CustomPriorityQueue frontier, boolean[][] visited) {
        this.frontier = frontier;
        this.visited = visited;
    }

    @Override
    public void expandFrontier(Node current, Grid grid, Path path, double[][] cost, int directions) {
        if (visited[current.getX()][current.getY()]) {
            return;
        }
        visited[current.getX()][current.getY()] = true;
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

    @Override
    public CustomPriorityQueue getFrontier() {
        return frontier;
    }

    @Override
    public void setGoal(Node goal) {
        this.goal = goal;
    }
}
