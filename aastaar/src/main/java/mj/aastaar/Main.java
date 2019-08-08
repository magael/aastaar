package mj.aastaar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mj.aastaar.algorithms.AStar;
import mj.aastaar.algorithms.BreadthFirstSearch;
import mj.aastaar.algorithms.DijkstraNoClosed;
import mj.aastaar.algorithms.DijkstraWithHashMap;
import mj.aastaar.algorithms.DijkstraWithArray;
import mj.aastaar.algorithms.PathFindingAlgorithm;
import mj.aastaar.map.Grid;
import mj.aastaar.map.MapCreator;
import mj.aastaar.map.Node;
import mj.aastaar.utils.InputHandler;

/**
 *
 * @author MJ
 */
public class Main extends Application {

    private static Grid grid;
    private static Node start, goal;
    private static Node[][] shortestPaths;
    private static Color[] pathColors;
    private static InputHandler inputHandler;

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
            // initialize arrays for the shortest paths of the different algorithms
            // to be improved
            shortestPaths = new Node[5][0];
            pathColors = new Color[5];

            // BFS
            runPathfindingAlgorithm(new BreadthFirstSearch(), "BFS", 0, Color.YELLOW);

            // Dijkstra
            runPathfindingAlgorithm(new DijkstraWithArray(), "Dijkstra with arrays", 1, Color.CYAN);
            runPathfindingAlgorithm(new DijkstraWithHashMap(), "Dijkstra with hash tables", 2, Color.CYAN);
            runPathfindingAlgorithm(new DijkstraNoClosed(), "Dijkstra with no closed set", 2, Color.CYAN);

            // A*
            runPathfindingAlgorithm(new AStar(), "A*", 4, Color.MAGENTA);

            // GUI
            launch(Main.class);
        }
    }

    @Override
    public void start(Stage window) throws Exception {
        GridPane layout = gridGUI();
        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    private static void runPathfindingAlgorithm(PathFindingAlgorithm algorithm, String name, int i, Color color) {
        System.out.println("Starting " + name);
        int pathLength = algorithm.search(grid, start, goal, 4);
        System.out.println(name + " shortest path "
                + "length: " + pathLength
                + ", cost: " + algorithm.getCost(goal));
        shortestPaths[i] = algorithm.getPath().shortestPath(goal, start, pathLength);
        System.out.println("Retrieved " + name + " shortest path as array \n");
        pathColors[i] = color;
    }

    // NOTE: GridPane.add receives coordinates in (..., column, row)
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
                layout.add(new Rectangle(tileSize, tileSize, color), j, i);
            }
        }

        // printing different paths found by different algroithms
        for (int i = 0; i < shortestPaths.length; i++) {
            Node[] path = shortestPaths[i];
            if (path == null) {
                continue;
            }
            for (int j = 0; j < path.length - 1; j++) {
                layout.add(new Rectangle(tileSize, tileSize, pathColors[i]), path[j].getY(), path[j].getX());
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
    // - or mayne init one grid at a time when user chooses a map
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
        // example maps
        //mapCreator.createMapFromFile("mapdata/dao-map/arena2.map");
        //mapCreator.createMapFromFile("mapdata/sc1-map/Aftershock.map");
        //mapCreator.createMapFromFile("mapdata/wc3maps512-map/tranquilpaths.map");
        // timbermawhold contains shallow water
        mapCreator.createMapFromFile("mapdata/wc3maps512-map/timbermawhold.map");
        if (mapCreator.getGrid() != null) {
            char[][] gridArray = mapCreator.getGrid();
            char[] impassable = {'T', 'W', '@'};
            grid = new Grid(gridArray, impassable);
        }
    }

    private static void initDefaultPositions() {
        // TODO: no magic numbers
        // example coordinates for different maps
        // Aftershock
//        int startX = 82;
//        int startY = 203;
//        int goalX = 78;
//        int goalY = 199;
        // Aftershock other:
//        int startX = 82;
//        int startY = 203;
//        int goalX = 400;
//        int goalY = 390;
        // arena
//        int startX = 4;
//        int startY = 32;
//        int goalX = 47;
//        int goalY = 19;
        // arena2
//        int startX = 84;
//        int startY = 106;
//        int goalX = 77;
//        int goalY = 85;
        // arena2 other
//        int startX = 104;
//        int startY = 44;
//        int goalX = 70;
//        int goalY = 190;
        // timbermawhold
        int startX = 70;
        int startY = 380;
        int goalX = 394;
        int goalY = 101;
        // tranquilpaths
//        int startX = 250;
//        int startY = 50;
//        int goalX = 250;
//        int goalY = 375;

        start = new Node(startX, startY, 0.0);
        goal = new Node(goalX, goalY, 0.0);
    }
}
