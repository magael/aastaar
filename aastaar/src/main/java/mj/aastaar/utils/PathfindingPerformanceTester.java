package mj.aastaar.utils;

import mj.aastaar.Scenario;
import mj.aastaar.algorithms.AStar;
import mj.aastaar.algorithms.PathfindingAlgorithm;
import mj.aastaar.algorithms.UniformCostSearch;
import mj.aastaar.map.Grid;
import mj.aastaar.map.Node;

/**
 * Testing the performance of pathfinding algorithms.
 *
 * @author MJ
 */
public class PathfindingPerformanceTester {

    private Scenario scenario;

    public PathfindingPerformanceTester(Scenario scenario) {
        this.scenario = scenario;
    }

    public double[][] run(PathfindingAlgorithm[] algorithms, int[] nums) {
        double[][] times = new double[algorithms.length][nums.length];
        for (int i = 0; i < times.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int n = nums[j];
                times[i][j] = testPathfindingPerformance(algorithms[i], n);
            }
        }
        return times;
    }

    public void printResults(double[][] times, int[] nums, String[] names) {
        System.out.println("Average runtime of pathfinding between two random points");
        for (int i = 0; i < times.length; i++) {
        System.out.println("\n" + names[i]);
            for (int j = 0; j < times[i].length; j++) {
                int n = nums[j];
                System.out.println((n * n) + " repetitions: " + times[i][j] + " ms");
            }
        }
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
