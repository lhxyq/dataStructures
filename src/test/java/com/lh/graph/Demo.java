package com.lh.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LH 2446059046@qq.com on 2017/6/13.
 */
public class Demo {
    public static void main(String[] args) {
        Graph<Integer> graph = new UndirectedGraph<>();

        graph.addVertex(1, 0);
        graph.addVertex(2, 0);
        graph.addVertex(3, 0);
        graph.addVertex(4, 0);
        graph.addVertex(5, 0);
        graph.addVertex(6, 0);
        graph.addVertex(7, 0);
        graph.addVertex(8, 0);

        graph.addEdge(1, 2, 0);
        graph.addEdge(1, 3, 0);
        graph.addEdge(2, 4, 0);
        graph.addEdge(2, 5, 0);
        graph.addEdge(3, 6, 0);
        graph.addEdge(3, 7, 0);
        graph.addEdge(4, 8, 0);
        graph.addEdge(5, 8, 0);
        graph.addEdge(6, 7, 0);

        /*List<Object> dfsResult = new ArrayList<>();
        graph.DFS(((UndirectedGraph) graph).vertices[0], dfsResult);

        System.out.print("深度优先遍历:\t");
        for (Object object : dfsResult) {
            System.out.print(((UndirectedGraph.Vertex) object).data + "\t");
        }
        System.out.println();*/

        List<Object> bfsResult = new ArrayList<>();
        graph.BFS(((UndirectedGraph) graph).vertices[5], bfsResult);
        System.out.print("广度优先遍历:\t");
        for (Object object : bfsResult) {
            System.out.print(((UndirectedGraph.Vertex) object).data + "\t");
        }
        System.out.println();
    }
}
