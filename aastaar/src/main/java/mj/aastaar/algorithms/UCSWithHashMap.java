package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithArray;
import mj.aastaar.algorithms.path.PathWithHashMap;
import mj.aastaar.datastructures.CustomHashMap;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class UCSWithHashMap implements PathfindingAlgorithm {

    private Node goal; 
    private PathWithArray path;
//    private PathWithHashMap path;
    private CustomPriorityQueue frontier;
    private CustomHashMap<Node, Double> cost;
    private Grid grid;

    public UCSWithHashMap(Grid grid) {
        this.grid = grid;
        initDataStructures();
    }

    // returns the amount of steps in a shortest path or -1 if not found
    // NOTE: cannot do multiple searches with the same object
    @Override
    public int search(Node start, Node goal, int directions) {
        this.goal = goal;
        frontier.heapInsert(start);
        cost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.heapDelMin();
            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }
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
        if (!cost.containsKey(goal)) return -1;
        return cost.get(goal);
    }

    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Node current, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
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
//        path = new PathWithHashMap();
        path = new PathWithArray(nx, ny);
        frontier = new CustomPriorityQueue(nx * ny);
        cost = new CustomHashMap<>(16);
    }
}
