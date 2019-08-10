/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import mj.aastaar.Scenario;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
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
    
    @Test
    public void startNodeIsInvalidWhenImpassable() {
        assertFalse(scenario.startNodeIsValid());
    }
    
    @Test
    public void startNodeIsInvalidWhenOutOfBounds() {
        start.setX(-1);
        
        assertFalse(scenario.startNodeIsValid());
    }
    
    @Test
    public void startNodeIsValidatedWhenValid() {
        start.setX(1);
        start.setY(1);
        
        assertTrue(scenario.startNodeIsValid());
    }
}
