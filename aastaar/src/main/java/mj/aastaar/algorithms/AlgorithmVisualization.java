
package mj.aastaar.algorithms;

import mj.aastaar.map.Node;

public class AlgorithmVisualization {
    
    private PathfindingAlgorithm algorithm;
    private String name;
    private String color;
    private Node[] shortestPath;
    private Node[][] cameFrom;

    public AlgorithmVisualization(PathfindingAlgorithm algorithm, String name, String color) {
        this.algorithm = algorithm;
        this.name = name;
        this.color = color;
    }

    public PathfindingAlgorithm getAlgorithm() {
        return algorithm;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public Node[][] getCameFrom() {
        return cameFrom;
    }

    public Node[] getShortestPath() {
        return shortestPath;
    }

    public void setAlgorithm(PathfindingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCameFrom(Node[][] cameFrom) {
        this.cameFrom = cameFrom;
    }

    public void setShortestPath(Node[] shortestPath) {
        this.shortestPath = shortestPath;
    }
}
