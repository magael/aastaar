package mj.aastaar.algorithms;

import mj.aastaar.map.Node;

/**
 * A composition consisting of a pathfinding algorithm, it's visualization
 * components and information retrieved by the algorithm.
 *
 * @author User
 */
public class AlgorithmVisualization {

    private PathfindingAlgorithm algorithm;
    private String name;
    private String color;
    private Node[] shortestPath;
    private Object cameFrom;

    /**
     *
     * @param algorithm Pathfinding algorithm
     * @param name The name of the algorithm
     * @param color Hex representation of a color for visualizing the path
     */
    public AlgorithmVisualization(PathfindingAlgorithm algorithm, String name, String color) {
        this.algorithm = algorithm;
        this.name = name;
        this.color = color;
    }

    /**
     *
     * @return Pathfinding algorithm
     */
    public PathfindingAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     *
     * @return Hex representation of a color for visualizing the path
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return The name of the algorithm
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Links between nodes
     */
    public Node[][] getCameFrom() {
        return (Node[][]) cameFrom;
    }

    /**
     *
     * @return Nodes included in the shortest path found by the algorithm
     */
    public Node[] getShortestPath() {
        return shortestPath;
    }

    /**
     *
     * @param algorithm Pathfinding algorithm
     */
    public void setAlgorithm(PathfindingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     *
     * @param color Hex representation of a color for visualizing the path
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @param name The name of the algorithm
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param cameFrom Links between nodes
     */
    public void setCameFrom(Object cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     *
     * @param shortestPath Nodes included in the shortest path found by the algorithm
     */
    public void setShortestPath(Node[] shortestPath) {
        this.shortestPath = shortestPath;
    }
}
