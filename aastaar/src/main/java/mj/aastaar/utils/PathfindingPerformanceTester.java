package mj.aastaar.utils;

import mj.aastaar.algorithms.PathfindingAlgorithm;
import mj.aastaar.map.Grid;

/**
 * Testing the performance of pathfinding algorithms.
 *
 * @author MJ
 */
public class PathfindingPerformanceTester {

    public void runPathfindingAlgorithm(Grid grid, PathfindingAlgorithm algorithm, int n) {
        for (int i = 0; i < n; i++) {
            
        }
//            System.out.println("Starting " + name);
//            int pathLength = algorithm.search(start, goal, 4);
//            System.out.print(name + " shortest path length: " + pathLength);
//            System.out.println(", cost: " + algorithm.getCost(goal));
//            shortestPaths[i] = algorithm.getPath().shortestPath(goal, start, pathLength);
//            System.out.println("Retrieved " + name + " shortest path as array \n");
//
//            int n = 100;
//        for (int run = 0; run < nums.length; run++) {
//            int num = nums[run];
//            int[] arr = new int[num];
//            long[] times = new long[n];
//            long t;
//
//            //generate queries
//            int[] lArr = new int[n * 100];
//            int[] rArr = new int[lArr.length];
//            for (int i = 0; i < lArr.length; i++) {
//                lArr[i] = rand.nextInt(num - 1);
//                rArr[i] = lArr[i] + rand.nextInt(num - lArr[i]);
//            }
//
//            times = new long[lArr.length];
//            for (int i = 0; i < lArr.length; i++) {
//                long tAcc = 0;
//                int l = lArr[i];
//                int r = rArr[i];
//                for (int j = 0; j < n; j++) {
//                    t = System.nanoTime();
//                    sRMQ.query(l, r);
//                    tAcc += System.nanoTime() - t;
//                }
//                times[i] = tAcc / n;
//            }
//            staticQueries[run] = getAverage(times);
//            staticStd[run] = getStd(times, staticQueries[run]);
//
//            System.out.println("Ran " + num);
    }
}
