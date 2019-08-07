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
public class DijkstraWithHashMap {

    public double shortestPath(Grid grid, Node start, Node goal, int directions) {
        HashMap<Node, Double> cost = new HashMap<>();
        HashSet<Node> visited = new HashSet<>();
        PriorityQueue<Node> frontier = new PriorityQueue();

        cost.put(start, 0.0);
        frontier.add(new Node(start.getX(), start.getY(), 0.0));

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            
            if (current.equals(goal)) {
                return cost.get(goal);
            }

            if (visited.contains(current)) {
                continue;
            }
            
            visited.add(current);

            for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
                if (next == null) continue;
                double newCost = cost.get(current) + grid.cost(next, goal);
                if (!cost.containsKey(next) || cost.get(next) > newCost) {
                    cost.put(next, newCost);
                    frontier.add(new Node(next.getX(), next.getY(), newCost));
                }

            }

        }
        return -1;
    }
}
