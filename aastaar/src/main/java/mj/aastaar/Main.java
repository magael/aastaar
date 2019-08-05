package mj.aastaar;

//import mj.aastaar.algorithms.AStar;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mj.aastaar.algorithms.BreadthFirstSearch;
import mj.aastaar.algorithms.Dijkstra;
import mj.aastaar.map.Grid;
import mj.aastaar.map.MapCreator;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class Main extends Application {

    private static Grid grid;

    // TODO: no magic numbers
    // arena
//    private static int startX = 4;
//    private static int startY = 32;
//    private static int goalX = 47;
//    private static int goalY = 19;
    private static int startX = 19;
    private static int startY = 26;
    private static int goalX = 19;
    private static int goalY = 29;

    // arena2
//    private static int startX = 99;
//    private static int startY = 159;
//    private static int goalX = 101;
//    private static int goalY = 162;'
    // Aftershock
//    private static int startX = 82;
//    private static int startY = 203;
//    private static int goalX = 78;
//    private static int goalY = 199;
    @Override
    public void start(Stage window) throws Exception {
        GridPane layout = new GridPane();

        char[][] grid2D = new char[0][0];
        if (grid != null && grid.getLength() > 0) {
            grid2D = grid.getGrid();
        } else {
            System.out.println("no grid");
        }
        if (grid2D != null) {
            Random random = new Random();
            for (int i = 0; i < grid2D.length - 1; i++) {
                for (int j = 0; j < grid2D[i].length - 1; j++) {
                    Color color = Color.rgb(125, 125, 125, random.nextDouble());
                    int tileSize = 10;
                    if (grid2D.length < 100 ) {
                        tileSize = 8;
                    } else if (grid2D.length < 200) {
                        tileSize = 4;
                    } else if (grid2D.length < 300) {
                        tileSize = 3;
                    } else if (grid2D.length < 400) {
                        tileSize = 2;
                    } else {
                        tileSize = 1;
                    }
                    layout.add(new Rectangle(tileSize, tileSize, color), i, j);
                }
            }
        } else {
            System.out.println("no grid2d");
        }

        Scene view = new Scene(layout);
        window.setScene(view);
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfig(null);

        if (grid != null && grid.getGrid() != null && grid.getLength() > 0) {
            System.out.println(grid);

            // BFS
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            Node start = new Node(startX, startY, 0.0);
            Node goal = new Node(goalX, goalY, 0.0);

            int pathLength = bfs.shortestPathLength(grid, start, goal, 4);
            System.out.println("BFS shortest path length: " + pathLength);

//            Node[] path = bfs.shortestPath(goal, start, pathLength);
//            System.out.println("BFS shortest path: ");
//            for (int i = 0; i < pathLength - 1; i++) {
//                System.out.println(path[i]);
//            }
            // Dijkstra
            Dijkstra dijkstra = new Dijkstra();
            dijkstra.shortestPath(grid, start, goal, 4);
            System.out.println("Dijkstra shortest path length: " + dijkstra.shortestPath(grid, start, goal, 4));

//            // A*
//            AStar astar = new AStar();
//            System.out.println(astar.shortestPathLength(grid, start, goal, 4));
            launch(Main.class);
        }
    }

    // create the pathfinding grid based on a specific map
    // TODO:
    // read config file
    // map list / collection (probably a separate class), where:
    // - read map paths from .cfg file to String[] mapFilePaths
    // - initialize an array of Grids
    // - for each line in mapFilePaths: add to grids
    // TODO 2: refactor into separate non-GUI class,
    // probably take mapCreator as param, return grid
    private static void initConfig(String configFilePath) {
        MapCreator mapCreator = new MapCreator();
        if (configFilePath == null) {
            //mapCreator.createMapFromFile("mapdata/dao-map/den308d.map");
            mapCreator.createMapFromFile("mapdata/sc1-map/Aftershock.map");
            if (mapCreator.getGrid() != null) {
                char[][] gridArray = mapCreator.getGrid();
                char[] impassable = {'T', 'W', '@'};
                grid = new Grid(gridArray, impassable);
            }
        }
    }
}
