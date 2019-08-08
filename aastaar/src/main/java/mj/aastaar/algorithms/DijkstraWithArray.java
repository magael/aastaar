package mj.aastaar.algorithms;

import java.util.PriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class DijkstraWithArray implements PathFindingAlgorithm {

    private Path path;
    private PriorityQueue<Node> frontier;
    private double cost[][];
    private boolean[][] visited;

    public DijkstraWithArray() {
        path = new Path();
        frontier = new PriorityQueue();
    }

    // returns the amount of steps in a shortest path or -1 if not found
    // NOTE: cannot do multiple searches with the same object
    @Override
    public int search(Grid grid, Node start, Node goal, int directions) {
        int nx = grid.getLength();
        int ny = grid.getGrid2D()[0].length;
        cost = new double[nx][ny];
        visited = new boolean[nx][ny];

        for (int i = 0; i < nx; i++) {
            for (int j = 1; j < ny; j++) {
                cost[i][j] = 1000000000.0;
            }
        }

        cost[start.getX()][start.getY()] = 0.0;
        frontier.add(new Node(start.getX(), start.getY(), 0.0));

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                return path.earlyExit(current, start);
            }

            if (visited[current.getX()][current.getY()]) {
                continue;
            }

            expandFrontier(current, grid, directions);

        }
        return -1;
    }

    @Override
    public Path getPath() {
        return path;
    }

    public double getCost(Node goal) {
        return cost[goal.getX()][goal.getY()];
    }

    // used by the search to put new nodes to the frontier (a.k.a. open set) and path
    private void expandFrontier(Node current, Grid grid, int directions) {
        visited[current.getX()][current.getY()] = true;

        for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
            if (next == null) continue;
            double newCost = cost[current.getX()][current.getY()] + grid.cost(current, next);
            if (newCost < cost[next.getX()][next.getY()]) {
                cost[next.getX()][next.getY()] = newCost;
                next.setPriority(newCost);
                frontier.add(next);
                path.putCameFrom(next, current);
            }
        }
    }
}
