package mj.aastaar.algorithms;

import mj.aastaar.algorithms.frontier.Frontier;
import mj.aastaar.algorithms.path.Path;
import mj.aastaar.algorithms.path.PathWithArray;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class BestFirstSearch implements PathFindingAlgorithm {

    private Grid grid;
    private Frontier frontier;
    private PathWithArray path;
    private double cost[][];

    public BestFirstSearch(Grid grid, Frontier frontier) {
        this.grid = grid;
        this.frontier = frontier;
        int nx = grid.getLength();
        int ny = grid.getRowLength();
        path = new PathWithArray(nx, ny);
        cost = new double[nx][ny];

        for (int i = 0; i < nx; i++) {
            for (int j = 1; j < ny; j++) {
                cost[i][j] = 1000000000.0;
            }
        }
    }

    @Override
    public int search(Node start, Node goal, int directions) {
        if (frontier == null) {
            System.out.println("Failed to initialize frontier");
            return -1;
        }
        frontier.setGoal(goal);
        frontier.getFrontier().heapInsert(start);
        cost[start.getX()][start.getY()] = 0.0;

        while (!frontier.getFrontier().isEmpty()) {
            Node current = frontier.getFrontier().heapDelMin();

            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }

            frontier.expandFrontier(current, grid, path, cost, directions);
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
}
