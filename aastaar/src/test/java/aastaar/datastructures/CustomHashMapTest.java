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
    
    @Before
    public void setUp() {
        defaultDoubleMap = new CustomHashMap<>();
        bigNodeMap = new CustomHashMap<>();
        customLoadBooleanMap = new CustomHashMap<Node, Boolean>();
    }

//     @Test
//     public void addingEntriesWorks() {
//     
//     }
}
