package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Utility {

    public Utility() {

    }

    public static void dfs(Map<Integer, List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfsHelper(i, graph, visited);
            }
        }
    }

    public static void dfsHelper(int node, Map<Integer, List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        // process whatever you need to here
        System.out.println("visited node: " + node);
        List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
        for(int nei : neighbors) {
            if (!visited[nei]) {
                dfsHelper(nei, graph, visited);
            }
        }
    }

    public static List<Integer> topoSort(Map<Integer, List<Integer>> graph, int n) {
        int[] color = new int[n];

        boolean[] visited = new boolean[n];
        List<Integer> ordering = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                topoSortHelper(i, graph, ordering, color, visited);
            }
        }

        // we go to every node and see if it is processed or not
        // when we first come to a node we see if it is colored or not.
        // if it is then there is no ordering possible
        Collections.reverse(ordering);
        System.out.println(ordering);
        return ordering;

    }

    public static void topoSortHelper(int node, Map<Integer, List<Integer>> graph,
                               List<Integer> order,
                               int[] color,
                               boolean[] visited) {
        visited[node] = true;
        color[node] = 1;
        // mark the node as visited

        List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());

        for(int nei : neighbors) {
            if(color[nei] == 1) {
                // there is a cycle in the graph
                // we mark the color of this node as -1
                color[node] = -1;
            } else {
                if(color[nei]== 0)
                    topoSortHelper(nei, graph, order, color, visited);
            }
        }



        if(color[node] == 1) {
            color[node] = 2;
            order.add(node);
        }

    }
}
