package mj.aastaar.algorithms;

import java.util.PriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
// TODO: extends abstract class PathFindinAlgorithm
public class DijkstraWithArray {
    
    public double shortestPath(Grid grid, Node start, Node goal, int directions) {
        int nx = grid.getLength();
        int ny = grid.getGrid()[0].length; // getRowLength-method to grid?
        double cost[][] = new double[nx][ny];
        boolean[][] visited = new boolean[nx][ny];
        PriorityQueue<Node> frontier = new PriorityQueue();
        
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
                return cost[goal.getX()][goal.getY()];
            }

            if (visited[current.getX()][current.getY()]) {
                continue;
            }
            
            visited[current.getX()][current.getY()] = true;

            for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
                if (next == null) continue;
                double newCost = cost[current.getX()][current.getY()] + grid.cost(current, next);
                if (newCost < cost[next.getX()][next.getY()]) {
                    cost[next.getX()][next.getY()] = newCost;
                    frontier.add(new Node(next.getX(), next.getY(), newCost));

                }
            }

        }
        return -1;
    }
}
