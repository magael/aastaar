package mj.aastaar.map;

/**
 *
 * @author MJ
 */
public class Node implements Comparable<Node>{

    private int x, y;
    private double priority;

    /**
     *
     * @param x
     * @param y
     * @param priority
     */
    public Node(int x, int y, double priority) {
        this.x = x;
        this.y = y;
        this.priority = priority;
    }

    /**
     *
     * @return
     */
    public double getPriority() {
        return priority;
    }

    /**
     *
     * @param priority
     */
    public void setPriority(double priority) {
        this.priority = priority;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
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

    @Override
    public int compareTo(Node o) {
        if (this.priority > o.priority) return 1;
        if (this.priority < o.priority) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
