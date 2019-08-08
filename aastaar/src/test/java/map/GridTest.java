package map;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MJ
 */
public class GridTest {

    Grid grid;
    
    @Before
    public void setUp() {
        char[][] gridArray = {
            {'T', 'T', 'W', '@', '@'},
            {'T', '.', 'W', 'W', '@'},
            {'W', '.', '.', '.', 'T'},
            {'S', '.', '.', '.', 'T'},
            {'S', '.', 'T', 'T', 'T'}
        };
        char[] impassable = {'T', 'W', '@'};
        grid = new Grid(gridArray, impassable);
    }
    
    @Test
    public void gettingCorrectNeighboursInTheMiddle() {
        int nodeX = 2;
        int nodeY = 2;
        int directions = 4;
        Node[] neighbours = grid.getNeighbours(nodeX, nodeY, directions);
        
        Node downNeighbour = neighbours[0];
        assertEquals(3, downNeighbour.getX());
        assertEquals(2, downNeighbour.getY());
        
        //impassable 'W'
        Node upNeighbour = neighbours[1];
        assertNull(upNeighbour);
        
        Node righNeighbour = neighbours[2];
        assertEquals(2, righNeighbour.getX());
        assertEquals(3, righNeighbour.getY());
        
        Node leftNeighbour = neighbours[3];
        assertEquals(2, leftNeighbour.getX());
        assertEquals(1, leftNeighbour.getY());
    }
    
    @Test
    public void gettingCorrectNeighboursFromAnEdge() {
        int nodeX = 4;
        int nodeY = 0;
        int directions = 4;
        Node[] neighbours = grid.getNeighbours(nodeX, nodeY, directions);
        
        Node downNeighbour = neighbours[0];
        assertNull(downNeighbour);
        
        Node upNeighbour = neighbours[1];
        assertEquals(3, upNeighbour.getX());
        assertEquals(0, upNeighbour.getY());
        
        Node rightNeighbour = neighbours[2];
        assertEquals(4, rightNeighbour.getX());
        assertEquals(1, rightNeighbour.getY());
        
        Node leftNeighbour = neighbours[3];
        assertNull(leftNeighbour);
    }
    
    @Test
    public void heuristicReturnsCorrectManhattanDistance() {
        int ax = 2;
        int ay = 4;
        int bx = 8;
        int by = 1;
        
        Node a = new Node(ax, ay, 0.0);
        Node b = new Node(bx, by, 0.0);
        
        double h = (double) Math.abs(ax - bx) + Math.abs(ay - by);
        
        assertEquals(h, grid.heuristic(a, b), 0.0);
    }
    
    @Test
    public void costReturnsOneForGround() {
        Node from = new Node(1, 1, 0);
        Node to = new Node(2, 1, 0);
        
        assertEquals(1.0, grid.cost(from, to), 0.0);
    }
    
    @Test
    public void costReturnsThreeWhenEnteringWater() {
        Node from = new Node(3, 1, 0);
        Node to = new Node(3, 0, 0);
        
        assertEquals(3.0, grid.cost(from, to), 0.0);
    }
    
    @Test
    public void costReturnsThreeWhenExitingWater() {
        Node from = new Node(3, 0, 0);
        Node to = new Node(3, 1, 0);
        
        assertEquals(3.0, grid.cost(from, to), 0.0);
    }
    
    @Test
    public void costReturnsFiveWhenMovingInWater() {
        Node from = new Node(3, 0, 0);
        Node to = new Node(4, 0, 0);
        
        assertEquals(5.0, grid.cost(from, to), 0.0);
    }
}
