package mj.aastaar.algorithms.path;

import mj.aastaar.map.Node;

/**
 * Interface for classes that provide functionality to retrieve
 * the shortest path between two positions on a grid.
 * 
 * @author MJ
 */
public interface Path {

    /**
     *
     * @param node Node to check
     * @return True if path contains the node, otherwise false
     */
    public boolean containsNode(Node node);
    
    /**
     * Adds a link between two nodes.
     *
     * @param to One of the nodes in the path
     * @param from One of the nodes in the path
     */
    public void putCameFrom(Node to, Node from);
    
    /**
     * Returns the Nodes of the shortest path
     * after the path and it's length are found.
     * The path nodes begin with the first node after the start,
     * ending with the goal node.
     *
     * @param goal The goal node
     * @param start The start node
     * @param pathLength The length of the found shortest path
     * @return The shortest path as a Node array
     */
    public Node[] shortestPath(Node goal, Node start, int pathLength);
    
    /**
     *
     * @return
     */
    public Object getCameFrom();
}
