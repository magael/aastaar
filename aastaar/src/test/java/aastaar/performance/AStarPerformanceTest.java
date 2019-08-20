package aastaar.performance;

import static org.junit.Assert.*;

import mj.aastaar.algorithms.AStar;
import mj.aastaar.map.Grid;

import org.junit.Before;
import org.junit.Test;

/**
 * Performance tests for the A* algorithm.
 *
 * @author MJ
 */
public class AStarPerformanceTest {

    private AStar astar;
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
        double edgeWeight = 2.0;
        grid = new Grid(gridArray, impassable, edgeWeight);
        astar = new AStar(grid);
    }

    @Test
    public void hello() {
    }
}
