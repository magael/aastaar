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

    public char[] getRow(int row) {
        return grid[row];
    }

    public int getLength() {
        return grid.length;
    }

    public void print() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    // TODO: get neighbours in specified directions (8 with diagonals, else 4)
    // currently only allows movement in 4 directions
    // NOTE: for large maps it might be optimal to use a custom arraylist implementation?
    public Node[] getNeighbours(int x, int y, int directions) {
        Node[] neighbours = new Node[directions];
        if (x < (grid.length - 1) && isPassable(grid[x + 1][y])) {
            neighbours[0] = new Node(x + 1, y);
        }
        if (x > 0 && isPassable(grid[x - 1][y])) {
            neighbours[1] = new Node(x - 1, y);
        }
        if (y < (grid[0].length - 1) && isPassable(grid[x][y + 1])) {
            neighbours[2] = new Node(x, y + 1);
        }
        if (y > 0 && isPassable(grid[x][y - 1])) {
            neighbours[3] = new Node(x, y - 1);
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
