package mj.aastaar.algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class AStar implements PathFindingAlgorithm {
    
    private Path path;
    private PriorityQueue<Node> frontier;
    private HashMap<Node, Double> cost;

    public AStar() {
        path = new Path();
        frontier = new PriorityQueue<>();
        cost = new HashMap<>();
    }
    
    // runs the pathfinding algorithm, returns the length of the shortest path
    public int search(Grid grid, Node start, Node goal, int directions) {
        frontier.add(start);
        path.put(start, start);
        cost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }
            expandFrontier(grid, current, goal, directions);
        }
        return -1;
    }

    @Override
    public Path getPath() {
        return path;
    }

    private void expandFrontier(Grid grid, Node current, Node goal, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
            double newCost = cost.get(current) + grid.cost(current, next);
            if (!cost.containsKey(next) || newCost < cost.get(next)) {
                cost.put(next, newCost);
                next.setPriority(newCost + grid.heuristic(next, goal));
                frontier.add(next);
                path.put(next, current);
            }
        }
    }
}
