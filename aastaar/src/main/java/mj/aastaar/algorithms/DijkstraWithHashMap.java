/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class DijkstraWithHashMap implements PathFindingAlgorithm {

    private Path path;
    private HashMap<Node, Node> cameFrom;
    private HashMap<Node, Double> cost;
    private HashSet<Node> visited;
    private PriorityQueue<Node> frontier;

    public DijkstraWithHashMap() {
        path = new Path();
        cameFrom = new HashMap<>();
        cost = new HashMap<>();
        visited = new HashSet<>();
        frontier = new PriorityQueue();
    }

    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        cost.put(start, 0.0);
        frontier.add(new Node(start.getX(), start.getY(), 0.0));

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }

            if (visited.contains(current)) {
                continue;
            }

            expandFrontier(current, grid, directions, goal);

        }
        return -1;
    }
    
    @Override
    public Path getPath() {
        return path;
    }

    private void expandFrontier(Node current, Grid grid, int directions, Node goal) {
        visited.add(current);
        
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) {
                continue;
            }
            double newCost = cost.get(current) + grid.cost(current, next);
            if (!cost.containsKey(next) || newCost < cost.get(next)) {
                cost.put(next, newCost);
                frontier.add(new Node(next.getX(), next.getY(), newCost));
                path.put(next, current);
            }
        }
    }
}
