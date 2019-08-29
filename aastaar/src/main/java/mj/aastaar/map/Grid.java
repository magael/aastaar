package mj.aastaar.map;

/**
 * Pathfinding grid with characters representing terrain.
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
        if (x < (grid.length - 1) && isPassable(grid[x + 1][y]) && directions > 0) {
            neighbours[0] = new Node(x + 1, y, 1);
        }
        if (x > 0 && isPassable(grid[x - 1][y]) && directions > 1) {
            neighbours[1] = new Node(x - 1, y, 1);
        }
        if (y < (grid[0].length - 1) && isPassable(grid[x][y + 1]) && directions > 2) {
            neighbours[2] = new Node(x, y + 1, 1);
        }
        if (y > 0 && isPassable(grid[x][y - 1]) && directions > 3) {
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
    
    /**
     * Checking if a position on the map is within the grid bounds.
     * 
     * @param x The x-coordinate of the position of that is checked
     * @param y The y-coordinate of the position of that is checked
     * @return True if the position is in grid bounds, otherwise false
     */
    public boolean inBounds(int x, int y) {
        if (x < grid.length && x >= 0 && y < grid[0].length && y >= 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Checking if the current starting point is a valid location on the map.
     *
     * @param node The node that is checked
     * @return True if node is in bounds and passable, otherwise false
     */
    public boolean nodeIsValid(Node node) {
        int x = node.getX();
        int y = node.getY();
        if (inBounds(x, y) && isPassable(grid[x][y])) {
            return true;
        }
        return false;
    }
}
