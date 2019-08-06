///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mj.aastaar.algorithms;
//
//import java.util.HashMap;
//import java.util.PriorityQueue;
//import mj.aastaar.map.Grid;
//import mj.aastaar.map.Node;
//
///**
// *
// * @author MJ
// */
//public class DijkstraOpenClosed {
//    
//    public int shortestPath(Grid grid, Node start, Node goal, int directions) {
//        HashMap<Node, Long> dist = new HashMap<>();
//        boolean[] visited = new boolean[n + 1];
//        
//        for (int i = 1; i <= n; i++) {
//            dist[i] = 1000000000000000l;
//        }
//        
//        PriorityQueue<Node> pq = new PriorityQueue();
//
//        dist[alku] = 0;
//
//        pq.add(new Pari(alku, 0l));
//
// 
//
//        while (!pq.isEmpty()) {
//
//            Pari p = pq.poll();
//
//            int s = p.s;
//
//            long w = p.w;
//
// 
//
//            if (kasitelty[s]) {
//
//                continue;
//
//            }
//
// 
//
//            kasitelty[s] = true;
//
// 
//
//            for (Pari seuraava : vl[s]) {
//
//                if (dist[seuraava.s] > w + seuraava.w) {
//
//                    dist[seuraava.s] = w + seuraava.w;
//
//                    pq.add(new Pari(seuraava.s, dist[seuraava.s]));
//
//                }
//
//            }
//
//        }
//
// 
//
//        return dist;
//    }
//}
