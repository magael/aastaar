/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj.aastaar.algorithms;

import mj.aastaar.algorithms.frontier.AStarFrontier;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;

/**
 *
 * @author MJ
 */
public class AStar extends BestFirstSearch {

    public AStar(Grid grid) {
        super(grid);
        int nx = grid.getLength();
        int ny = grid.getRowLength();
        CustomPriorityQueue cpq = new CustomPriorityQueue(nx * ny);
        boolean[][] visited = new boolean[nx][ny];
        AStarFrontier frontier = new AStarFrontier(cpq, visited);
        super.frontier = frontier;
    }
}
