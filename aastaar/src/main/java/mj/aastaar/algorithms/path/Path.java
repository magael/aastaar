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
     *
     * @param goal
     * @param start
     * @param pathLength
     * @return
     */
    public Node[] shortestPath(Node goal, Node start, int pathLength);
}
