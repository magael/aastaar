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
            if (i < 10) s += " ";
            for (int j = 0; j < grid[i].length; j++) {
                s += grid[i][j];
            }
            s += '\n';
        }
        return s;
    }

    // TODO: different cost for shallow water than normal ground
    // and no cutting corners and sqrt(2) for diagonal
    public double cost(Node from, Node to) {
//        char current = grid[from.getX()][to.getX()];
//        char next = grid[to.getX()][to.getY()];
        return 1.0;
    }

    // TODO: get neighbours in specified directions (8 with diagonals, else 4)
    // currently only allows movement in 4 directions
    // NOTE: for large maps it might be optimal to use a custom arraylist implementation?
    // NOTE: i'd like to test precomputed neighbour lists / hashtables vs this kind of dynamic check
    public Node[] getNeighbours(int x, int y, int directions) {
        Node[] neighbours = new Node[directions];
        if (x < (grid.length - 1) && isPassable(grid[x + 1][y])) {
            neighbours[0] = new Node(x + 1, y, 0);
        }
        if (x > 0 && isPassable(grid[x - 1][y])) {
            neighbours[1] = new Node(x - 1, y, 0);
        }
        if (y < (grid[0].length - 1) && isPassable(grid[x][y + 1])) {
            neighbours[2] = new Node(x, y + 1, 0);
        }
        if (y > 0 && isPassable(grid[x][y - 1])) {
            neighbours[3] = new Node(x, y - 1, 0);
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
