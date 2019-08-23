package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithHashMap;
import java.util.HashMap;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class AStarWithHashMap implements PathfindingAlgorithm {
    
    private PathWithHashMap path;
    private CustomPriorityQueue frontier;
    private HashMap<Node, Double> cost;
    private Grid grid;

    public AStarWithHashMap(Grid grid) {
        this.grid = grid;
        path = new PathWithHashMap();
        frontier = new CustomPriorityQueue((grid.getLength() * grid.getRowLength()));
        cost = new HashMap<>();
    }
    
    // returns the amount of steps in a shortest path or -1 if not found
    // NOTE: cannot do multiple searches with the same object
    @Override
    public int search(Node start, Node goal, int directions) {
        path.putCameFrom(start, start);
        frontier.heapInsert(start);
        cost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.heapDelMin();

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
    
    @Override
    public double getCost(Node goal) {
        if (!cost.containsKey(goal)) return -1;
        return cost.get(goal);
    }

    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Grid grid, Node current, Node goal, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
            double newCost = cost.get(current) + grid.cost(current, next);
            if (!cost.containsKey(next) || newCost < cost.get(next)) {
                cost.put(next, newCost);
                next.setPriority(newCost + grid.heuristic(next, goal));
                frontier.heapInsert(next);
                path.putCameFrom(next, current);
            }
        }
    }
}
