package mj.aastaar.map;

import java.util.ArrayList;

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
    public ArrayList<Node> getNeighbours(int x, int y, int directions) {
//        Node[] neighbours = new Node[directions];
        ArrayList<Node> neighbours = new ArrayList<>();

        if (x < (grid.length - 1) && isPassable(grid[x + 1][y])) {
            neighbours.add(new Node(x + 1, y));
        }
        if (x > 0 && isPassable(grid[x - 1][y])) {
            neighbours.add(new Node(x - 1, y));
        }
        if (y < (grid[0].length - 1) && isPassable(grid[x][y + 1])) {
            neighbours.add(new Node(x, y + 1));
        }
        if (y > 0 && isPassable(grid[x][y - 1])) {
            neighbours.add(new Node(x, y - 1));
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
