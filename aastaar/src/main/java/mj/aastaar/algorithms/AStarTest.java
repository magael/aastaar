/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class AStarTest extends DijkstraWithHashMap {
    
    public AStarTest(Grid grid) {
        super(grid);
    }
    
    @Override
    public void setPriority(Node node, double cost) {
        node.setPriority(cost + getGrid().heuristic(node, getGoal()));
    }
    
}
