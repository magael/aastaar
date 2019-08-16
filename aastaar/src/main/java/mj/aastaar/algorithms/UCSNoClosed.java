package mj.aastaar.algorithms;

import mj.aastaar.algorithms.frontier.UCSFrontierNoClosed;
import mj.aastaar.datastructures.CustomPriorityQueue;
import mj.aastaar.map.Grid;

/**
 *
 * @author MJ
 */
public class UCSNoClosed extends BestFirstSearch {

    public UCSNoClosed(Grid grid) {
        super(grid);
    }

    @Override
    public void initFrontier() {
        int nx = grid.getLength();
        int ny = grid.getRowLength();
        CustomPriorityQueue cpq = new CustomPriorityQueue(nx * ny);
        UCSFrontierNoClosed frontier = new UCSFrontierNoClosed(cpq);
        super.frontier = frontier;
    }
}
