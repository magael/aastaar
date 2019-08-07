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

    public char[][] getGrid() {
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

    // TODO: different cost for shallow water than normal ground
    // and no cutting corners and cost of sqrt(2) for diagonal
    public double cost(Node from, Node to) {
//        char current = grid[from.getX()][to.getX()];
//        char next = grid[to.getX()][to.getY()];
        return 1.0;
    }

    // returns the adjacent nodes which are in bounds and passable
    // if also the current node is in bounds and passable
    // TODO: get neighbours in specified directions (8 with diagonals, else 4),
    // currently only allows movement in 4 directions
    // TODO: dynamic array
    // TODO: refactor inBounds checks
    // NOTE: it would be interesting to test precomputed neighbour lists / matrices
    // or hashtables vs this kind of dynamic check
    public Node[] getNeighbours(int x, int y, int directions) {
        Node[] neighbours = new Node[directions];
        if (x >= 0 && y >= 0 && x < grid.length && y < grid.length && isPassable(grid[x][y])) {
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
        }
        return neighbours;
    }

    private boolean isPassable(char c) {
        for (char ic : impassable) {
            if (c == ic) {
                return false;
            }
        }
        return true;
    }
}
