package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithArray;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class UCS implements PFA {

    public PathWithArray path;
    public CustomPriorityQueue frontier;
    public Grid grid;
    public double cost[][];
    public Node goal;

//    private boolean[][] visited; // optional, but might influence speed
    public UCS(Grid grid) {
        this.grid = grid;
    }

    @Override
    public int search(Node start, Node goal, int directions) {
        initializeDataStructures(goal);

        frontier.heapInsert(start);
        cost[start.getX()][start.getY()] = 0.0;

        while (!frontier.isEmpty()) {
            Node current = frontier.heapDelMin();

            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }

//            if (visited[current.getX()][current.getY()]) {
//                continue;
//            }
//            visited[current.getX()][current.getY()] = true;
            expandFrontier(current, directions);
        }
        return -1;
    }

    private void initializeDataStructures(Node goal) {
        int nx = grid.getLength();
        int ny = grid.getRowLength();
        path = new PathWithArray(nx, ny);
        frontier = new CustomPriorityQueue(nx * ny);
        cost = new double[nx][ny];
        this.goal = goal;
//        visited = new boolean[nx][ny];

        for (int i = 0; i < nx; i++) {
            for (int j = 1; j < ny; j++) {
                cost[i][j] = 1000000000.0;
            }
        }
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public double getCost(Node node) {
        double c = cost[node.getX()][node.getY()];
        if (c == 1000000000.0) {
            return -1;
        }
        return c;
    }

    /**
     * Adding new nodes to the frontier (a.k.a. open set) and path
     *
     * @param current
     * @param directions
     */
    public void expandFrontier(Node current, int directions) {
        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) {
                continue;
            }
            double newCost = cost[current.getX()][current.getY()] + grid.cost(current, next);
            if (newCost < cost[next.getX()][next.getY()]) {
                cost[next.getX()][next.getY()] = newCost;
                next.setPriority(newCost);
                frontier.heapInsert(next);
                path.putCameFrom(next, current);
            }
        }
    }
}
