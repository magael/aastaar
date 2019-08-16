package aastaar.algorithms.path;

import mj.aastaar.algorithms.path.PathWithArray;
import mj.aastaar.map.Node;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MJ
 */
public class PathTest {

    private PathWithArray path;
    private Node a;
    private Node b;
    private Node c;
    private Node d;

    @Before
    public void setUp() {
        path = new PathWithArray(2, 2);
        a = new Node(0, 0, 0);
        b = new Node(0, 1, 0);
        c = new Node(1, 0, 0);
        d = new Node(1, 1, 0);
    }

    @Test
    public void earlyExitReturnsCorrectPathLength() {
        path.putCameFrom(b, a);
        path.putCameFrom(d, b);
        assertEquals(2, path.earlyExit(d, a));
    }
    
    @Test
    public void shortestPathContainsTheCorrectNodes() {
        path.putCameFrom(b, a);
        path.putCameFrom(d, b);
        Node[] shortestPath = {b, d};
        assertArrayEquals(shortestPath, path.shortestPath(d, a, 2));
    }
    
    @Test
    public void shortestPathIsNullIfNoShortestPathFound() {
        assertNull(path.shortestPath(d, a, 2));
    }
    
    @Test
    public void shortestPathIsNullIfAskingForInvalidLengthPath() {
        path.putCameFrom(b, a);
        path.putCameFrom(d, b);
        assertNull(path.shortestPath(d, a, 0));
    }
    
    @Test
    public void containsNodeReturnsTrueIfNodeInPath() {
        path.putCameFrom(b, a);
        path.putCameFrom(d, b);
        assertTrue(path.containsNode(d));
    }
    
    @Test
    public void containsNodeReturnsFalseIfNodeNotInPath() {
        assertFalse(path.containsNode(d));
    }
}
