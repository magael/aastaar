package aastaar.algorithms.path;

import static org.junit.Assert.*;

import mj.aastaar.algorithms.path.PathWithArray;
import mj.aastaar.map.Node;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Path class.
 * 
 * @author MJ
 */
public class PathTest {

    private PathWithArray path;
    private Node nodeA;
    private Node nodeB;
    private Node nodeC;
    private Node nodeD;

    @Before
    public void setUp() {
        path = new PathWithArray(2, 2);
        nodeA = new Node(0, 0, 0);
        nodeB = new Node(0, 1, 0);
        nodeC = new Node(1, 0, 0);
        nodeD = new Node(1, 1, 0);
    }

    @Test
    public void earlyExitReturnsCorrectPathLength() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeD, nodeB);
        assertEquals(2, path.earlyExit(nodeD, nodeA));
    }
    
    @Test
    public void shortestPathContainsTheCorrectNodes() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeD, nodeB);
        Node[] shortestPath = {nodeB, nodeD};
        assertArrayEquals(shortestPath, path.shortestPath(nodeD, nodeA, 2));
    }
    
    @Test
    public void shortestPathIsNullIfNoShortestPathFound() {
        assertNull(path.shortestPath(nodeD, nodeA, 2));
    }
    
    @Test
    public void shortestPathIsNullIfAskingForInvalidLengthPath() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeD, nodeB);
        assertNull(path.shortestPath(nodeD, nodeA, 0));
    }
    
    @Test
    public void containsNodeReturnsTrueIfNodeInPath() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeD, nodeB);
        assertTrue(path.containsNode(nodeD));
    }
    
    @Test
    public void containsNodeReturnsFalseIfNodeNotInPath() {
        assertFalse(path.containsNode(nodeD));
    }
}
