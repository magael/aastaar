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
public class BreadthFirstSearch extends PathFindingAlgorithm {

    private ArrayDeque<Node> frontier;

    public BreadthFirstSearch() {
        super();
        frontier = new ArrayDeque<>();
    }
    
    //NOTE: not sure if best practice to use "super" keyword or not

    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        frontier.add(start);
        super.cameFrom.put(start, start);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return super.earlyExit(current, start);
            }

            expandFrontier(grid, current, directions);
        }
        return -1;
    }

    @Override
    public void expandFrontier(Grid grid, Node current, int directions) {
        Node[] currentNeighbours = grid.getNeighbours(current.getX(), current.getY(), directions);
        for (Node next : currentNeighbours) {
            if (next != null && !super.cameFrom.containsKey(next)) {
                frontier.add(next);
                super.cameFrom.put(next, current);
            }
        }
    }
}
