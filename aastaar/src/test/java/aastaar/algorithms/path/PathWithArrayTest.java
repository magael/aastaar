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
public class PathWithArrayTest {

    private PathWithArray path;
    private Node nodeA;
    private Node nodeB;
    private Node nodeC;

    @Before
    public void setUp() {
        path = new PathWithArray(2, 2);
        nodeA = new Node(0, 0, 0);
        nodeB = new Node(0, 1, 0);
        nodeC = new Node(1, 1, 0);
    }

    @Test
    public void earlyExitReturnsCorrectPathLength() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeC, nodeB);
        assertEquals(2, path.earlyExit(nodeC, nodeA));
    }
    
    @Test
    public void shortestPathContainsTheCorrectNodes() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeC, nodeB);
        Node[] shortestPath = {nodeB, nodeC};
        assertArrayEquals(shortestPath, path.shortestPath(nodeC, nodeA, 2));
    }
    
    @Test
    public void shortestPathIsNullIfNoShortestPathFound() {
        assertNull(path.shortestPath(nodeC, nodeA, 2));
    }
    
    @Test
    public void shortestPathIsNullIfAskingForInvalidLengthPath() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeC, nodeB);
        assertNull(path.shortestPath(nodeC, nodeA, 0));
    }
    
    @Test
    public void containsNodeReturnsTrueIfNodeInPath() {
        path.putCameFrom(nodeB, nodeA);
        path.putCameFrom(nodeC, nodeB);
        assertTrue(path.containsNode(nodeC));
    }
    
    @Test
    public void containsNodeReturnsFalseIfNodeNotInPath() {
        assertFalse(path.containsNode(nodeC));
    }
}
