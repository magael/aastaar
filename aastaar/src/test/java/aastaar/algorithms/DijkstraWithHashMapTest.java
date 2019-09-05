package aastaar.algorithms;

import static org.junit.Assert.*;

import mj.aastaar.algorithms.DijkstraWithHashMap;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Uniform cost search algorithm.
 * 
 * @author MJ
 */
public class DijkstraWithHashMapTest {

    private DijkstraWithHashMap dijkstra;
    private Grid grid;

    @Before
    public void setUp() {
        char[][] gridArray = {
            {'T', '.', 'W', '@', '@'},
            {'T', '.', 'W', 'W', '@'},
            {'W', '.', '.', '.', 'T'},
            {'S', 'S', '.', '.', '.'},
            {'.', '.', '.', 'T', 'T'}
        };
        char[] impassable = {'T', 'W', '@'};
        double heavyEdgeWeight = 2.0;
        grid = new Grid(gridArray, impassable, heavyEdgeWeight);
        dijkstra = new DijkstraWithHashMap(grid);
    }

    @Test
    public void findsTheCorrectPathLengthOfAStraightLinePath() {
        Node start = new Node(2, 1, 0);
        Node goal = new Node(2, 3, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(2, shortestPathLength);
    }

    @Test
    public void findsTheCorrectPathLengthOfACurvedPath() {
        Node start = new Node(3, 3, 0);
        Node goal = new Node(1, 1, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(4, shortestPathLength);
    }

    @Test
    public void findsTheCorrectPathLengthGoingAroundShallowWater() {
        Node start = new Node(2, 2, 0);
        Node goal = new Node(3, 0, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(5, shortestPathLength);
    }
    
    @Test
    public void findsTheCorrectPathLengthGoingThroughShallowWater() {
        Node start = new Node(2, 2, 0);
        Node goal = new Node(3, 1, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(2, shortestPathLength);
    }
    
    @Test
    public void findsTheCorrectPathLengthToTheLeftEdge() {
        Node start = new Node(1, 1, 0);
        Node goal = new Node(4, 0, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(6, shortestPathLength);
    }
    
    @Test
    public void findsTheCorrectPathLengthToTheRightEdge() {
        Node start = new Node(2, 2, 0);
        Node goal = new Node(3, 4, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(3, shortestPathLength);
    }
    
    @Test
    public void findsTheCorrectPathLengthToTheTopEdge() {
        Node start = new Node(2, 2, 0);
        Node goal = new Node(0, 1, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(3, shortestPathLength);
    }

    @Test
    public void findsTheCorrectPathLengthToTheBottomEdge() {
        Node start = new Node(2, 2, 0);
        Node goal = new Node(4, 0, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(4, shortestPathLength);
    }
    
    @Test
    public void noPathForImpassableStart() {
        Node start = new Node(0, 0, 0);
        Node goal = new Node(2, 2, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(-1, shortestPathLength);
    }
    
    @Test
    public void noPathForOutOfBoundsStart() {
        Node start = new Node(-1, -1, 0);
        Node goal = new Node(2, 2, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(-1, shortestPathLength);
    }

    @Test
    public void noPathForImpassableGoal() {
        Node start = new Node(1, 1, 0);
        Node goal = new Node(0, 4, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(-1, shortestPathLength);
    }
    
    @Test
    public void noPathForOutOfBoundsGoal() {
        Node start = new Node(1, 1, 0);
        Node goal = new Node(-1, -1, 0);
        int shortestPathLength = dijkstra.search(start, goal, 4);
        assertEquals(-1, shortestPathLength);
    }

}
