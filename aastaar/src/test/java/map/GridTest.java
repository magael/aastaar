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
            {'T', 'T', 'T', 'T', 'T'},
            {'T', '.', 'W', '.', 'T'},
            {'T', '.', '.', '.', 'T'},
            {'T', 'W', '.', '.', 'T'},
            {'T', 'T', 'T', 'T', 'T'}
        };
        char[] impassable = {'T', 'W', '@'};
        grid = new Grid(gridArray, impassable);
    }
    
    @Test
    public void gettingCorrectNeighbours() {
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
}
