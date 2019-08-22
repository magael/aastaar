package mj.aastaar.utils;

import mj.aastaar.Scenario;
import mj.aastaar.algorithms.PathfindingAlgorithm;
import mj.aastaar.map.Node;

/**
 * Testing the performance of pathfinding algorithms. Tries to follow the
 * example from the course testing materials.
 *
 * @author MJ
 */
public class PathfindingPerformanceTester {

    private Scenario scenario;
    private String[] names;
    private int[] nums;
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
     * @param algorithms The algorithms that are tested
     * @param names The names of the algorithms that are tested
     * @param nums Array of numbers n, where n is the number of times the tests
     * are run
     */
    public void run(PathfindingAlgorithm[] algorithms, String[] names, int[] nums) {
        this.names = names;
        this.nums = nums;
        times = new double[algorithms.length][nums.length];
        initRandomPositions();

        for (int i = 0; i < algorithms.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                times[i][j] = testAlgorithm(algorithms[i], j, nums[j]);
            }
        }
    }

    @Override
    public String toString() {
        String results = "";
        results += "Average runtime of pathfinding between two random points:\n";
        for (int i = 0; i < times.length; i++) {
            results += "\n" + names[i] + "\n";
            for (int j = 0; j < times[i].length; j++) {
                int n = nums[j];
                results += n + " repetitions: " + (times[i][j] / 1000000) + " ms" + "\n";
            }
        }
        return results;
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
        long times[] = new long[num];
        long tAcc = 0;

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < 50; j++) {
                long t = System.nanoTime();
                algorithm.search(startNodes[numIndex][i], goalNodes[numIndex][i], 4);
                tAcc += System.nanoTime() - t;
            }
            times[i] = tAcc / 50;
            tAcc = 0;
        }

        return getAverage(times);
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
