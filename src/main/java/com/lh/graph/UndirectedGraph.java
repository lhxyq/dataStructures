package com.lh.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by LH 2446059046@qq.com on 2017/6/13.
 * <p>
 * 无向图
 */
public class UndirectedGraph<T> implements Graph<T> {
    /**
     * 边
     */
    class Edge {
        int position;//图中顶点的位置
        double weight;//边的权重
        Edge next;//指向下一条边的指针

    }

    /**
     * 顶点
     */
    class Vertex<T> {
        T data;//顶点数据
        Edge firstEdge;//该顶点的第一条边
        double cost;//顶点的权重
        boolean visited = false;
    }

    public Vertex[] vertices;//顶点数组
    private final int DEFAULT_SIZE = 10;//顶点数组默认的大小
    private int vertextNum;//顶点的个数
    private int edgeNum;//边的个数

    public UndirectedGraph() {
        vertextNum = 0;
        edgeNum = 0;

        //初始化顶点数组
        vertices = new Vertex[DEFAULT_SIZE];
    }

    public void addVertex(T t, double cost) {
        if (null == t)
            return;

        //判断顶点是否已存在
        for (Vertex vertex : vertices) {
            if (null != vertex && t.equals(vertex.data))
                return;
        }

        Vertex<T> vertex = new Vertex<T>();
        vertex.data = t;
        vertex.cost = cost;
        vertex.firstEdge = null;

        vertices[vertextNum] = vertex;
        vertextNum++;

        //如果顶点数超过数组容量，就给数组扩容
        if (vertextNum == vertices.length)
            this.resize(vertices, 2 * vertices.length);
    }

    public void addEdge(T start, T end, double weight) {
        int pStart = this.getVertexPosition(start);//起始顶点位置
        int pEnd = this.getVertexPosition(end);//邻接点的位置

        /**
         *
         * 如果边已经存在则更新权重
         */
        Edge edge0 = this.findEdge(vertices[pStart], vertices[pEnd]);
        Edge edge1 = this.findEdge(vertices[pEnd], vertices[pStart]);
        if (null != edge0 && null != edge1) {
            edge0.weight = weight;
            edge1.weight = weight;
        }

        /**
         *
         * 添加start ---> end的边
         */
        Edge e0 = new Edge();
        e0.position = pEnd;
        e0.weight = weight;
        e0.next = null;
        this.putEdgeToLast(vertices[pStart], e0);

        /**
         *
         * 添加end ---> start的边
         */
        Edge e1 = new Edge();
        e1.position = pStart;
        e1.weight = weight;
        e1.next = null;
        this.putEdgeToLast(vertices[pEnd], e1);
    }

    /**
     * 递归的实现深度优先遍历
     */
    public void DFS(Object start, List<Object> result) {
        if (null == start || ((Vertex) start).visited)
            return;

        result.add(start);
        ((Vertex) start).visited = true;

        Edge edge = ((Vertex) start).firstEdge;
        while (null != edge) {
            DFS(vertices[edge.position], result);
            edge = edge.next;
        }
    }

    /**
     * 利用队列实现广度优先遍历
     *
     * @param start  起始顶点
     * @param result 排序结果
     */
    public void BFS(Object start, List<Object> result) {
        Queue<Vertex> queue = new LinkedList<>();//顶点队列

        queue.offer((Vertex) start);

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            if (!vertex.visited) {
                result.add(vertex);
                vertex.visited = true;

                Edge edge = vertex.firstEdge;
                while (null != edge) {
                    Vertex nextVertex = vertices[edge.position];
                    if (!nextVertex.visited)
                        queue.offer(nextVertex);

                    edge = edge.next;
                }
            }
        }
    }

    /**
     * 获得顶点的位置
     *
     * @param t 顶点数据
     * @return -1表示不存在
     */
    private int getVertexPosition(T t) {
        int position = -1;

        for (int i = 0; i < vertices.length; i++) {
            if (t.equals(vertices[i].data)) {
                position = i;
                break;
            }
        }

        return position;
    }

    /**
     * 将边放到顶点的最后
     *
     * @param vertex 顶点
     * @param edge   边
     */
    private void putEdgeToLast(Vertex<T> vertex, Edge edge) {
        Edge node = vertex.firstEdge;
        if (null == node) {
            vertex.firstEdge = edge;
        } else {
            while (null != node && null != node.next) {
                node = node.next;
            }
            node.next = edge;
        }
    }

    /**
     * 查找边
     *
     * @param start
     * @param end
     * @return
     */
    private Edge findEdge(Vertex<T> start, Vertex<T> end) {
        Edge edge = null;

        Edge node = start.firstEdge;

        while (null != node) {
            if (end == vertices[node.position]) {
                edge = node;
                break;
            }
            node = node.next;
        }

        return edge;
    }

    /**
     * 给数组扩容
     *
     * @param vertices 要扩容的数组
     * @param newSize  新的容量
     */
    private void resize(Vertex[] vertices, int newSize) {
        Vertex[] newVertex = new Vertex[newSize];

        for (int i = 0; i < vertices.length; i++) {
            newVertex[i] = vertices[i];
        }

        vertices = newVertex;
    }
}
