/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aastaar.datastructures;

import mj.aastaar.datastructures.CustomEntry;
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
        bigNodeMap = new CustomHashMap<>();
        customLoadBooleanMap = new CustomHashMap<Node, Boolean>();
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
}
