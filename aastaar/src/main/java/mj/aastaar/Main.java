package mj.aastaar;

import java.math.BigDecimal;
import java.math.MathContext;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private GraphicsContext pathGraphics;
    private String showExplored;

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
            String[] pathColors = {cyan, magenta};
            scenario.setPathColors(pathColors);
            scenario.setShortestPaths(new Node[pathColors.length][]);

            PathfindingAlgorithm[] algorithms = {new UniformCostSearch(grid), new AStar(grid)};
            scenario.setAlgorithms(algorithms);
            String[] algoNames = {"Dijkstra", "A*"};
            scenario.setAlgoNames(algoNames);
            for (int i = 0; i < algorithms.length; i++) {
                scenario.runPathfindingAlgorithm(algorithms[i], algoNames[i], i);
            }

            System.out.println("Launching visualization. "
                    + "Closing the window will begin performance testing.\n");
            launch(Main.class);

            runPerformanceTests(algorithms, algoNames);
        }
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

    @Override
    public void start(Stage window) throws Exception {
        double tileSize = 2.0;
        Grid grid = scenario.getGrid();

        Canvas pathCanvas = new Canvas(grid.getLength() * tileSize,
                grid.getRowLength() * tileSize);
        pathGraphics = pathCanvas.getGraphicsContext2D();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(tileCanvas(grid.getGrid2D(), tileSize));
        
        ToolBar toolbar = toolBar(tileSize);
        borderPane.setRight(toolbar);

        colorStartAndGoal(tileSize);
        colorPaths(tileSize);

        ScrollPane scrollPane = new ScrollPane(new Group(borderPane, pathCanvas));
        Scene scene = new Scene(scrollPane);

        window.setScene(scene);
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    /**
     * Creating a toolbar with legend and interactive UI elements.
     *
     * @param tileSize The map tile size
     * @return JavaFX ToolBar object
     */
    private ToolBar toolBar(double tileSize) {
        ToolBar toolbar = new ToolBar();
        toolbar.setOrientation(Orientation.VERTICAL);
        toolbar.setPadding(new Insets(20));
        toolbar.setBackground(new Background(new BackgroundFill(
                Color.web("#130d14"), null, Insets.EMPTY)));
        int fontSize = 14;

        algorithmsLegend(fontSize, toolbar);

        Label randomPositionsLabel = new Label("New random positions: ");
        randomPositionsLabel.setTextFill(Color.WHITE);
        randomPositionsLabel.setFont(new Font(fontSize));
        Button randomPositionsButton = new Button("Randomize");
        randomPositionsButton.setOnAction(value -> {
            clickRandomPositions(tileSize);
        });

        Label exploredLabel = new Label("Visualize explored nodes: ");
        exploredLabel.setTextFill(Color.WHITE);
        exploredLabel.setFont(new Font(fontSize));
        final String[] exploredCoices = {"None", scenario.getAlgoNames()[0],
            scenario.getAlgoNames()[1]};
        ChoiceBox exploredBox = exploredBox(exploredCoices, tileSize);

        Separator separator = separator();
        Separator separator2 = separator();

        toolbar.getItems().addAll(separator, randomPositionsLabel,
                randomPositionsButton, separator2, exploredLabel, exploredBox);
        return toolbar;
    }

    /**
     * Information on algorithms and colors used in the pathfinding
     * visualization.
     *
     * @param fontSize The font size used for the toolbar elements
     * @param toolbar
     */
    private void algorithmsLegend(int fontSize, ToolBar toolbar) {
        Label colorsLabel = new Label("Shortest paths: ");
        colorsLabel.setTextFill(Color.WHITE);
        colorsLabel.setFont(new Font(fontSize));
        toolbar.getItems().add(colorsLabel);

        for (int i = 0; i < scenario.getAlgorithms().length; i++) {
            Text colorsText = new Text(scenario.getAlgoNames()[i]);
            colorsText.setFill(Color.web(scenario.getPathColors()[i]));
            colorsText.setFont(Font.font(fontSize));
            toolbar.getItems().add(colorsText);
        }
    }

    /**
     * Menu box to select to show the nodes explored by zero or more algorithms
     *
     * @param exploredCoices Choices listed in the menu
     * @param tileSize The map tile size
     * @return JavaFX ChoiceBox object
     */
    private ChoiceBox exploredBox(final String[] exploredCoices, double tileSize) {
        ChoiceBox exploredBox = new ChoiceBox(FXCollections.observableArrayList(exploredCoices));
        exploredBox.setValue("None");
        exploredBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                clearExplored(tileSize);
                showExplored = exploredCoices[new_value.intValue()];
                if (!showExplored.equals("None")) {
                    colorExplored(tileSize);
                }
                colorStartAndGoal(tileSize);
                colorPaths(tileSize);
            }
        });
        return exploredBox;
    }

    /**
     * Creating a separator UI-element
     *
     * @return JavaFX Separator object
     */
    private Separator separator() {
        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setPadding(new Insets(10));
        separator.setOpacity(0.5);
        return separator;
    }

    /**
     * Handling the user clicking the "New random positions"-button.
     *
     * @param tileSize The map tile size
     */
    private void clickRandomPositions(double tileSize) {
        clearStartAndGoalColors(tileSize);
        clearPaths(tileSize);
        clearExplored(tileSize);
        scenario.initRandomPositions();
        PathfindingAlgorithm[] algorithms = scenario.getAlgorithms();
        String[] algoNames = scenario.getAlgoNames();
        for (int i = 0; i < algorithms.length; i++) {
            scenario.runPathfindingAlgorithm(algorithms[i], algoNames[i], i);
        }

        if (showExplored != null && !showExplored.equals("None")) {
            colorExplored(tileSize);
        }
        colorStartAndGoal(tileSize);
        colorPaths(tileSize);
    }

    /**
     * Coloring the start and goal nodes.
     *
     * @param tileSize The map tile size
     */
    private void colorStartAndGoal(double tileSize) {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
        Color startColor = Color.RED;
        Color goalColor = Color.LAWNGREEN;

        pathGraphics.setFill(startColor);
        pathGraphics.fillRect((int) (start.getY() * tileSize), (int) (start.getX() * tileSize), tileSize, tileSize);
        pathGraphics.setFill(goalColor);
        pathGraphics.fillRect((int) (goal.getY() * tileSize), (int) (goal.getX() * tileSize), tileSize, tileSize);
    }

    /**
     * Clearing the start and goal colors.
     *
     * @param tileSize The map tile size
     */
    private void clearStartAndGoalColors(double tileSize) {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
        pathGraphics.clearRect((int) (start.getY() * tileSize), (int) (start.getX() * tileSize), tileSize, tileSize);
        pathGraphics.clearRect((int) (goal.getY() * tileSize), (int) (goal.getX() * tileSize), tileSize, tileSize);
    }

    /**
     * Visualizing the map tiles. Coordinates -1 hack to fix offset between
     * canvases.
     *
     * @param grid2D The map grid as a character array
     * @param tileSize Pixel dimensions for each tile
     * @return
     */
    private Canvas tileCanvas(char[][] grid2D, double tileSize) {
        Canvas tileCanvas = new Canvas(grid2D.length * tileSize, grid2D[0].length * tileSize);
        GraphicsContext tileGC = tileCanvas.getGraphicsContext2D();
        for (int i = 0; i < grid2D.length - 1; i++) {
            for (int j = 0; j < grid2D[i].length - 1; j++) {
                tileGC.setFill(tileColor(grid2D[i][j]));
                tileGC.fillRect((int) (j * tileSize) - 1, (int) (i * tileSize) - 1, tileSize, tileSize);
            }
        }
        return tileCanvas;
    }

    /**
     * Coloring different paths found by different algorithms.
     *
     * @param layout JavaFX GridPane object
     * @param tileSize The map tile size
     */
    private void colorPaths(double tileSize) {
        Node[][] shortestPaths = scenario.getShortestPaths();
        String[] pathColors = scenario.getPathColors();
        for (int i = 0; i < shortestPaths.length; i++) {
            Node[] path = shortestPaths[i];
            if (path == null) {
                continue;
            }
            pathGraphics.setFill(Color.web(pathColors[i]));
            for (int j = 0; j < path.length - 1; j++) {
                pathGraphics.fillRect((int) (path[j].getY() * tileSize), (int) (path[j].getX() * tileSize), tileSize, tileSize);
            }
        }
    }

    /**
     * Clearing the path colors.
     *
     * @param tileSize The map tile size
     */
    private void clearPaths(double tileSize) {
        Node[][] shortestPaths = scenario.getShortestPaths();
        for (int i = 0; i < shortestPaths.length; i++) {
            Node[] path = shortestPaths[i];
            if (path == null) {
                continue;
            }
            for (int j = 0; j < path.length - 1; j++) {
                pathGraphics.clearRect((int) (path[j].getY() * tileSize), (int) (path[j].getX() * tileSize), tileSize, tileSize);
            }
        }
    }

    /**
     * Coloring the nodes explored by the pathfinding algorithms.
     *
     * @param tileSize The map tile size
     */
    private void colorExplored(double tileSize) {
        Node[][] cameFrom = scenario.getCameFrom(showExplored);
        for (int i = 0; i < cameFrom.length; i++) {
            Node[] nodes = cameFrom[i];
            if (nodes == null) {
                continue;
            }
            for (int j = 0; j < nodes.length - 1; j++) {
                if (nodes[j] == null) {
                    continue;
                }

                pathGraphics.setFill(Color.web("#C5C3DA"));
                pathGraphics.fillRect((int) (nodes[j].getY() * tileSize), (int) (nodes[j].getX() * tileSize), tileSize, tileSize);
            }
        }
    }

    /**
     * Clearing the nodes explored by the pathfinding algorithms.
     *
     * @param tileSize The map tile size
     */
    private void clearExplored(double tileSize) {
        Node[][] cameFrom = scenario.getCameFrom(showExplored);
        for (int i = 0; i < cameFrom.length; i++) {
            Node[] nodes = cameFrom[i];
            if (nodes == null) {
                continue;
            }
            for (int j = 0; j < nodes.length - 1; j++) {
                if (nodes[j] == null) {
                    continue;
                }
                pathGraphics.clearRect((int) (nodes[j].getY() * tileSize), (int) (nodes[j].getX() * tileSize), tileSize, tileSize);
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
