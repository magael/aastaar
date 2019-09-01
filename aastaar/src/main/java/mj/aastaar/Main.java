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
import javafx.scene.Group;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mj.aastaar.algorithms.AStar;
import mj.aastaar.algorithms.AlgorithmVisualization;
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
    private int showExplored;

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
     * Using the performance tester class to test pathfinding speed. Setting the
     * number of times the tests are run.
     *
     * @param algorithms The algorithms that are tested
     * @param algoNames The names of the algorithms that are
     */
    private static String runPerformanceTests(AlgorithmVisualization[] algoVisuals) {
//        int[] nums = {10, 50, 100, 500, 1000};
        int[] nums = {10, 10, 20};
        PathfindingPerformanceTester tester = new PathfindingPerformanceTester(scenario);
        long t = System.nanoTime();
        tester.run(nums);
        BigDecimal elapsedTime = new BigDecimal((System.nanoTime() - t) / 1000000000);

        String testResults = tester.toString() + "\nPerformance tests ran\nin a total of "
                + elapsedTime.round(new MathContext(3)) + " seconds.";
        return testResults;
    }

    /**
     * Initialize the algorithms and their visualization components.
     *
     * @param grid
     */
    private static void initAlgorithms(Grid grid) {
        String cyan = "#00FFFF";
        String magenta = "#FF00FF";
        AlgorithmVisualization dijkstra = new AlgorithmVisualization(new UniformCostSearch(grid), "Dijkstra", cyan);
        AlgorithmVisualization aStar = new AlgorithmVisualization(new AStar(grid), "A*", magenta);
        AlgorithmVisualization[] algorithmVisuals = {dijkstra, aStar};
        scenario.setAlgorithmVisuals(algorithmVisuals);

        for (int i = 0; i < algorithmVisuals.length; i++) {
            scenario.runPathfindingAlgorithm(algorithmVisuals[i]);
        }
    }

    @Override
    public void start(Stage window) throws Exception {
        double tileSize = 2.0;
        Grid grid = scenario.getGrid();

        Canvas pathCanvas = new Canvas(grid.getLength() * tileSize, grid.getRowLength() * tileSize);
        pathGraphics = pathCanvas.getGraphicsContext2D();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(tileCanvas(grid.getGrid2D(), tileSize));
        ToolBar toolbar = toolBar(window, tileSize);
        borderPane.setRight(toolbar);

        colorPaths(tileSize);
        colorStartAndGoal(tileSize);

        ScrollPane scrollPane = new ScrollPane(new Group(borderPane, pathCanvas));
        Scene scene = new Scene(scrollPane);
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
     * Initialize a new scene with the given grid.
     *
     * @param window JavaFX stage component, where the scene is to be set
     * @param grid Pathfinding grid
     * @param tileSize The map tile size
     * @return JavaFX Scene object
     */
    private Scene initScene(Stage window, Grid grid, double tileSize) {
        Canvas pathCanvas = new Canvas(grid.getLength() * tileSize, grid.getRowLength() * tileSize);
        pathGraphics = pathCanvas.getGraphicsContext2D();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(tileCanvas(grid.getGrid2D(), tileSize));
        ToolBar toolbar = toolBar(window, tileSize);
        borderPane.setRight(toolbar);
        colorPaths(tileSize);
        colorStartAndGoal(tileSize);
        ScrollPane scrollPane = new ScrollPane(new Group(borderPane, pathCanvas));
        Scene scene = new Scene(scrollPane);
        return scene;
    }

    /**
     * Creating a toolbar with legend and interactive UI elements.
     *
     * @param tileSize The map tile size
     * @return JavaFX ToolBar object
     */
    private ToolBar toolBar(Stage window, double tileSize) {
        ToolBar toolbar = new ToolBar();
        toolbar.setOrientation(Orientation.VERTICAL);
        toolbar.setPadding(new Insets(15));
        toolbar.setBackground(new Background(new BackgroundFill(
                Color.web("#130d14"), null, Insets.EMPTY)));
        int fontSize = 14;
        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();

        addAlgorithmsLegend(fontSize, toolbar);

        //TODO: texts into private global array, update on clickRandom or clickNew
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
            if (grid.nodeIsValid(start) && grid.nodeIsValid(goal)) {
                invalidPositionLabel.setText("");
                clearPaths(tileSize);
                clearStartAndGoalColors(tileSize);
                if (showExplored >= 0) {
                    clearExplored(tileSize);
                }
                scenario.setStart(start);
                scenario.setGoal(goal);
                for (int i = 0; i < algoVisuals.length; i++) {
                    scenario.runPathfindingAlgorithm(algoVisuals[i]);
                    updatePathTexts(pathLengthTexts, i, algoVisuals, pathCostTexts);
                }
                if (showExplored >= 0) {
                    colorExplored(tileSize);
                }
                colorPaths(tileSize);
                colorStartAndGoal(tileSize);
            } else {
                invalidPositionLabel.setText("Invalid positions");
            }
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
        ChoiceBox exploredBox = exploredBox(finalChoices, tileSize);

        Label randomPositionsLabel = new Label("New random positions: ");
        randomPositionsLabel.setTextFill(Color.WHITE);
        randomPositionsLabel.setFont(new Font(fontSize));
        Button randomPositionsButton = new Button("Randomize");
        randomPositionsButton.setOnAction(value -> {
            clickRandomPositions(tileSize);
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
            switchMap(window, tileSize);
        });
        Button nextMapButton = new Button("Next");
        nextMapButton.setOnAction(value -> {
            scenario.setNextGrid();
            switchMap(window, tileSize);
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
                switchMap(window, tileSize);
            });
            mapButtons[i] = mapButton;
        }
        HBox mapNumberButtons = new HBox(mapButtons);

        Label perfTestLabel = new Label("WARNING:\n"
                + "While the tests are running,\nthe application will be "
                + "frozen\nfor a few seconds up to\na few minutes.");
        perfTestLabel.setTextFill(Color.WHITE);
        Button perfTestButton = new Button("Run performance tests");
        Text testResults = new Text();
        testResults.setTextAlignment(TextAlignment.CENTER);
        testResults.setFill(Color.WHITE);
        perfTestButton.setOnAction(value -> {
            testResults.setText(runPerformanceTests(scenario.getAlgorithmVisuals()));
        });

        toolbar.getItems().addAll(separator(), startPositionLabel,
                startTextFields, goalPositionLabel, goalTextFields,
                newPositionsButton, invalidPositionLabel,
                separator(), pathTexts,
                separator(), exploredLabel, exploredBox,
                separator(), randomPositionsLabel, randomPositionsButton,
                separator(), mapsLabel, prevNextMapButtons, mapNumberButtons,
                separator(), perfTestLabel, perfTestButton, testResults);

        return toolbar;
    }

    private void updatePathTexts(Text[] pathLengthTexts, int i, AlgorithmVisualization[] algoVisuals, Text[] pathCostTexts) {
        if (pathLengthTexts[i] == null) {
            pathLengthTexts[i] = new Text();
        }
        pathLengthTexts[i].setText(algoVisuals[i].getName()
                + "\npath length:\n"
                + algoVisuals[i].getShortestPath().length + "\n");
        pathLengthTexts[i].setFill(Color.WHITE);
        
        if (pathCostTexts[i] == null) {
            pathCostTexts[i] = new Text();
        }
        pathCostTexts[i].setText("\npath cost:\n"
                + algoVisuals[i].getAlgorithm().getCost(scenario.getGoal())
                + "\n");
        pathCostTexts[i].setFill(Color.WHITE);
    }

    private void switchMap(Stage window, double tileSize) {
        scenario.initRandomPositions();
        initAlgorithms(scenario.getGrid());
        Scene newScene = initScene(window, scenario.getGrid(), tileSize);
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
     * Menu box to select to show the nodes explored by zero or more algorithms
     *
     * @param exploredChoices Choices listed in the menu
     * @param tileSize The map tile size
     * @return JavaFX ChoiceBox object
     */
    private ChoiceBox exploredBox(final String[] exploredChoices, double tileSize) {
        ChoiceBox exploredBox = new ChoiceBox(FXCollections.observableArrayList(exploredChoices));
        exploredBox.setValue(exploredChoices[0]);
        showExplored = -1;
        exploredBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                if (showExplored >= 0) {
                    clearExplored(tileSize);
                }
                showExplored = new_value.intValue() - 1;
                if (showExplored >= 0) {
                    colorExplored(tileSize);
                }
                colorPaths(tileSize);
                colorStartAndGoal(tileSize);
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
        separator.setPadding(new Insets(5));
        separator.setOpacity(0.5);
        return separator;
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
     * Handling the user clicking the "New random positions"-button.
     *
     * @param tileSize The map tile size
     */
    private void clickRandomPositions(double tileSize) {
        clearPaths(tileSize);
        clearStartAndGoalColors(tileSize);
        if (showExplored >= 0) {
            clearExplored(tileSize);
        }
        scenario.initRandomPositions();
        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();
        for (int i = 0; i < algoVisuals.length; i++) {
            scenario.runPathfindingAlgorithm(algoVisuals[i]);
        }

        if (showExplored >= 0) {
            colorExplored(tileSize);
        }
        colorPaths(tileSize);
        colorStartAndGoal(tileSize);
    }

    private void updatePositionTexts(TextField startXField, TextField startYField, TextField goalXField, TextField goalYField) {
        startXField.setText(Integer.toString(scenario.getStart().getX()));
        startYField.setText(Integer.toString(scenario.getStart().getY()));
        goalXField.setText(Integer.toString(scenario.getGoal().getX()));
        goalYField.setText(Integer.toString(scenario.getGoal().getY()));
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
     *
     * @param tileSize The map tile size
     */
    private void clearStartAndGoalColors(double tileSize) {
        Node start = scenario.getStart();
        Node goal = scenario.getGoal();
        pathGraphics.clearRect((int) (start.getY() * tileSize) - tileSize, (int) (start.getX() * tileSize) - tileSize, (tileSize * tileSize) + tileSize, (tileSize * tileSize) + tileSize);
        pathGraphics.clearRect((int) (goal.getY() * tileSize) - tileSize, (int) (goal.getX() * tileSize) - tileSize, (tileSize * tileSize) + tileSize, (tileSize * tileSize) + tileSize);
    }

    /**
     * Coloring different paths found by different algorithms.
     *
     * @param tileSize The map tile size
     */
    private void colorPaths(double tileSize) {
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
     *
     * @param tileSize The map tile size
     */
    private void clearPaths(double tileSize) {
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
     *
     * @param tileSize The map tile size
     */
    private void colorExplored(double tileSize) {
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
     *
     * @param tileSize The map tile size
     */
    private void clearExplored(double tileSize) {
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
