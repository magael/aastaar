/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import java.util.HashMap;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public abstract class PathFindingAlgorithm {

    public HashMap<Node, Node> cameFrom;

    public PathFindingAlgorithm() {
        cameFrom = new HashMap<>();
    }

    // the main method for running the pathfinding algorithm
    public abstract int search(Grid grid, Node start, Node goal, int directions);
    
    // used by the search to add new nodes to the frontier (a.k.a. open set)
    // NOTE: better to leave as private method?
    public abstract void expandFrontier(Grid grid, Node current, int directions);

    // returns the Nodes of the shortest path after the path and it's length are found
    // TODO: no separate shortestPlathLength and shortestPathLength methods,
    // always return the shortest path.
    // would still require 2 iterations of the path or a dynamic or a very large array
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

    // traces the steps back from goal to start,
    // returns the length of the shortest path
    // NOTE: commented out a sketch of using a queue for the shortest path
    public int earlyExit(Node current, Node start) {
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
