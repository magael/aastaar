package mj.aastaar.algorithms.path;

import mj.aastaar.map.Node;

/**
 *
 * @author mijamija
 */
public interface Path {

    /**
     *
     * @param node
     * @return
     */
    public boolean containsNode(Node node);
    
    /**
     *
     * @param to
     * @param from
     */
    public void putCameFrom(Node to, Node from);
    
    /**
     * Returns the Nodes of the shortest path
     * after the path and it's length are found.
     *
     * @param goal
     * @param start
     * @param length
     * @return
     */
    public Node[] shortestPath(Node goal, Node start, int pathLength);
}
