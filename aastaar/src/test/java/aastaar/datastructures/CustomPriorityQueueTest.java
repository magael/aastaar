package aastaar.datastructures;

import static org.junit.Assert.*;

import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Node;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the CustomPriorityQueue class.
 * 
 * @author MJ
 */
public class CustomPriorityQueueTest {
    
    CustomPriorityQueue pq;
    
    @Before
    public void setUp() {
        pq = new CustomPriorityQueue(5);
        pq.heapInsert(new Node(0, 0, 5));
        pq.heapInsert(new Node(1, 1, 3));
        pq.heapInsert(new Node(2, 2, 8));
    }
    
    @Test
    public void addingNodesWorks() {
        assertEquals(3, pq.getHeapSize());
        pq.heapInsert(new Node(3, 3, 7));
        assertEquals(4, pq.getHeapSize());
    }
    
    @Test
    public void headGrowsWhenAlmostFull() {
        pq.heapInsert(new Node(4, 4, 2));
        pq.heapInsert(new Node(5, 5, 11));
        assertEquals(5, pq.getHeapSize());
        pq.heapInsert(new Node(6, 6, 6));
        assertEquals(6, pq.getHeapSize());
    }

    @Test
    public void returnsSmallestAsRoot() {
        assertEquals(new Node(1, 1, 3), pq.heapMin());
    }
    
    @Test
    public void pollingReturnsSmallest() {
        assertEquals(new Node(1, 1, 3), pq.heapDelMin());
        assertEquals(new Node(0, 0, 5), pq.heapDelMin());
        assertEquals(new Node(2, 2, 8), pq.heapDelMin());
    }
    
    @Test
    public void pollingReducesHeapSize() {
        assertEquals(3, pq.getHeapSize());
        pq.heapDelMin();
        assertEquals(2, pq.getHeapSize());
        pq.heapDelMin();
        assertEquals(1, pq.getHeapSize());
        pq.heapDelMin();
        assertEquals(0, pq.getHeapSize());
    }
    
    @Test
    public void pollingReturnsNullIfHeapIsEmpty() {
        pq = new CustomPriorityQueue(10);
        assertNull(pq.heapDelMin());
    }
    
    @Test
    public void isEmptyIsFalseWhenHeapIsNotEmpty() {
        assertFalse(pq.isEmpty());
    }
    
    @Test
    public void isEmptyIsTrueWhenHeapIsEmpty() {
        pq = new CustomPriorityQueue(262144);
        assertEquals(0, pq.getHeapSize());
        assertTrue(pq.isEmpty());
    }
    
    @Test
    public void defaultQueueSizeWorks() {
        pq = new CustomPriorityQueue();
        pq.heapInsert(new Node(0, 0, 5));
        pq.heapInsert(new Node(1, 1, 3));
        assertEquals(2, pq.getHeapSize());
    }
}
