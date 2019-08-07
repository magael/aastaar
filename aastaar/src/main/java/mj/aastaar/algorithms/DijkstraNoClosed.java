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
public class DijkstraNoClosed {
    
    private PriorityQueue<Node> frontier;
    private HashMap<Node, Node> cameFrom;
    private HashMap<Node, Double> costSoFar;

    public DijkstraNoClosed() {
        frontier = new PriorityQueue<>();
        cameFrom = new HashMap<>();
        costSoFar = new HashMap<>();
    }
    
    public int shortestPath(Grid grid, Node start, Node goal, int directions) {
        frontier.add(start);
        cameFrom.put(start, start);
        costSoFar.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return earlyExit(current, start, cameFrom);
            }
            expandFrontier(grid, current, directions);
        }
        return -1;
    }

    // this version adds duplicates to the frontier, but will require a simpler
    // priorityqueue without a decrease-key operation,
    // which is faster according to redblobgames Amit Patel
    // (redblobgames.com/pathfinding/a-star/implementation.html#algorithm),
    // citing “Priority Queues and DijkstraNoClosed’s Algorithm” by Chen et al.
    private void expandFrontier(Grid grid, Node current, int directions) {
        Node[] currentNeighbours = grid.getNeighbours(current.getX(), current.getY(), directions);
        for (Node next : currentNeighbours) {
            double newCost = costSoFar.get(current) + grid.cost(current, next);
            if (next != null && (!costSoFar.containsKey(next) || newCost < costSoFar.get(next))) {
                costSoFar.put(next, newCost);
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