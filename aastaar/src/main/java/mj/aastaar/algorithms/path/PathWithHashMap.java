package mj.aastaar.algorithms.path;

import mj.aastaar.datastructures.CustomHashMap;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class PathWithHashMap implements Path {

    private CustomHashMap<Node, Node> cameFrom;

    /**
     *
     */
    public PathWithHashMap() {
        cameFrom = new CustomHashMap<>();
    }

    /**
     *
     * @return
     */
    public CustomHashMap<Node, Node> getCameFrom() {
        return cameFrom;
    }
    
    public boolean containsNode(Node node) {
        if (cameFrom.containsKey(node)) {
            return true;
        }
        return false;
    }
    
    public void putCameFrom(Node to, Node from) {
        cameFrom.put(to, from);
    }

    // traces the steps back from goal to start,
    // returns the length of the shortest path

    /**
     *
     * @param current
     * @param start
     * @return
     */
    public int earlyExit(Node current, Node start) {
        int steps = 0;
        while (!current.equals(start)) {
            current = cameFrom.get(current);
            steps++;
        }
        return steps;
    }
    
    // returns the Nodes of the shortest path after the path and it's length are found
    // TODO: no separate shortestPlathLength and shortestPathLength methods,
    // always return the shortest path.
    // would still require 2 iterations of the path or a dynamic or a very large array
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
}
