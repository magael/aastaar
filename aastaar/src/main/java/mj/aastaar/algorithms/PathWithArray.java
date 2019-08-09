package mj.aastaar.algorithms;

import mj.aastaar.map.Node;

/**
 *
 * @author mijamija
 */
public class PathWithArray implements Path {

    //public HashMap<Node, Node> cameFrom;
    public Node[][] cameFrom;

    public PathWithArray(int nx, int ny) {
        //cameFrom = new HashMap<>();
        cameFrom = new Node[nx][ny];
    }

//    public HashMap<Node, Node> getCameFrom() {
//        return cameFrom;
//    }
    public Node[][] getCameFrom() {
        return cameFrom;
    }

    @Override
    public boolean containsNode(Node node) {
//        if (cameFrom.containsKey(node)) {
//            return true;
//        }
        if (cameFrom[node.getX()][node.getY()] != null) {
            return true;
        }
        return false;
    }

    @Override
    public void putCameFrom(Node to, Node from) {
//        cameFrom.put(to, from);
        cameFrom[to.getX()][to.getY()] = from;
    }

    // traces the steps back from goal to start,
    // returns the length of the shortest path
    public int earlyExit(Node current, Node start) {
        int steps = 0;
        while (!current.equals(start)) {
//            current = cameFrom.get(current);
            current = cameFrom[current.getX()][current.getY()];
            steps++;
        }
        return steps;
    }

    // returns the Nodes of the shortest path after the path and it's length are found
    // TODO: no separate shortestPlathLength and shortestPathLength methods,
    // always return the shortest path.
    // would still require 2 iterations of the path or a dynamic or a very large array
    @Override
    public Node[] shortestPath(Node goal, Node start, int length) {
        if (cameFrom == null || length < 1) {
            System.out.println("Path not found.");
            return null;
        }
        Node[] path = new Node[length];
        Node current = goal;
        int i = length - 1;
        while (i >= 0) {
            path[i] = current;
//            current = cameFrom.get(current);
            current = cameFrom[current.getX()][current.getY()];
            i--;
        }
        return path;
    }
}
