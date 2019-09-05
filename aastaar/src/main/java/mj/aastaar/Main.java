package mj.aastaar;

import java.math.BigDecimal;
import java.math.MathContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mj.aastaar.algorithms.AStarWithArray;
import mj.aastaar.algorithms.AlgorithmVisualization;
import mj.aastaar.algorithms.DijkstraWithHashMap;
import mj.aastaar.algorithms.DijkstraWithArray;
import mj.aastaar.algorithms.DijkstraWithJavaHashMap;
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
    private int showExplored;
    private double tileSize;

    /**
     * The main program.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Initializing the scenario and maps. Initializing the pathfinding
     * algorithms and launching the Java FX GUI.
     */
    private static void run() {
        scenario = new Scenario();
        initMaps();
        scenario.initRandomPositions();
        Grid grid = scenario.getGrid();

        if (scenario.getStart() == null || scenario.getGoal() == null) {
            System.out.println("Error initializing start and goal positions");
        } else if (grid == null || grid.getGrid2D() == null || grid.getLength() < 1) {
            System.out.println("Error creating a pathfinding grid");
        } else {
            initAlgorithms(grid);
            launch(Main.class);
        }
    }

    /**
     * Initialize the maps, impassable characters and edge weights for passing
     * through heavier terrain.
     */
    private static void initMaps() {
        String[] mapPaths = {"mapdata/sc1-map/Rosewood.map",
            "mapdata/sc1-map/Aftershock.map",
            "mapdata/sc1-map/Legacy.map",
            "mapdata/wc3maps512-map/divideandconquer.map",
            "mapdata/wc3maps512-map/timbermawhold.map",
            "mapdata/wc3maps512-map/bootybay.map"};
        char[] impassable = {'T', 'W', '@'};
        double heavyEdgeWeight = 2.0;
        scenario.initGrids(mapPaths, impassable, heavyEdgeWeight);
    }

    /**
     * Initialize the algorithms and their visualization components.
     *
     * @param grid
     */
    private static void initAlgorithms(Grid grid) {
        String cyan = "#00FFFF";
        String magenta = "#FF00FF";
        AlgorithmVisualization dijkstra = new AlgorithmVisualization(new DijkstraWithArray(grid), "Dijkstra w/ 2D-array", cyan);
        AlgorithmVisualization dijkstraWHM = new AlgorithmVisualization(new DijkstraWithHashMap(grid), "Dijkstra w/ hash table", magenta);
        AlgorithmVisualization dijkstraWJava = new AlgorithmVisualization(new DijkstraWithJavaHashMap(grid), "Dijkstra w/ Java hash'", "#00FF00");
//        AlgorithmVisualization aStar = new AlgorithmVisualization(new AStar(grid), "A*", magenta);
        AlgorithmVisualization[] algorithmVisuals = {dijkstra, dijkstraWHM, dijkstraWJava};
        scenario.setAlgorithmVisuals(algorithmVisuals);

        for (int i = 0; i < algorithmVisuals.length; i++) {
            scenario.runPathfindingAlgorithm(algorithmVisuals[i]);
        }
    }

    /**
     * Using the performance tester class to test pathfinding speed. Setting the
     * number of times the tests are run.
     *
     * @param algorithms The algorithms that are tested
     * @param algoNames The names of the algorithms that are
     */
    private static String runPerformanceTests(AlgorithmVisualization[] algoVisuals) {
        int[] nums = {10, 10, 20};
        PathfindingPerformanceTester tester = new PathfindingPerformanceTester(scenario);
        long t = System.nanoTime();
        tester.run(nums);
        BigDecimal elapsedTime = new BigDecimal((System.nanoTime() - t) / 1000000000);

        String testResults = tester.toString() + "\nPerformance tests ran\nin a total of "
                + elapsedTime.round(new MathContext(3)) + " seconds.";
        return testResults;
    }

    @Override
    public void start(Stage window) throws Exception {
        tileSize = 1.0;
        Scene scene = initScene(window, scenario.getGrid());
        window.setScene(scene);
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        window.setTitle("Pathfinding visualization on game maps");
        window.show();
    }

    /**
     *
     * @param size The map tile size
     */
    public void setTileSize(double size) {
        tileSize = size;
    }

    /**
     * Initialize a new scene with the given grid.
     *
     * @param window JavaFX stage component, where the scene is to be set
     * @param grid Pathfinding grid
     * @return JavaFX Scene object
     */
    private Scene initScene(Stage window, Grid grid) {
        Canvas pathCanvas = new Canvas((int) grid.getLength() * tileSize, (int) grid.getRowLength() * tileSize);
        pathGraphics = pathCanvas.getGraphicsContext2D();

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(
                Color.web("#130d14"), null, Insets.EMPTY)));
        StackPane sp = new StackPane(tileCanvas(grid.getGrid2D()));
        sp.getChildren().add(pathCanvas);
        borderPane.setCenter(sp);
        ToolBar toolbar = toolBar(window);
        borderPane.setRight(toolbar);

        colorPaths();
        colorStartAndGoal();

        ScrollPane scrollPane = new ScrollPane(borderPane);
        Scene scene = new Scene(scrollPane);
        return scene;
    }

    /**
     * Creating a toolbar with legend and interactive UI elements.
     *
     * @return JavaFX ToolBar object
     */
    private ToolBar toolBar(Stage window) {
        ToolBar toolbar = new ToolBar();
        toolbar.setOrientation(Orientation.VERTICAL);
        toolbar.setPadding(new Insets(15));
        toolbar.setBackground(new Background(new BackgroundFill(
                Color.web("#130d14"), null, Insets.EMPTY)));
        int fontSize = 14;
        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();

        addAlgorithmsLegend(fontSize, toolbar);

        VBox pathLengthTextBox = new VBox();
        pathLengthTextBox.setPadding(new Insets(5));
        VBox pathCostTextBox = new VBox();
        pathCostTextBox.setPadding(new Insets(5));
        HBox pathTexts = new HBox();
        Text[] pathLengthTexts = new Text[algoVisuals.length];
        Text[] pathCostTexts = new Text[algoVisuals.length];
        for (int i = 0; i < algoVisuals.length; i++) {
            updatePathTexts(pathLengthTexts, i, algoVisuals, pathCostTexts);
            pathLengthTextBox.getChildren().add(pathLengthTexts[i]);
            pathCostTextBox.getChildren().add(pathCostTexts[i]);
        }
        pathTexts.getChildren().addAll(pathLengthTextBox, pathCostTextBox);

        Label startPositionLabel = new Label("Start position x, y");
        startPositionLabel.setTextFill(Color.WHITE);
        startPositionLabel.setFont(new Font(fontSize));
        Label goalPositionLabel = new Label("Goal position x, y");
        goalPositionLabel.setTextFill(Color.WHITE);
        goalPositionLabel.setFont(new Font(fontSize));

        TextField startXField = new TextField(Integer.toString(scenario.getStart().getX()));
        startXField.setMaxWidth(50);
        TextField startYField = new TextField(Integer.toString(scenario.getStart().getY()));
        startYField.setMaxWidth(50);
        HBox startTextFields = new HBox();
        startTextFields.getChildren().addAll(startXField, startYField);
        TextField goalXField = new TextField(Integer.toString(scenario.getGoal().getX()));
        goalXField.setMaxWidth(50);
        TextField goalYField = new TextField(Integer.toString(scenario.getGoal().getY()));
        goalYField.setMaxWidth(50);
        HBox goalTextFields = new HBox();
        goalTextFields.getChildren().addAll(goalXField, goalYField);
        Label invalidPositionLabel = new Label("");
        invalidPositionLabel.setTextFill(Color.RED);
        invalidPositionLabel.setFont(new Font(fontSize));
        Button newPositionsButton = new Button("New positions");
        newPositionsButton.setOnAction(value -> {
            Node start = new Node(Integer.parseInt(startXField.getText()), Integer.parseInt(startYField.getText()), 0);
            Node goal = new Node(Integer.parseInt(goalXField.getText()), Integer.parseInt(goalYField.getText()), 0);
            Grid grid = scenario.getGrid();
            clickNewPositions(start, goal, invalidPositionLabel, pathLengthTexts, pathCostTexts);
        });

        Label exploredLabel = new Label("Visualize explored nodes: ");
        exploredLabel.setTextFill(Color.WHITE);
        exploredLabel.setFont(new Font(fontSize));
        String[] exploredCoices = new String[algoVisuals.length + 1];
        exploredCoices[0] = "None";
        for (int i = 0; i < algoVisuals.length; i++) {
            exploredCoices[i + 1] = algoVisuals[i].getName();
        }
        final String[] finalChoices = exploredCoices;
        ChoiceBox exploredBox = exploredBox(finalChoices);

        Label randomPositionsLabel = new Label("New random positions: ");
        randomPositionsLabel.setTextFill(Color.WHITE);
        randomPositionsLabel.setFont(new Font(fontSize));
        Button randomPositionsButton = new Button("Randomize");
        randomPositionsButton.setOnAction(value -> {
            clickRandomPositions();
            updatePositionTexts(startXField, startYField, goalXField, goalYField);
            for (int i = 0; i < algoVisuals.length; i++) {
                updatePathTexts(pathLengthTexts, i, algoVisuals, pathCostTexts);
            }
            invalidPositionLabel.setText("");
        });

        Label mapsLabel = new Label("Switch between maps: ");
        mapsLabel.setTextFill(Color.WHITE);
        mapsLabel.setFont(new Font(fontSize));

        Button previousMapButton = new Button("Prev");
        previousMapButton.setOnAction(value -> {
            scenario.setPreviousGrid();
            switchMap(window);
        });
        Button nextMapButton = new Button("Next");
        nextMapButton.setOnAction(value -> {
            scenario.setNextGrid();
            switchMap(window);
        });
        HBox prevNextMapButtons = new HBox(previousMapButton, nextMapButton);

        Grid[] grids = scenario.getGrids();
        Button[] mapButtons = new Button[grids.length];
        for (int i = 0; i < grids.length; i++) {
            int j = i;
            Button mapButton = new Button(Integer.toString(i + 1));
            mapButton.setOnAction(value -> {
                scenario.setGridIndex(j);
                scenario.setGrid(grids[j]);
                switchMap(window);
            });
            mapButtons[i] = mapButton;
        }
        HBox mapNumberButtons = new HBox(mapButtons);

        Label perfTestLabel = new Label("WARNING:\nWhile the tests are running,"
                + "\nthe application will be frozen.");
        perfTestLabel.setTextFill(Color.WHITE);
        Button perfTestButton = new Button("Run performance tests");
        Text testResults = new Text();
        testResults.setTextAlignment(TextAlignment.CENTER);
        testResults.setFill(Color.WHITE);
        perfTestButton.setOnAction(value -> {
            testResults.setText(runPerformanceTests(scenario.getAlgorithmVisuals()));
        });

        Label tileSizeLabel = new Label("Set tilesize: ");
        tileSizeLabel.setFont(new Font(fontSize));
        tileSizeLabel.setTextFill(Color.WHITE);
        final String[] tileSizeChoices = {"1.0", "2.0", "3.0", "4.0"};
        ChoiceBox tileSizeBox = new ChoiceBox(FXCollections.observableArrayList(tileSizeChoices));
        tileSizeBox.setValue(Double.toString(tileSize));
        tileSizeBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                setTileSize(Double.parseDouble(tileSizeChoices[new_value.intValue()]));
                Scene newScene = initScene(window, scenario.getGrid());
                window.setScene(newScene);
                window.show();
            }
        });

        toolbar.getItems().addAll(separator(), startPositionLabel,
                startTextFields, goalPositionLabel, goalTextFields,
                newPositionsButton, invalidPositionLabel,
                separator(), randomPositionsLabel, randomPositionsButton,
                separator(), pathTexts,
                separator(), exploredLabel, exploredBox,
                separator(), mapsLabel, prevNextMapButtons, mapNumberButtons,
                separator(), perfTestLabel, perfTestButton, testResults,
                separator(), tileSizeLabel, tileSizeBox);

        return toolbar;
    }

    /**
     * Updating the info text for the shortest paths and their costs found by
     * the pathfinding algorithms.
     *
     * @param pathLengthTexts A JavaFX Text array to hold path length text
     * @param i Index of the algorithm array
     * @param algoVisuals Array containing AlgorithmVisualization objects
     * @param pathCostTexts A JavaFX Text array to hold path cost text
     */
    private void updatePathTexts(Text[] pathLengthTexts, int i, AlgorithmVisualization[] algoVisuals, Text[] pathCostTexts) {
        if (pathLengthTexts[i] == null) {
            pathLengthTexts[i] = new Text();
        }
        Node[] path = algoVisuals[i].getShortestPath();
        int pathLength = (path != null ? path.length : -1);
        pathLengthTexts[i].setText(algoVisuals[i].getName()
                + "\npath length:\n"
                + pathLength + "\n");
        pathLengthTexts[i].setFill(Color.WHITE);

        if (pathCostTexts[i] == null) {
            pathCostTexts[i] = new Text();
        }
        pathCostTexts[i].setText("\npath cost:\n"
                + algoVisuals[i].getAlgorithm().getCost(scenario.getGoal())
                + "\n");
        pathCostTexts[i].setFill(Color.WHITE);
    }

    /**
     * Initializing new positions, running pathfinding algorithms and rendering
     * the new map after the grid has been set in the Scenario.
     *
     * @param window JavaFX Stage object
     */
    private void switchMap(Stage window) {
        scenario.initRandomPositions();
        initAlgorithms(scenario.getGrid());
        Scene newScene = initScene(window, scenario.getGrid());
        window.setScene(newScene);
        window.show();
    }

    /**
     * Information on algorithms and colors used in the pathfinding
     * visualization.
     *
     * @param fontSize The font size used for the toolbar elements
     * @param toolbar
     */
    private void addAlgorithmsLegend(int fontSize, ToolBar toolbar) {
        Label colorsLabel = new Label("Shortest paths: ");
        colorsLabel.setTextFill(Color.WHITE);
        colorsLabel.setFont(new Font(fontSize));
        toolbar.getItems().add(colorsLabel);

        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();
        for (int i = 0; i < algoVisuals.length; i++) {
            Text colorsText = new Text(algoVisuals[i].getName());
            colorsText.setFill(Color.web(algoVisuals[i].getColor()));
            colorsText.setFont(Font.font(fontSize));
            toolbar.getItems().add(colorsText);
        }
    }

    /**
     * Menu box to select to show the nodes explored by zero or more algorithms.
     *
     * @param exploredChoices Choices listed in the menu
     * @return JavaFX ChoiceBox object
     */
    private ChoiceBox exploredBox(final String[] exploredChoices) {
        ChoiceBox exploredBox = new ChoiceBox(FXCollections.observableArrayList(exploredChoices));
        exploredBox.setValue(exploredChoices[0]);
        showExplored = -1;
        exploredBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                if (showExplored >= 0) {
                    clearExplored();
                }
                showExplored = new_value.intValue() - 1;
                colorPathCanvas();
            }
        });
        return exploredBox;
    }

    /**
     * Creating a separator UI-element.
     *
     * @return JavaFX Separator object
     */
    private Separator separator() {
        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setPadding(new Insets(5));
        separator.setOpacity(0.5);
        return separator;
    }

    /**
     * Visualizing the map tiles.
     *
     * @param grid2D The map grid as a character array
     * @return
     */
    private Canvas tileCanvas(char[][] grid2D) {
        Canvas tileCanvas = new Canvas((int) grid2D.length * tileSize, (int) grid2D[0].length * tileSize);
        GraphicsContext tileGC = tileCanvas.getGraphicsContext2D();
        for (int i = 0; i < grid2D.length - 1; i++) {
            for (int j = 0; j < grid2D[i].length - 1; j++) {
                tileGC.setFill(tileColor(grid2D[i][j]));
                tileGC.fillRect((int) (j * tileSize), (int) (i * tileSize), tileSize, tileSize);
            }
        }
        return tileCanvas;
    }

    /**
     * Handling the user setting specific start and goal coordinates.
     *
     * @param start Start position for pathfinding
     * @param goal Goal position for pathfinding
     * @param invalidPositionLabel Label informing of possible invalid positions
     * @param pathLengthTexts Texts for the length of the shortest paths found
     * by each algorithm
     * @param pathCostTexts Texts for the cost of the shortest paths found by
     * each algorithm
     */
    private void clickNewPositions(Node start, Node goal, Label invalidPositionLabel, Text[] pathLengthTexts, Text[] pathCostTexts) {
        Grid grid = scenario.getGrid();
        if (grid.nodeIsValid(start) && grid.nodeIsValid(goal)) {
            AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();
            invalidPositionLabel.setText("");
            clearPathCanvas();
            scenario.setStart(start);
            scenario.setGoal(goal);
            for (int i = 0; i < algoVisuals.length; i++) {
                scenario.runPathfindingAlgorithm(algoVisuals[i]);
                updatePathTexts(pathLengthTexts, i, algoVisuals, pathCostTexts);
            }
            colorPathCanvas();
        } else {
            invalidPositionLabel.setText("Invalid positions");
        }
    }

    /**
     * Handling the user clicking the "New random positions"-button.
     */
    private void clickRandomPositions() {
        clearPathCanvas();
        scenario.initRandomPositions();
        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();
        for (int i = 0; i < algoVisuals.length; i++) {
            scenario.runPathfindingAlgorithm(algoVisuals[i]);
        }
        colorPathCanvas();
    }

    /**
     * Visualizing the pathfinding.
     */
    private void colorPathCanvas() {
        if (showExplored >= 0) {
            colorExplored();
        }
        colorPaths();
        colorStartAndGoal();
    }

    /**
     * Clearing the pathfinding visualization.
     */
    private void clearPathCanvas() {
        clearPaths();
        clearStartAndGoalColors();
        if (showExplored >= 0) {
            clearExplored();
        }
    }

    private void updatePositionTexts(TextField startXField, TextField startYField, TextField goalXField, TextField goalYField) {
        startXField.setText(Integer.toString(scenario.getStart().getX()));
        startYField.setText(Integer.toString(scenario.getStart().getY()));
        goalXField.setText(Integer.toString(scenario.getGoal().getX()));
        goalYField.setText(Integer.toString(scenario.getGoal().getY()));
    }

    /**
     * Coloring the start and goal nodes.
     */
    private void colorStartAndGoal() {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
        Color startColor = Color.RED;
        Color goalColor = Color.LAWNGREEN;
        Color strokeColor = Color.web("#130d14");

        pathGraphics.setStroke(strokeColor);
        pathGraphics.setLineWidth(tileSize + 1);
        pathGraphics.strokeRect((int) (start.getY() * tileSize), (int) (start.getX() * tileSize), tileSize, tileSize);
        pathGraphics.strokeRect((int) (goal.getY() * tileSize), (int) (goal.getX() * tileSize), tileSize, tileSize);
        pathGraphics.setFill(startColor);
        pathGraphics.fillRect((int) (start.getY() * tileSize), (int) (start.getX() * tileSize), tileSize, tileSize);
        pathGraphics.setFill(goalColor);
        pathGraphics.fillRect((int) (goal.getY() * tileSize), (int) (goal.getX() * tileSize), tileSize, tileSize);
    }

    /**
     * Clearing the start and goal colors.
     */
    private void clearStartAndGoalColors() {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
        pathGraphics.clearRect((int) (start.getY() * tileSize) - tileSize, (int) (start.getX() * tileSize) - tileSize, (tileSize * tileSize) + tileSize, (tileSize * tileSize) + tileSize);
        pathGraphics.clearRect((int) (goal.getY() * tileSize) - tileSize, (int) (goal.getX() * tileSize) - tileSize, (tileSize * tileSize) + tileSize, (tileSize * tileSize) + tileSize);
    }

    /**
     * Coloring different paths found by different algorithms.
     */
    private void colorPaths() {
        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();
        for (int i = 0; i < algoVisuals.length; i++) {
            Node[] path = algoVisuals[i].getShortestPath();
            if (path == null) {
                continue;
            }
            pathGraphics.setFill(Color.web(algoVisuals[i].getColor()));
            for (int j = 0; j < path.length - 1; j++) {
                pathGraphics.fillRect((int) (path[j].getY() * tileSize), (int) (path[j].getX() * tileSize), tileSize, tileSize);
            }
        }
    }

    /**
     * Clearing the path colors.
     */
    private void clearPaths() {
        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();
        for (int i = 0; i < algoVisuals.length; i++) {
            Node[] path = algoVisuals[i].getShortestPath();
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
     */
    private void colorExplored() {
        Node[][] cameFrom = scenario.getAlgorithmVisuals()[showExplored].getCameFrom();
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
     */
    private void clearExplored() {
        Node[][] cameFrom = scenario.getAlgorithmVisuals()[showExplored].getCameFrom();
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
     * Determining the color for a map tile.
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
