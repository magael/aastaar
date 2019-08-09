package mj.aastaar.map;

/**
 *
 * @author MJ
 */
public class Grid {

    private char[][] grid;
    private char[] impassable;

    public Grid(char[][] grid, char[] impassable) {
        this.grid = grid;
        this.impassable = impassable;
    }

    public char[][] getGrid2D() {
        return grid;
    }

    public int getLength() {
        return grid.length;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < grid.length; i++) {
            s += Integer.toString(i) + ". ";
            if (i < 10) {
                s += " ";
            }
            for (int j = 0; j < grid[i].length; j++) {
                s += grid[i][j];
            }
            s += '\n';
        }
        return s;
    }

    // manhattan distance on a square grid
    // with a custom of implementation calculating absolute value
    // TODO: octile distance for diagonal movement
    public double heuristic(Node a, Node b) {
        double x = a.getX() - b.getX();
        x = (x > 0) ? x : 0 - x;
        double y = a.getY() - b.getY();
        y = (y > 0) ? y : 0 - y;
        return x + y;
    }

    // different cost for shallow water than normal ground
    // NOTE: should only be called for nodes that have already been checked
    // as passable and in bounds
    // NOTE: different "shallow water"-penalties yield different paths
    public double cost(Node from, Node to) {
        double cost = 1.0;
        char current = grid[from.getX()][from.getY()];
        char next = grid[to.getX()][to.getY()];
        if (current == 'S') {
            cost += 2.0;
        }
        if (next == 'S') {
            cost += 2.0;
        }
        return cost;
    }

    // returns the adjacent nodes which are in bounds and passable
    // if also the current node is in bounds and passable
    // (checked in case starting node is out of bounds)
    // TODO: directions which are specified in coordinate arrays,
    // currently only allows movement in 4 directions (horizontal, vertical),
    // no cutting corners for diagonal movement
    // TODO: refactor inBounds checks
    // NOTE: it would be interesting to test precomputed neighbour lists / matrices
    // or hashtables vs this kind of dynamic check
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

    public boolean isPassable(char c) {
        for (char ic : impassable) {
            if (c == ic) {
                return false;
            }
        }
        return true;
    }
}
