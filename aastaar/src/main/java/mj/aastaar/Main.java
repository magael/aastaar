package mj.aastaar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mj.aastaar.algorithms.AStar;
import mj.aastaar.algorithms.BreadthFirstSearch;
import mj.aastaar.algorithms.DijkstraNoClosed;
import mj.aastaar.algorithms.DijkstraWithHashMap;
import mj.aastaar.algorithms.DijkstraWithArray;
import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class Main extends Application {

    private static Scenario scenario;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        // initializing the map, start and goal
        scenario = new Scenario();
        scenario.initConfig(null);

        if (scenario.getStart() == null || scenario.getGoal() == null) {
            System.out.println("Error initializing start and goal positions");
        } else if (scenario.getGrid() == null || scenario.getGrid2D() == null || scenario.getGrid().getLength() < 1) {
            System.out.println("Error creating a pathfinding grid");
        } else {
            // initialize arrays for the shortest paths of the different algorithms
            // to be improved
            scenario.setShortestPaths(new Node[5][]);
            scenario.setPathColors(new String[5]);
                    
            scenario.runPathfindingAlgorithm(new BreadthFirstSearch(), "BFS", 0, "#FFFF00"); // color: yellow
            scenario.runPathfindingAlgorithm(new DijkstraWithArray(), "Dijkstra with arrays", 1, "#000FFF"); // color: blue
            scenario.runPathfindingAlgorithm(new DijkstraWithHashMap(), "Dijkstra with hash tables", 2, "#0FF000"); // color: green
            scenario.runPathfindingAlgorithm(new DijkstraNoClosed(), "Dijkstra with no closed set", 3, "#00FFFF"); // color: cyan
            scenario.runPathfindingAlgorithm(new AStar(), "A*", 4, "#FF00FF"); // color: magenta

            // GUI
            launch(Main.class);
        }
    }

    // called automatically after launching the JavaFX Application
    @Override
    public void start(Stage window) throws Exception {
        GridPane layout = gridGUI();
        ScrollPane sp = new ScrollPane(layout);
        Scene scene = new Scene(sp);

        window.setScene(scene);
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    // NOTE: GridPane.add receives coordinates as (..., column, row)
    private GridPane gridGUI() {
        GridPane layout = new GridPane();
        char[][] grid2D = scenario.getGrid2D();
        double tileSize = 2.0;

        addTiles(grid2D, layout, tileSize);
        colorPaths(layout, tileSize);

        return layout;
    }

    // creating the map tiles
    private void addTiles(char[][] grid2D, GridPane layout, double tileSize) {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
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
    }

    // coloring different paths found by different algroithms
    private void colorPaths(GridPane layout, double tileSize) {
        Node[][] shortestPaths = scenario.getShortestPaths();
        String[] pathColors = scenario.getPathColors();
        for (int i = 0; i < shortestPaths.length; i++) {
            Node[] path = shortestPaths[i];
            if (path == null) {
                continue;
            }
            Color color = Color.web(pathColors[i]);
            for (int j = 0; j < path.length - 1; j++) {
                layout.add(new Rectangle(tileSize, tileSize, color), path[j].getY(), path[j].getX());
            }
        }
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
}
