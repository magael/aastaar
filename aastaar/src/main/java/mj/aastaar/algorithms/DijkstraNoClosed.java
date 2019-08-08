package mj.aastaar.algorithms;

//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.PriorityQueue;
//import java.util.Set;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class DijkstraNoClosed implements PathFindingAlgorithm {
    
    private Path path;
    private PriorityQueue<Node> frontier;
    private HashMap<Node, Double> cost;

    public DijkstraNoClosed() {
        path = new Path();
        frontier = new PriorityQueue<>();
        cost = new HashMap<>();
    }
    
    // returns the amount of steps in a shortest path or -1 if not found
    // NOTE: cannot do multiple searches with the same object
    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        frontier.add(start);
        path.putCameFrom(start, start);
        cost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }
            expandFrontier(grid, current, directions);
        }
        return -1;
    }
    
    public double getCost(Node goal) {
        return cost.get(goal);
    }
    
    @Override
    public Path getPath() {
        return path;
    }
    
    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Grid grid, Node current, int directions) {
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
