/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class DijkstraOpenClosed {
    
    public double shortestPath(Grid grid, Node start, Node goal, int directions) {
        int n = 512;
        double dist[][] = new double[n + 1][n + 1];
//        HashMap<Node, Double> dist = new HashMap<>();
        boolean[][] visited = new boolean[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < visited.length; j++) {
                dist[i][j] = 1000;
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue();

        dist[start.getX()][start.getY()] = 0.0;
        //dist.put(start, 0.0);

        pq.add(new Node(start.getX(), start.getY(), 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();
            
            if (current.equals(goal)) {
                return dist[goal.getX()][goal.getY()];
            }

            if (visited[current.getX()][current.getY()]) {
                continue;
            }
            
            visited[current.getX()][current.getY()] = true;

            for (Node next : grid.getNeighbours(current.getX(), current.getY(), directions)) {
                if (next == null) continue;
                double newCost = current.getPriority() + next.getPriority();
                if (dist[next.getX()][next.getY()] > newCost) {
                    dist[next.getX()][next.getY()] = newCost;
//                    dist.put(next, newCost);
                    pq.add(new Node(next.getX(), next.getY(), dist[next.getX()][next.getY()]));

                }
//                if (next != null && dist.get(next) > newCost) {
//                    dist.put(next, newCost);
//                    pq.add(new Node(next.getX(), next.getY(), dist.get(next)));
//
//                }

            }

        }
        return -1;
//        return dist.get(goal);
    }
}
