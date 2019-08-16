package mj.aastaar.algorithms;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithArray;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Implementation of uniform cost search, which is a variant of
 * Dijkstra's algorithm.
 * 
 * @author MJ
 */
public class UniformCostSearch implements PathFindingAlgorithm {

    public static Grid grid;
    public static Node goal;
    
    private PathWithArray path;
    private CustomPriorityQueue frontier;
    private double cost[][];
//    private boolean[][] visited; // optional, but might influence speed

    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        this.grid = grid;
        this.goal = goal;
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
//            visited[current.getX()][current.getY()] = true;

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
        double c = cost[goal.getX()][goal.getY()];
        if (c == 1000000000.0) {
            return -1;
        }
        return c;
    }
    
    public void setPriority(Node next, double newCost) {
        next.setPriority(newCost);
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
            double newCost = cost[current.getX()][current.getY()] + grid.cost(current, next);
            if (newCost < cost[next.getX()][next.getY()]) {
                cost[next.getX()][next.getY()] = newCost;
                setPriority(next, newCost);
                frontier.heapInsert(next);
                path.putCameFrom(next, current);
            }
        }
    }
}
