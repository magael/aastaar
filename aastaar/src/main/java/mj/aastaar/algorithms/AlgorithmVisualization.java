
package mj.aastaar.algorithms;

import mj.aastaar.map.Node;

/**
 *
 * @author User
 */
public class AlgorithmVisualization {
    
    private PathfindingAlgorithm algorithm;
    private String name;
    private String color;
    private Node[] shortestPath;
    private Node[][] cameFrom;

    /**
     *
     * @param algorithm
     * @param name
     * @param color
     */
    public AlgorithmVisualization(PathfindingAlgorithm algorithm, String name, String color) {
        this.algorithm = algorithm;
        this.name = name;
        this.color = color;
    }

    /**
     *
     * @return
     */
    public PathfindingAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     *
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Node[][] getCameFrom() {
        return cameFrom;
    }

    /**
     *
     * @return
     */
    public Node[] getShortestPath() {
        return shortestPath;
    }

    /**
     *
     * @param algorithm
     */
    public void setAlgorithm(PathfindingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param cameFrom
     */
    public void setCameFrom(Node[][] cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     *
     * @param shortestPath
     */
    public void setShortestPath(Node[] shortestPath) {
        this.shortestPath = shortestPath;
    }
}
