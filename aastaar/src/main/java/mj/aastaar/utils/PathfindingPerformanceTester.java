package mj.aastaar.utils;

import mj.aastaar.Scenario;
import mj.aastaar.algorithms.PathfindingAlgorithm;
import mj.aastaar.map.Node;

/**
 * Testing the performance of pathfinding algorithms.
 *
 * @author MJ
 */
public class PathfindingPerformanceTester {

    private Scenario scenario;
    private String[] names;
    private double[][] times;
    private int[] nums;

    /**
     *
     * @param scenario
     */
    public PathfindingPerformanceTester(Scenario scenario) {
        this.scenario = scenario;
    }

    /**
     *
     * @param algorithms
     * @param names
     * @param nums
     */
    public void run(PathfindingAlgorithm[] algorithms, String[] names, int[] nums) {
        this.names = names;
        this.nums = nums;
        times = new double[algorithms.length][nums.length];
        for (int i = 0; i < times.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int n = nums[j];
                times[i][j] = testPathfindingPerformance(algorithms[i], n);
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
                results += (n * n) + " repetitions: " + times[i][j] + " ns" + "\n";
            }
        }
        return results;
    }

    private double testPathfindingPerformance(PathfindingAlgorithm algorithm, int n) {
        long times[] = new long[n * n];
        long tAcc = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                scenario.initRandomPositions();
                Node start = scenario.getStart();
                Node goal = scenario.getGoal();
                long t = System.nanoTime();
                algorithm.search(start, goal, 4);
                tAcc += System.nanoTime() - t;
            }
            times[i] = tAcc / n;
        }

        return getAverage(times);
    }

    private double getAverage(long[] times) {
        double s = 0;
        for (long time : times) {
            s += time;
        }
        return s / times.length;
    }
}
