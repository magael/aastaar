/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */

// NOTE: small optimization might be converting grid from chars to 0's & 1's
// at initialization, to simplify checking for neighbours when expanding
public class BreadthFirstSearch {

    Node start;
    Node goal;

    public BreadthFirstSearch(int startX, int startY, int goalX, int goalY) {
        start = new Node(startX, startY);
        goal = new Node(goalX, goalY);
    }

    public int shortestPath(char[][] grid) {
        ArrayDeque<Node> frontier = new ArrayDeque<>();
        HashMap<Node, Node> cameFrom = new HashMap<>();

        frontier.add(start);
        cameFrom.put(start, start);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return earlyExit(current, cameFrom);
            }
            
            expandFrontier(grid, cameFrom, frontier, current);
        }
        return -1;
    }

    private void expandFrontier(char[][] grid, HashMap<Node, Node> cameFrom, ArrayDeque<Node> frontier, Node current) {
        ArrayList<Node> currentNeighbours = getNeighbours(grid, current.getX(), current.getY());
        for (Node next : currentNeighbours) {
            if (!cameFrom.containsKey(next)) {
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

    private ArrayList<Node> getNeighbours(char[][] grid, int x, int y) {
        ArrayList<Node> neighbours = new ArrayList<>();

        if (x < (grid.length - 1) && isPassable(grid[x + 1][y])) {
            neighbours.add(new Node(x + 1, y));
        }
        if (x > 0 && isPassable(grid[x - 1][y])) {
            neighbours.add(new Node(x - 1, y));
        }
        if (y < (grid[0].length - 1) && isPassable(grid[x][y + 1])) {
            neighbours.add(new Node(x, y + 1));
        }
        if (y > 0 && isPassable(grid[x][y - 1])) {
            neighbours.add(new Node(x, y - 1));
        }

        return neighbours;
    }

    // TODO: get passable characters as parameter
    private boolean isPassable(char c) {
        if (c == 'T') return false;
        if (c == 'W') return false;
        if (c == '@') return false;
        return true;
    }
}
