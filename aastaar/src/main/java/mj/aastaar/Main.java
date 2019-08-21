package mj.aastaar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mj.aastaar.algorithms.AStar;
import mj.aastaar.algorithms.UniformCostSearch;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;
import mj.aastaar.utils.PathfindingPerformanceTester;

/**
 * Initializing a pathfinding scenario, and a Java FX graphical user interface
 * for visualizing pathfinding maps and algorithms.
 *
 * @author MJ
 */
public class Main extends Application {

    private static Scenario scenario;

    /**
     * The main program.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Initializing the scenario from configurations, providing the scenario
     * with algorithms to run and providing arrays for the algorithm's shortest
     * paths. Launching the Java FX GUI.
     */
    private static void run() {
        scenario = new Scenario();
        scenario.initConfig();
        Grid grid = scenario.getGrid();

        if (scenario.getStart() == null || scenario.getGoal() == null) {
            System.out.println("Error initializing start and goal positions");
        } else if (grid == null || scenario.getGrid2D() == null || scenario.getGrid().getLength() < 1) {
            System.out.println("Error creating a pathfinding grid");
        } else {
            scenario.setShortestPaths(new Node[2][]);
            scenario.setPathColors(new String[2]);
            String cyan = "#00FFFF";
            String magenta = "#FF00FF";

            scenario.runPathfindingAlgorithm(new UniformCostSearch(grid), "Dijkstra", 0, cyan);
            scenario.runPathfindingAlgorithm(new AStar(grid), "A*", 1, magenta);

            System.out.println("Launching visualization, please wait...");
            launch(Main.class);

            // Testing performance
//            PathfindingPerformanceTester tester = new PathfindingPerformanceTester();
//            tester.runPathfindingAlgorithm(new AStar(grid), "A*");
        }
    }

    @Override
    public void start(Stage window) throws Exception {
        GridPane layout = gridGUI();
        ScrollPane scrollPane = new ScrollPane(layout);
        Scene scene = new Scene(scrollPane);

        window.setScene(scene);
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    /**
     * Creating the grid visualization with JavaFX objects.
     *
     * NOTE: GridPane.add receives coordinates as (..., column, row)
     *
     * @return grid of colored rectangles, a.k.a. tiles, representing the map
     * and shortest paths
     */
    private GridPane gridGUI() {
        GridPane layout = new GridPane();
        char[][] grid2D = scenario.getGrid2D();
        double tileSize = 2.0;

        addTiles(grid2D, layout, tileSize);
        colorPaths(layout, tileSize);

        return layout;
    }

    /**
     * Adding map tiles to the GridPane. Green for the start, red for the goal
     * and calling the tileColor-method for the rest.
     *
     * @param grid2D 2D character array representation of the map grid
     * @param layout JavaFX GridPane object
     * @param tileSize Pixel dimensions for each tile
     */
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

    /**
     * Coloring different paths found by different algorithms.
     *
     * @param layout JavaFX GridPane object
     * @param tileSize Pixel dimensions for each tile
     */
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

    /**
     * Determine the color for a map tile.
     *
     * @param c Character representation of the map grid node
     * @return JavaFX Color object
     */
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
