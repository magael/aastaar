/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms.frontier;

import mj.aastaar.algorithms.path.Path;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public interface Frontier {
    public void expandFrontier(Node current, Grid grid, Path path, double[][] cost, int directions);
    
    public CustomPriorityQueue getFrontier();
}
