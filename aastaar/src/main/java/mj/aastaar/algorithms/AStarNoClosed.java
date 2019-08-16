package mj.aastaar.algorithms;

import mj.aastaar.algorithms.frontier.AStarFrontierNoClosed;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;

/**
 *
 * @author MJ
 */
public class AStarNoClosed extends BestFirstSearch {

    public AStarNoClosed(Grid grid) {
        super(grid);
    }

    @Override
    public void initFrontier() {
        int nx = grid.getLength();
        int ny = grid.getRowLength();
        CustomPriorityQueue cpq = new CustomPriorityQueue(nx * ny);
        AStarFrontierNoClosed frontier = new AStarFrontierNoClosed(cpq);
        super.frontier = frontier;
    }

}
