package mj.aastaar.algorithms.path;

import mj.aastaar.map.Node;

/**
 * Storing paths and retrieving the shortest path between two positions
 * on a grid.
 * 
 * @author mijamija
 */
public class PathWithArray implements Path {

    /**
     *
     */
    private Node[][] cameFrom;

    /**
     *
     * @param nx The x in n = x * y nodes
     * @param ny The y in n = x * y nodes
     */
    public PathWithArray(int nx, int ny) {
        cameFrom = new Node[nx][ny];
    }

    /**
     *
     * @return A 2D array containing where each node has come from in the path
     */
    @Override
    public Node[][] getCameFrom() {
        return cameFrom;
    }

    @Override
    public boolean containsNode(Node node) {
        if (cameFrom[node.getX()][node.getY()] != null) {
            return true;
        }
        return false;
    }

    @Override
    public void putCameFrom(Node to, Node from) {
        cameFrom[to.getX()][to.getY()] = from;
    }
    
    
    @Override
    public Node[] shortestPath(Node goal, Node start, int length) {
        if (cameFrom[goal.getX()][goal.getY()] == null || length < 1) {
            System.out.println("Path not found.");
            return null;
        }
        Node[] path = new Node[length];
        Node current = goal;
        int i = length - 1;
        while (i >= 0) {
            path[i] = current;
            current = cameFrom[current.getX()][current.getY()];
            i--;
        }
        return path;
    }

    /**
     * Traces the steps back from goal to start,
     * returns the length of the shortest path.
     * 
     * @param current The goal node
     * @param start The start node
     * @return Amount of steps in the shortest path
     */
    public int earlyExit(Node current, Node start) {
        int steps = 0;
        while (!current.equals(start)) {
            current = cameFrom[current.getX()][current.getY()];
            steps++;
        }
        return steps;
    }
}
