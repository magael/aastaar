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

    private Node start;
    private Node goal;

    public int shortestPath(Grid grid, int startX, int startY, int goalX, int goalY, int directions) {
        start = new Node(startX, startY);
        goal = new Node(goalX, goalY);
        ArrayDeque<Node> frontier = new ArrayDeque<>();
        HashMap<Node, Node> cameFrom = new HashMap<>();

        frontier.add(start);
        cameFrom.put(start, start);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return earlyExit(current, cameFrom);
            }
            
            expandFrontier(grid, cameFrom, frontier, current, directions);
        }
        return -1;
    }

    private void expandFrontier(Grid grid, HashMap<Node, Node> cameFrom, ArrayDeque<Node> frontier, Node current, int directions) {
        Node[] currentNeighbours = grid.getNeighbours(current.getX(), current.getY(), directions);
        for (Node next : currentNeighbours) {
            if (next != null && !cameFrom.containsKey(next)) {
                frontier.add(next);
                cameFrom.put(next, current);
            }
        }
    }

    private int earlyExit(Node current, HashMap<Node, Node> cameFrom) {
        int steps = 0;
        while (!current.equals(start)) {
            current = cameFrom.get(current);
            steps++;
        }
        return steps;
    }
}
