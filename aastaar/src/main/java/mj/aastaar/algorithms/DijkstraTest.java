package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithArray;
import mj.aastaar.algorithms.path.PathWithHashMap;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.datastructures.CustomHashMap;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Implementation of uniform cost search, which is a variant of Dijkstra's
 * algorithm. Using 2D-arrays for the path and path cost.
 *
 * @author MJ
 */
public class DijkstraTest implements PathfindingAlgorithm {

    private Node goal;
    private Grid grid;
//    private PathWithArray path;
    private PathWithHashMap path;
    private CustomPriorityQueue frontier;
    private CustomHashMap<Node, Double> cost;
    private boolean[][] visited;

    /**
     *
     * @param grid Pathfinding grid
     */
    public DijkstraTest(Grid grid) {
        this.grid = grid;
    }

    @Override
    public int search(Node start, Node goal, int directions) {
        if (!grid.nodeIsValid(start) || !grid.nodeIsValid(goal)) {
            System.out.println("Invalid positions.");
            return -1;
        }
        initDataStructures();
        this.goal = goal;
        frontier.heapInsert(start);
        cost.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Node current = frontier.heapDelMin();
            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }
            if (visited[current.getX()][current.getY()]) {
                continue;
            }
            visited[current.getX()][current.getY()] = true;
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
        return visited;
    }

    /**
     *
     * @return Pathfinding grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     *
     * @return Goal node
     */
    public Node getGoal() {
        return goal;
    }

    /**
     *
     * @param node The node that needs it's priority set
     * @param cost The cost to the node
     */
    public void setPriority(Node node, double cost) {
        node.setPriority(cost);
    }

    /**
     * Adding new nodes to the frontier (a.k.a. open set) and path
     *
     * @param current The position where the expansion is happening from
     * @param directions Allowed amount of directions for movement
     */
    private void expandFrontier(Node current, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) {
                continue;
            }
            double newCost = cost.get(current) + grid.cost(current, next);
            if (!cost.containsKey(next) || newCost < cost.get(next)) {
                cost.put(next, newCost);
                setPriority(next, newCost);
                frontier.heapInsert(next);
                path.putCameFrom(next, current);
            }
        }
    }

    /**
     * Initializing data structures for the path, frontier and cost.
     */
    private void initDataStructures() {
        int nx = grid.getLength();
        int ny = grid.getRowLength();
//        path = new PathWithArray(nx, ny);
        path = new PathWithHashMap();
        frontier = new CustomPriorityQueue();
        cost = new CustomHashMap<>(8191);
        visited = new boolean[nx][ny];
    }
}
