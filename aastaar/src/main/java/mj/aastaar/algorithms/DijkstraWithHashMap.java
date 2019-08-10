package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithHashMap;
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

    private PathWithHashMap path;
    private PriorityQueue<Node> frontier;
    private HashMap<Node, Double> cost;
    private HashSet<Node> visited;

    public DijkstraWithHashMap() {
        path = new PathWithHashMap();
        frontier = new PriorityQueue();
        cost = new HashMap<>();
        visited = new HashSet<>();
    }

    // returns the amount of steps in a shortest path or -1 if not found
    // NOTE: cannot do multiple searches with the same object
    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        path.putCameFrom(start, start);
        frontier.add(start);
        cost.put(start, 0.0);

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

    @Override
    public double getCost(Node goal) {
        if (!cost.containsKey(goal)) return -1;
        return cost.get(goal);
    }

    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Node current, Grid grid, int directions, Node goal) {
        visited.add(current);

        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
            double newCost = cost.get(current) + grid.cost(current, next);
            if (!cost.containsKey(next) || newCost < cost.get(next)) {
                cost.put(next, newCost);
                next.setPriority(newCost);
                frontier.add(next);
                path.putCameFrom(next, current);
            }
        }
    }
}
