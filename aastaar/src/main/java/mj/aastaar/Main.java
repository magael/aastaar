package mj.aastaar;

//import mj.aastaar.algorithms.AStar;
import mj.aastaar.algorithms.BreadthFirstSearch;
import mj.aastaar.algorithms.Dijkstra;
import mj.aastaar.map.Grid;
import mj.aastaar.map.MapCreator;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class Main {

    private static Grid grid;
    // TODO: no magic numbers
    private static int startX = 4;
    private static int startY = 32;
    private static int goalX = 47;
    private static int goalY = 19;
//    private static int startX = 19;
//    private static int startY = 26;
//    private static int goalX = 19;
//    private static int goalY = 29;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfig(null);
        if (grid.getGrid() != null && grid.getLength() > 0) {
            System.out.println(grid);
            // BFS
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            Node start = new Node(startX, startY, 0.0);
            Node goal = new Node(goalX, goalY, 0.0);
            System.out.println(bfs.shortestPath(grid, start, goal, 4));
            // Dijkstra
            Dijkstra dijkstra = new Dijkstra();
            dijkstra.shortestPath(grid, start, goal, 4);
            System.out.println(dijkstra.shortestPath(grid, start, goal, 4));
            // A*
//            AStar astar = new AStar();
//            System.out.println(astar.shortestPath(grid, start, goal, 4));
        }
    }

    // create the pathfinding grid based on a specific map
    // TODO:
    // read config file
    // map list / collection (probably a separate class), where:
    // - read map paths from .cfg file to String[] mapFilePaths
    // - initialize an array of Grids
    // - for each line in mapFilePaths: add to grids
    private static void initConfig(String configFilePath) {
        MapCreator mapCreator = new MapCreator();
        if (configFilePath == null) {
            mapCreator.createMapFromFile("mapdata/dao-map/arena.map");
            char[][] gridArray = mapCreator.getGrid();
            char[] impassable = {'T', 'W', '@'};
            grid = new Grid(gridArray, impassable);
        }
    }
}
