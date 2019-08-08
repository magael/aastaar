/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import java.util.ArrayDeque;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class BreadthFirstSearch implements PathFindingAlgorithm {

    private ArrayDeque<Node> frontier;
    private Path path;

    public BreadthFirstSearch() {
        frontier = new ArrayDeque<>();
        path = new Path();
    }

    @Override
    public Path getPath() {
        return path;
    }
    
    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        frontier.add(start);
        path.putCameFrom(start, start);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }

            expandFrontier(grid, current, directions);
        }
        return -1;
    }

    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Grid grid, Node current, int directions) {
        Node[] currentNeighbours = grid.getNeighbours(current.getX(), current.getY(), directions);
        for (Node next : currentNeighbours) {
            if (next != null && !path.containsNode(next)) {
                frontier.add(next);
                path.putCameFrom(next, current);
            }
        }
    }
}
