package mj.aastaar;

import java.math.BigDecimal;
import java.math.MathContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mj.aastaar.algorithms.AStar;
import mj.aastaar.algorithms.PathfindingAlgorithm;
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
     * paths. Launching the Java FX GUI and invoking performance tests.
     */
    private static void run() {
        scenario = new Scenario();
        scenario.initConfig();
        Grid grid = scenario.getGrid();
        
        if (scenario.getStart() == null || scenario.getGoal() == null) {
            System.out.println("Error initializing start and goal positions");
        } else if (grid == null || grid.getGrid2D() == null || grid.getLength() < 1) {
            System.out.println("Error creating a pathfinding grid");
        } else {
            String cyan = "#00FFFF";
            String magenta = "#FF00FF";
//            String[] pathColors = {cyan, magenta};
//            scenario.setPathColors(pathColors);
//            scenario.setShortestPaths(new Node[pathColors.length][]);
//
//            PathfindingAlgorithm[] algorithms = {new UniformCostSearch(grid), new AStar(grid)};
//            String[] algoNames = {"Dijkstra", "A*"};

//            String[] pathColors = {cyan};
//            scenario.setPathColors(pathColors);
//            scenario.setShortestPaths(new Node[pathColors.length][]);
//
//            PathfindingAlgorithm[] algorithms = {new UniformCostSearch(grid)};
//            String[] algoNames = {"Dijkstra"};
            String[] pathColors = {magenta};
            scenario.setPathColors(pathColors);
            scenario.setShortestPaths(new Node[pathColors.length][]);
            
            PathfindingAlgorithm[] algorithms = {new AStar(grid)};
            scenario.setAlgorithms(algorithms);
            String[] algoNames = {"A*"};
            scenario.setAlgoNames(algoNames);
            for (int i = 0; i < algorithms.length; i++) {
                scenario.runPathfindingAlgorithm(algorithms[i], algoNames[i], i);
            }
            
            System.out.println("Launching visualization, please wait...");
            System.out.println("Closing the window will begin performance testing.\n");
            
            launch(Main.class);

//            runPerformanceTests(algorithms, algoNames);
        }
    }
    
    @Override
    public void start(Stage window) throws Exception {
        double tileSize = 2.0;
        GridPane layout = gridGUI(tileSize);
        BorderPane toolBarPane = addToolBar(scenario.getGrid2D(), layout, tileSize);
        toolBarPane.setBottom(layout);
        ScrollPane scrollPane = new ScrollPane(toolBarPane);
        Scene scene = new Scene(scrollPane);
        
        window.setScene(scene);
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    /**
     * Using the performance tester class to test pathfinding speed. Setting the
     * number n, where n is the number of times the tests are run.
     *
     * @param algorithms The algorithms that are tested
     * @param algoNames The names of the algorithms that are
     */
    private static void runPerformanceTests(PathfindingAlgorithm[] algorithms, String[] algoNames) {
//        int[] nums = {10, 50, 100, 500, 1000};
        int[] nums = {10, 10, 20, 30, 50};
        PathfindingPerformanceTester tester = new PathfindingPerformanceTester(scenario);
        System.out.print("Beginning performance tests on the algorithms.\n");
        long t = System.nanoTime();
        tester.run(algorithms, algoNames, nums);
        BigDecimal elapsedTime = new BigDecimal((System.nanoTime() - t) / 1000000000);
        System.out.println(tester);
        System.out.println("Performance tests ran in a total of "
                + elapsedTime.round(new MathContext(3)) + " seconds.\n");
    }

    /**
     * Creating the grid visualization with JavaFX objects.
     *
     * NOTE: GridPane.add receives coordinates as (..., column, row)
     *
     * @return grid of colored rectangles, a.k.a. tiles, representing the map
     * and shortest paths
     */
    private GridPane gridGUI(double tileSize) {
        GridPane layout = new GridPane();
        char[][] grid2D = scenario.getGrid2D();
        
        addTiles(grid2D, layout, tileSize);
        colorStartAndGoal(layout, tileSize, Color.RED, Color.LAWNGREEN);
        colorExplored(layout, tileSize);
        colorPaths(layout, tileSize);
        
        return layout;
    }
    
    private void colorStartAndGoal(GridPane layout, double tileSize, Color startColor, Color goalColor) {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
        layout.add(new Rectangle(tileSize, tileSize, startColor), start.getY(), start.getX());
        layout.add(new Rectangle(tileSize, tileSize, goalColor), goal.getY(), goal.getX());
    }
    
    private BorderPane addToolBar(char[][] grid2D, GridPane layout, double tileSize) {
        BorderPane pane = new BorderPane();
        ToolBar toolbar = new ToolBar();
        Button randomPositionsButton = new Button("New random positions");
        
        randomPositionsButton.setOnAction(value -> {
            clickRandomPositions(grid2D, layout, tileSize);
        });
        
        toolbar.getItems().add(randomPositionsButton);
        pane.setTop(toolbar);
        return pane;
    }
    
    private void clickRandomPositions(char[][] grid2D, GridPane layout, double tileSize) {
        clearStartAndGoalColors(grid2D, layout, tileSize);
        scenario.initRandomPositions();
        scenario.runPathfindingAlgorithm(scenario.getAlgorithms()[0], scenario.getAlgoNames()[0], 0);
        colorStartAndGoal(layout, tileSize, Color.RED, Color.LAWNGREEN);
        System.out.println("click");
    }

    private void clearStartAndGoalColors(char[][] grid2D, GridPane layout, double tileSize) {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
        Color startColor = tileColor(grid2D[start.getX()][start.getY()]);
        Color goalColor = tileColor(grid2D[goal.getX()][goal.getY()]);
        colorStartAndGoal(layout, tileSize, startColor, goalColor);
    }

    /**
     * Adding map tiles to the GridPane. Red for the start, green for the goal
     * and calling the tileColor-method for the rest.
     *
     * @param grid2D 2D character array representation of the map grid
     * @param layout JavaFX GridPane object
     * @param tileSize Pixel dimensions for each tile
     */
    private void addTiles(char[][] grid2D, GridPane layout, double tileSize) {
        for (int i = 0; i < grid2D.length - 1; i++) {
            for (int j = 0; j < grid2D[i].length - 1; j++) {
//                Color color;
//                if (i == start.getX() && j == start.getY()) {
//                    color = Color.RED;
//                } else if (i == goal.getX() && j == goal.getY()) {
//                    color = Color.LAWNGREEN;
//                } else {
//                    color = tileColor(grid2D[i][j]);
//                }
                layout.add(new Rectangle(tileSize, tileSize, tileColor(grid2D[i][j])), j, i);
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
                layout.add(new Rectangle(tileSize, tileSize, color),
                        path[j].getY(), path[j].getX());
            }
        }
    }
    
    private void colorExplored(GridPane layout, double tileSize) {
        Node[][] cameFrom = scenario.getCameFrom();
        for (int i = 0; i < cameFrom.length; i++) {
            Node[] nodes = cameFrom[i];
            if (nodes == null) {
                continue;
            }
            for (int j = 0; j < nodes.length - 1; j++) {
                if (nodes[j] == null) {
                    continue;
                }
                Color exploredColor = Color.web("#564A30");
                layout.add(new Rectangle(tileSize, tileSize, exploredColor),
                        nodes[j].getY(), nodes[j].getX());
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
