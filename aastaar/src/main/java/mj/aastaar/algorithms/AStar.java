//package mj.aastaar.algorithms;
//
//import java.util.PriorityQueue;
//import java.util.HashMap;
//import mj.aastaar.map.Grid;
//import mj.aastaar.map.Node;
//
///**
// *
// * @author MJ
// */
//
//public class AStar {
//
//    public int shortestPath(Grid grid, Node start, Node goal, int directions) {
//        PriorityQueue<Node> frontier = new PriorityQueue<>();
//        HashMap<Node, Node> cameFrom = new HashMap<>();
//
//        frontier.add(start);
//        cameFrom.put(start, start);
//
//        while (!frontier.isEmpty()) {
//            Node current = frontier.poll();
//
//            if (current.equals(goal)) {
//                return earlyExit(current, start, cameFrom);
//            }
//            
//            expandFrontier(grid, cameFrom, frontier, current, directions);
//        }
//        return -1;
//    }
//
//    private void expandFrontier(Grid grid, HashMap<Node, Node> cameFrom, PriorityQueue<Node> frontier, Node current, int directions) {
//        Node[] currentNeighbours = grid.getNeighbours(current.getX(), current.getY(), directions);
//        for (Node next : currentNeighbours) {
//            if (next != null && !cameFrom.containsKey(next)) {
//                frontier.add(next);
//                cameFrom.put(next, current);
//            }
//        }
//    }
//
//    private int earlyExit(Node current, Node start, HashMap<Node, Node> cameFrom) {
//        int steps = 0;
//        while (!current.equals(start)) {
//            current = cameFrom.get(current);
//            steps++;
//        }
//        return steps;
//    }
//}
