package aastaar.map;

import static org.junit.Assert.*;

import mj.aastaar.map.Node;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Node class.
 * 
 * @author MJ
 */
public class NodeTest {
    
    Node nodeA;
    Node nodeB;
    Node nodeC;
    Node nodeD;
    
    @Before
    public void setUp() {
        nodeA = new Node(0, 6, 0);
        nodeB = new Node(34, 170, 0);
        nodeC = new Node(0, 99, 10);
        nodeD = new Node(0, 6, -1);
    }

    @Test
    public void equalsReturnsTrueForSameObject() {
        assertTrue((nodeA.equals(nodeA)));
    }

    @Test
    public void equalsReturnsTrueForDifferentButWithEqualCoordinates() {
        assertTrue((nodeA.equals(nodeD)));
    }

    @Test
    public void equalsReturnsFalseForDifferent() {
        assertFalse((nodeA.equals(nodeB)));
    }

    @Test
    public void equalsReturnsFalseForDifferentOnSameRow() {
        assertFalse((nodeA.equals(nodeC)));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        assertFalse((nodeA.equals(null)));
    }

    @Test
    public void equalsReturnsFalseForDifferentClass() {
        String example = "not a node";
        assertFalse((nodeA.equals(example)));
    }

    @Test
    public void priorityIsMoreCorrectly() {
        assertEquals(1, nodeC.compareTo(nodeA));
    }

    @Test
    public void priorityIsLessCorrectly() {
        assertEquals(-1, nodeA.compareTo(nodeC));
    }

    @Test
    public void priorityIsSameCorrectly() {
        assertEquals(0, nodeA.compareTo(nodeB));
    }
}
