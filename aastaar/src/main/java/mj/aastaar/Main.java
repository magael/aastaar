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
    private static Node start, goal;

    public static void main(String[] args) {
        run();
    }

    // TODO: refactor out method runAlgorithm(algorithm)
    private static void run() {
        initConfig(null);

        if (start == null || goal == null) {
            System.out.println("Error initializing start and goal positions");
        } else if (grid == null || grid.getGrid() == null || grid.getLength() < 1) {
            System.out.println("Error creating a pathfinding grid");
        } else {
            //            System.out.println(grid);

            // BFS
//            System.out.println("Starting BFS");
//            BreadthFirstSearch bfs = new BreadthFirstSearch();
//
//            int pathLength = bfs.shortestPathLength(grid, start, goal, 4);
//            System.out.println("BFS shortest path length: " + pathLength);
            // testing separate shortest path method
//            Node[] path = bfs.shortestPath(goal, start, pathLength);
//            System.out.println("BFS shortest path: ");
//            for (int i = 0; i < pathLength - 1; i++) {
//                System.out.println(path[i]);
//            }
            // Dijkstra
//            System.out.println("Starting Dijkstra");
//            Dijkstra dijkstra = new Dijkstra();
//            dijkstra.shortestPath(grid, start, goal, 4);
//            int pathLength2 = dijkstra.shortestPath(grid, start, goal, 4);
//            System.out.println("Dijkstra shortest path length: " + pathLength2);
            // GUI
            launch(Main.class);
        }
    }

    @Override
    public void start(Stage window) throws Exception {
        GridPane layout = gridGUI();
        Scene view = new Scene(layout);
        window.setScene(view);
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    private GridPane gridGUI() {
        GridPane layout = new GridPane();
        char[][] grid2D = grid.getGrid();
        double tileSize = 2.0;

        for (int i = 0; i < grid2D.length - 1; i++) {
            for (int j = 0; j < grid2D[i].length - 1; j++) {
                Color color;
                if (i == start.getX() && j == start.getY()) {
                    color = Color.RED;
                } else if (i == goal.getX() && j == goal.getY()) {
                    color = Color.LAWNGREEN;
                } else {
                    color = tileColor(grid2D[i][j]);
                }

                layout.add(new Rectangle(tileSize, tileSize, color), i, j);
            }
        }
        return layout;
    }

    // determine color for map tiles
    private Color tileColor(char c) {
        Color color = Color.RED;
        switch (c) {
            case '.':
                color = Color.web("#c49858");
                break;
            case 'T':
                color = Color.web("#005c32");
                break;
            case '@':
                color = Color.web("#130d14");
                break;
            case 'W':
                color = Color.web("#066b97");
                break;
            case 'S':
                color = Color.web("#658278");
                break;
            default:
                break;
        }
        return color;
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
        if (configFilePath == null) {
            initDefaultGrid();
            initDefaultPositions();
        }
    }

    private static void initDefaultGrid() {
        MapCreator mapCreator = new MapCreator();
        //mapCreator.createMapFromFile("mapdata/dao-map/arena2.map");
        mapCreator.createMapFromFile("mapdata/sc1-map/Aftershock.map");
        //mapCreator.createMapFromFile("mapdata/bg512-map/AR0011SR.map");
        //mapCreator.createMapFromFile("mapdata/wc3maps512-map/timbermawhold.map");
        if (mapCreator.getGrid() != null) {
            char[][] gridArray = mapCreator.getGrid();
            char[] impassable = {'T', 'W', '@'};
            grid = new Grid(gridArray, impassable);
        }
    }

    private static void initDefaultPositions() {
        // TODO: no magic numbers
        // Aftershock
        int startX = 82;
        int startY = 203;
        int goalX = 78;
        int goalY = 199;
        start = new Node(startX, startY, 0.0);
        goal = new Node(goalX, goalY, 0.0);
    }
}
