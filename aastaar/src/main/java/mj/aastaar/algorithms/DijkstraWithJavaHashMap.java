package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithHashMap;
import java.util.HashMap;
import java.util.HashSet;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class DijkstraWithJavaHashMap implements PathfindingAlgorithm {

    private Node goal;
    private PathWithHashMap path;
    private CustomPriorityQueue frontier;
    private HashMap<Node, Double> cost;
//    private HashSet<Node> visited;
    private Grid grid;

    public DijkstraWithJavaHashMap(Grid grid) {
        this.grid = grid;
        initDataStructures();
    }

    // returns the amount of steps in a shortest path or -1 if not found
    // NOTE: cannot do multiple searches with the same object
    @Override
    public int search(Node start, Node goal, int directions) {
        this.goal = goal;
//        initDataStructures();
        path.putCameFrom(start, start);
        frontier.heapInsert(start);
        cost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.heapDelMin();
            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }
//            if (visited.contains(current)) {
//                continue;
//            }
            expandFrontier(current, directions);
        }
        return -1;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public double getCost(Node goal) {
        if (!cost.containsKey(goal)) {
            return -1;
        }
        return cost.get(goal);
    }

    @Override
    public boolean[][] getVisited() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Node current, int directions) {
//        visited.add(current);
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) {
                continue;
            }
            double newCost = cost.get(current) + grid.cost(current, next);
            if (!cost.containsKey(next) || newCost < cost.get(next)) {
                cost.put(next, newCost);
                next.setPriority(newCost);
                frontier.heapInsert(next);
                path.putCameFrom(next, current);
            }
        }
    }

    private void initDataStructures() {
        int nx = grid.getLength();
        int ny = grid.getRowLength();
        frontier = new CustomPriorityQueue(nx * ny);
        path = new PathWithHashMap();
        cost = new HashMap<>();
//        visited = new HashSet<>();
    }
}
