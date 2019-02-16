package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.Arrays;
import java.util.LinkedList;

public class SearchShortestLink {

    private UserNode[] nodes;
    private static  int EDGE_DISATNCE = 6;




//    public int[] shortestReach(int startID){
//        LinkedList<Integer> queue = new LinkedList<>();
//        queue.add(startID);

//        boolean[] visited = new boolean[nodes.length];
//        int[] distances = new int[nodes.length];

//        Arrays.fill(distances, -1);
//        distances[startID] = 0;
//
//        while(! queue.isEmpty()){
//            int node = queue.poll();
//            for (int neighbor : nodes[node].neighbors){
//                if( distances[neighbor] == -1){
//                    distances[neighbor] = distances[node] + EDGE_DISATNCE;
//                    queue.add(neighbor);
//                }
//            }
//        }
//        return  distances;
//    }



}
