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
// TODO: extends abstract class PathFindinAlgorithm
public class DijkstraNoClosed {
    
    private PriorityQueue<Node> frontier;
    private HashMap<Node, Node> cameFrom;
    private HashMap<Node, Double> cost;

    public DijkstraNoClosed() {
        frontier = new PriorityQueue<>();
        cameFrom = new HashMap<>();
        cost = new HashMap<>();
    }
    
    // returns the Nodes of the shortest path after the path and it's length are found
    // TODO: no separate shortestPlathLength and shortestPathLength methods,
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
        cost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return earlyExit(current, start, cameFrom);
            }
            expandFrontier(grid, current, directions);
        }
        return -1;
    }

    private void expandFrontier(Grid grid, Node current, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
            double newCost = cost.get(current) + grid.cost(current, next);
            if (!cost.containsKey(next) || newCost < cost.get(next)) {
                cost.put(next, newCost);
                next.setPriority(newCost);
                frontier.add(next);
                cameFrom.put(next, current);
            }
        }
    }

    private int earlyExit(Node current, Node start, HashMap<Node, Node> cameFrom) {
        int steps = 0;
        while (!current.equals(start)) {
            current = cameFrom.get(current);
            steps++;
        }
        return steps;
    }
}
