package mj.aastaar.map;

/**
 * 
 * @author MJ
 */
public class Grid {

    private char[][] grid;
    private char[] impassable;
    private double heavyEdgeWeight;

    /**
     *
     * @param grid A 2D character array representation of the map grid
     * @param impassable character representations of map nodes
     * that are marked impassable for pathfinding
     * @param heavyEdgeWeight and the penalty for moving through heavier terrain
     */
    public Grid(char[][] grid, char[] impassable, double heavyEdgeWeight) {
        this.grid = grid;
        this.impassable = impassable;
        this.heavyEdgeWeight = heavyEdgeWeight;
    }

    /**
     *
     * @return A 2D character array representation of the map grid
     */
    public char[][] getGrid2D() {
        return grid;
    }

    /**
     *
     * @return The grid length
     */
    public int getLength() {
        return grid.length;
    }
    

    /**
     * Assumes all the rows have equal length.
     * 
     * @return The length of the first row in the grid array
     */
    public int getRowLength() {
        return grid[0].length;
    }

//    @Override
//    public String toString() {
//        String s = "";
//        for (int i = 0; i < grid.length; i++) {
//            s += Integer.toString(i) + ". ";
//            if (i < 10) {
//                s += " ";
//            }
//            for (int j = 0; j < grid[i].length; j++) {
//                s += grid[i][j];
//            }
//            s += '\n';
//        }
//        return s;
//    }

    /**
     * Manhattan distance on a square grid,
     * with a custom of implementation of calculating absolute value.
     *
     * @param a One of the nodes involved in the calculation
     * @param b The other node involved in the calculation
     * @return The Manhattan distance.
     */
    public double heuristic(Node a, Node b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        x = (x > 0) ? x : 0 - x;
        y = (y > 0) ? y : 0 - y;
        return x + y;
    }


    /**
     * Different cost for shallow water than normal ground.
     * Should only be called for nodes that have already been checked
     * as passable and in bounds.
     * Different edge weights yield different paths.
     *
     * @param from The position from which the movement is occurring.
     * @param to The position to which the movement is heading.
     * @return The cost of the movement.
     */
    public double cost(Node from, Node to) {
        double cost = 1.0;
        char current = grid[from.getX()][from.getY()];
        char next = grid[to.getX()][to.getY()];
        if (current == 'S') {
            cost += heavyEdgeWeight;
        }
        if (next == 'S') {
            cost += heavyEdgeWeight;
        }
        return cost;
    }

    /**
     * Checking and retrieving the adjacent nodes in the grid,
     * which are in bounds and passable.
     * Currently only allows movement in four directions.
     *
     * @param x X-coordinate of the position of which neighbours are requested
     * @param y Y-coordinate of the position of which neighbours are requested
     * @param directions In how many directions are neighbours requested
     * @return Array of Nodes which are in bounds, passable and adjacent
     * to the provided position
     */
    public Node[] getNeighbours(int x, int y, int directions) {
        Node[] neighbours = new Node[directions];
        if (x < (grid.length - 1) && isPassable(grid[x + 1][y])) {
            neighbours[0] = new Node(x + 1, y, 1);
        }
        if (x > 0 && isPassable(grid[x - 1][y])) {
            neighbours[1] = new Node(x - 1, y, 1);
        }
        if (y < (grid[0].length - 1) && isPassable(grid[x][y + 1])) {
            neighbours[2] = new Node(x, y + 1, 1);
        }
        if (y > 0 && isPassable(grid[x][y - 1])) {
            neighbours[3] = new Node(x, y - 1, 1);
        }
        return neighbours;
    }

    /**
     * Checking if a position on the map is passable.
     * 
     * @param c Character representation of the map position.
     * @return True if the position is passable, otherwise false
     */
    public boolean isPassable(char c) {
        for (char ic : impassable) {
            if (c == ic) {
                return false;
            }
        }
        return true;
    }
}
