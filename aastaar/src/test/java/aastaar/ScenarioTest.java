package aastaar;

import static org.junit.Assert.*;

import mj.aastaar.Scenario;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

import org.junit.Before;
import org.junit.Test;

/**
 * * Unit tests for the Scenario class.
 * 
 * @author MJ
 */
public class ScenarioTest {

    private Scenario scenario;
    private Node start;

    @Before
    public void setUp() {
        scenario = new Scenario();
        char[][] gridArray = {
            {'T', 'T', 'W', '@', '@'},
            {'T', '.', 'W', 'W', '@'},
            {'W', '.', '.', '.', 'T'},
            {'S', '.', '.', '.', 'T'},
            {'S', '.', 'T', 'T', 'T'}
        };
        char[] impassable = {'T', 'W', '@'};
        double edgeWeight = 2.0;
        scenario.setGrid(new Grid(gridArray, impassable, edgeWeight));
        start = new Node(0, 0, 0);
        scenario.setStart(start);
    }
}
