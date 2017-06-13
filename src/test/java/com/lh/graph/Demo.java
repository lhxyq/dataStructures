package com.lh.graph;

/**
 * Created by LH 2446059046@qq.com on 2017/6/13.
 */
public class Demo {
    public static void main(String[] args) {
        UndirectedGraph<String> graph = new UndirectedGraph<String>();

        graph.addVertex("A", 0);
        graph.addVertex("B", 0);
        graph.addVertex("C", 0);

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 3);

        for (int i = 0; i < graph.vertices.length; i++) {
            if (null != graph.vertices[i])
                System.out.println(graph.vertices[i].data);
        }

    }
}
