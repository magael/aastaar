package mj.aastaar.utils;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import mj.aastaar.Scenario;
import mj.aastaar.algorithms.AlgorithmVisualization;
import mj.aastaar.algorithms.PathfindingAlgorithm;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Testing the performance of pathfinding algorithms. Tries to follow the
 * example from the course testing materials.
 *
 * @author MJ
 */
public class PathfindingPerformanceTester {

    private Scenario scenario;
    private int[] nums;
    private double[] initTimes;
    private double[][] times;
    private Node[][] startNodes;
    private Node[][] goalNodes;

    /**
     * The constructor for the PathfindingPerformanceTester class.
     *
     * @param scenario Pathfinding scenario with helpful methods and an
     * initialized grid
     */
    public PathfindingPerformanceTester(Scenario scenario) {
        this.scenario = scenario;
    }

    /**
     * Running performance tests on pathfinding algorithms.
     *
     * @param nums Array of numbers n, where n is the number of times the tests
     * are run
     */
    public void run(int[] nums) {
        AlgorithmVisualization[] algoVisuals = scenario.getAlgorithmVisuals();
        if (scenario.getGrid() == null || scenario.getGrid2D() == null
                || scenario.getGrid().getLength() < 1) {
            System.out.println("The scenario has no grid. Aborting tests.");
            return;
        }
        this.nums = nums;
        times = new double[algoVisuals.length][nums.length];
        initTimes = new double[algoVisuals.length];
        double initReps = 2;
        initRandomPositions();

        for (int i = 0; i < algoVisuals.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                times[i][j] = testAlgorithm(algoVisuals[i].getAlgorithm(), j, nums[j]);
            }
            for (int j = 0; j < initReps; j++) {
                initTimes[i] = testAlgorithmInit(algoVisuals[i].getAlgorithm(), i);
            }
        }
    }

    @Override
    public String toString() {
        String results = "";
        results += "Average runtime of\npathfinding between\ntwo random points:\n";
        for (int i = 0; i < times.length; i++) {
            results += "\n" + scenario.getAlgorithmVisuals()[i].getName() + "\n";
            results += initResults(i);
            for (int j = 0; j < times[i].length; j++) {
                int n = nums[j];
                BigDecimal ms = new BigDecimal(times[i][j] / 1000000);
                results += n + " positions: " + ms.round(new MathContext(4)) + " ms" + "\n";
            }
        }
        return results;
    }

    private String initResults(int i) {
        String results = "";
        BigDecimal ms = new BigDecimal(initTimes[i] / 1000000);
        results += "Initialization avg: " + ms.round(new MathContext(4)) + " ms." + "\n";
        return results;
    }

    private double testAlgorithmInit(PathfindingAlgorithm algorithm, int i) {
        double algoTimes = 0.0;
        long tAcc = 0;
        int n = 100;

        for (int j = 0; j < n; j++) {
            long t = System.nanoTime();
            Class c = algorithm.getClass();
            try {
                Constructor constructor = c.getConstructor(new Class[]{Grid.class});
                PathfindingAlgorithm newAlgorithm = (PathfindingAlgorithm) constructor.newInstance(scenario.getGrid());
            } catch (Exception e) {
                System.out.println(scenario.getAlgorithmVisuals()[i]
                        + "initialization failed on round " + j + ".");
            }
            tAcc += System.nanoTime() - t;
        }
        algoTimes = tAcc / n;
        tAcc = 0;

        return algoTimes;
    }

    /**
     * Testing a pathfinding algorithm's runtime across several repetitions. For
     * every given starting and goal position, the path is calculated 50 times,
     * and the average of those results is added to the result times. The
     * parameter num determines how many of those results are calculated.
     *
     * @param algorithm The pathfinding algorithm
     * @param numIndex An index in the nums array that the test run was given
     * @param num The number of results generated
     * @return The average runtime
     */
    private double testAlgorithm(PathfindingAlgorithm algorithm, int numIndex, int num) {
        long algoTimes[] = new long[num];
        long tAcc = 0;
        int n = 50;

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < n; j++) {
                long t = System.nanoTime();
                algorithm.search(startNodes[numIndex][i], goalNodes[numIndex][i], 4);
                tAcc += System.nanoTime() - t;
            }
            algoTimes[i] = tAcc / n;
            tAcc = 0;
        }

        return getAverage(algoTimes);
    }

    /**
     * Calculating the average of run times.
     *
     * @param times array of run times
     * @return the average runtime
     */
    private double getAverage(long[] times) {
        double s = 0;
        for (long time : times) {
            s += time;
        }
        return s / times.length;
    }

    private void initRandomPositions() {
        startNodes = new Node[nums.length][];
        goalNodes = new Node[nums.length][];
        for (int i = 0; i < nums.length; i++) {
            startNodes[i] = new Node[nums[i]];
            goalNodes[i] = new Node[nums[i]];
            for (int j = 0; j < nums[i]; j++) {
                scenario.initRandomPositions();
                startNodes[i][j] = scenario.getStart();
                goalNodes[i][j] = scenario.getGoal();
            }
        }
    }
}
