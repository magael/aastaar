package mj.aastaar.algorithms.path;

import mj.aastaar.datastructures.CustomHashMap;
import mj.aastaar.map.Node;

/**
 * Storing paths and retrieving the shortest path between two positions
 * on a grid.
 * 
 * @author MJ
 */
public class PathWithHashMap implements Path {

    private CustomHashMap<Node, Node> cameFrom;
    private final int DEFAULT_SIZE = 11;

    /**
     * Initializing the hash map of node links with the default size.
     */
    public PathWithHashMap() {
        cameFrom = new CustomHashMap<>(DEFAULT_SIZE);
    }
    
    /**
     * Initializing the hash map of node links with a custom size.
     */
    public PathWithHashMap(int size) {
        cameFrom = new CustomHashMap<>(size);
    }
    
    @Override
    public boolean containsNode(Node node) {
        return cameFrom.containsKey(node);
    }
    
    @Override
    public void putCameFrom(Node to, Node from) {
        cameFrom.put(to, from);
    }

    @Override
    public Node[] shortestPath(Node goal, Node start, int length) {
        if (cameFrom.isEmpty() || length < 1) {
            System.out.println("Path not found.");
            return null;
        }
        Node[] path = new Node[length];
        Node current = goal;
        int i = length - 1;
        while (i >= 0) {
            path[i] = current;
            current = cameFrom.get(current);
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
            current = cameFrom.get(current);
            steps++;
        }
        return steps;
    }
}
