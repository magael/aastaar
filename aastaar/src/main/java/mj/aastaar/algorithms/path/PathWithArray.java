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
    public Node[][] cameFrom;

    /**
     *
     * @param nx
     * @param ny
     */
    public PathWithArray(int nx, int ny) {
        cameFrom = new Node[nx][ny];
    }

    /**
     *
     * @return
     */
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
        if (cameFrom == null || length < 1) {
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
     * @param current
     * @param start
     * @return
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
