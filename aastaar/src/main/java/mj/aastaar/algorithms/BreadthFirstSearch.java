/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import java.util.ArrayDeque;
import java.util.HashMap;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class BreadthFirstSearch {

    private ArrayDeque<Node> frontier;
    private HashMap<Node, Node> cameFrom;

    public BreadthFirstSearch() {
        frontier = new ArrayDeque<>();
        cameFrom = new HashMap<>();
    }

    public int shortestPath(Grid grid, Node start, Node goal, int directions) {
        frontier.add(start);
        cameFrom.put(start, start);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return earlyExit(current, start);
            }

            expandFrontier(grid, current, directions);
        }
        return -1;
    }

    private void expandFrontier(Grid grid, Node current, int directions) {
        Node[] currentNeighbours = grid.getNeighbours(current.getX(), current.getY(), directions);
        for (Node next : currentNeighbours) {
            if (next != null && !cameFrom.containsKey(next)) {
                frontier.add(next);
                cameFrom.put(next, current);
            }
        }
    }

    private int earlyExit(Node current, Node start) {
        int steps = 0;
        while (!current.equals(start)) {
            current = cameFrom.get(current);
            steps++;
        }
        return steps;
    }
}
