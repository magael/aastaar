package mj.aastaar.map;

/**
 * A position on the grid, with a priority for pathfinding algorithms.
 * 
 * @author MJ
 */
public class Node {

    private int x, y;
    private double priority;

    /**
     *
     * @param x The x-coordinate of the position
     * @param y The y-coordinate of the position
     * @param priority The priority of the position for pathfinding algorithms
     */
    public Node(int x, int y, double priority) {
        this.x = x;
        this.y = y;
        this.priority = priority;
    }

    /**
     *
     * @return Priority
     */
    public double getPriority() {
        return priority;
    }

    /**
     *
     * @param priority Priority
     */
    public void setPriority(double priority) {
        this.priority = priority;
    }

    /**
     *
     * @param x The x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y The y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return The x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return The y-coordinate
     */
    public int getY() {
        return y;
    }
    
    @Override
    public int hashCode() {
        return (x * 18397) + (y * 20483);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Node other = (Node) obj;
        
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
}
