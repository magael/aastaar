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
public class Dijkstra implements PathFindingAlgorithm {

    private PathWithArray path;
    private CustomPriorityQueue frontier;
    private double cost[][];
//    private boolean[][] visited; // optional, but might influence speed

    // returns the amount of steps in a shortest path or -1 if not found
    // NOTE: cannot do multiple searches with the same object
    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        int nx = grid.getLength();
        int ny = grid.getRowLength();
        path = new PathWithArray(nx, ny);
        frontier = new CustomPriorityQueue(nx * ny);
        cost = new double[nx][ny];
//        visited = new boolean[nx][ny];

        for (int i = 0; i < nx; i++) {
            for (int j = 1; j < ny; j++) {
                cost[i][j] = 1000000000.0;
            }
        }

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

            expandFrontier(current, grid, directions);
        }
        return -1;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public double getCost(Node goal) {
        double c = cost[goal.getX()][goal.getY()];
        if (c == 1000000000.0) return -1;
        return c;
    }

    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Node current, Grid grid, int directions) {
//        visited[current.getX()][current.getY()] = true;

        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
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
