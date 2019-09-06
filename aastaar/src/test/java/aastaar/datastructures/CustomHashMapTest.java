/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aastaar.datastructures;

import static org.junit.Assert.*;

import mj.aastaar.datastructures.CustomHashMap;
import mj.aastaar.map.Node;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author MJ
 */
public class CustomHashMapTest {

    private CustomHashMap<Node, Double> defaultDoubleMap;
    private CustomHashMap<Node, Node> bigNodeMap;
    private CustomHashMap<Node, Boolean> customLoadBooleanMap;
    private Node nodeA;
    private Node nodeB;

    @Before
    public void setUp() {
        defaultDoubleMap = new CustomHashMap<>();
        bigNodeMap = new CustomHashMap<>(100000);
        customLoadBooleanMap = new CustomHashMap<Node, Boolean>(2, 0.8);
        nodeA = new Node(0, 0, 5.0);
        nodeB = new Node(836, 1, 100.0);
    }

    @Test
    public void addingEntriesWorks() {
        defaultDoubleMap.put(nodeA, 72.0);
        assertTrue(defaultDoubleMap.containsKey(nodeA));

        bigNodeMap.put(nodeA, nodeB);
        assertTrue(bigNodeMap.containsKey(nodeA));

        customLoadBooleanMap.put(nodeB, true);
        assertTrue(customLoadBooleanMap.containsKey(nodeB));
    }
    
    @Test
    public void updatingEntryWorks() {
        customLoadBooleanMap.put(nodeA, true);
        assertTrue(customLoadBooleanMap.get(nodeA));
        customLoadBooleanMap.put(nodeA, false);
        assertFalse(customLoadBooleanMap.get(nodeA));
    }
    
    @Test
    public void resizingWorks() {
        Node nodeC = new Node(3, 8, 263.0);
        customLoadBooleanMap.put(nodeA, true);
        customLoadBooleanMap.put(nodeB, true);
        customLoadBooleanMap.put(nodeC, true);
        assertEquals(true, customLoadBooleanMap.get(nodeC));
    }
}
