package mj.aastaar.algorithms;

import mj.aastaar.map.Node;

/**
 *
 * @author mijamija
 */
public interface Path {

    public boolean containsNode(Node node);
    
    public void putCameFrom(Node to, Node from);
    
    public Node[] shortestPath(Node goal, Node start, int pathLength);
}
