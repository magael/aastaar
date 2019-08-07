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
// TODO: extends abstract class PathFindinAlgorithm
public class BreadthFirstSearch {

    private ArrayDeque<Node> frontier;
    private HashMap<Node, Node> cameFrom;

    public BreadthFirstSearch() {
        frontier = new ArrayDeque<>();
        cameFrom = new HashMap<>();
    }
    
    // returns the Nodes of the shortest path after the path and it's length are found
    // TODO: no separate shortestPlathLength and shortestPath methods,
    // always return the shortest path.
    // will still require 2 iterations of the path or a dynamic or a very large array
    public Node[] shortestPath(Node goal, Node start, int length) {
        if (cameFrom.isEmpty() || length < 1) {
            System.out.println("Path not found.");
            return null;
        }
        Node[] path = new Node[length];
        Node current = goal;
        int i = length - 1;
        while (i >= 0) {
            path[i] = current;
            current = cameFrom.get(current);
            i--;
        }
        return path;
    }

    public int shortestPathLength(Grid grid, Node start, Node goal, int directions) {
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

    // traces the steps back from goal to start,
    // returns the length of the shortest path
    private int earlyExit(Node current, Node start) {
        int steps = 0;
        //ArrayDeque<Node> path = new ArrayDeque<>();
        while (!current.equals(start)) {
            current = cameFrom.get(current);
            //path.add(current);
            steps++;
        }
//        for (int i = 0; i < steps; i++) {
//            System.out.println(path.removeLast());
//        }
        return steps;
    }
}
