package com.lh.graph;

import java.util.List;

/**
 * Created by LH 2446059046@qq.com on 2017/6/13.
 * <p>
 * 图的基本操作
 */
public interface Graph<T> {
    /**
     * 添加顶点
     *
     * @param t    顶点
     * @param cost 顶点权重
     */
    void addVertex(T t, double cost);

    /**
     * 添加边
     *
     * @param start  起始顶点
     * @param end    邻接点
     * @param weight 边权重
     */
    void addEdge(T start, T end, double weight);

    /**
     * 深度优先遍历
     *
     * @param start  起始顶点
     * @param result 排序结果
     * @return
     */
    void DFS(Object start, List<Object> result);

    /**
     * 广度优先遍历
     *
     * @param start  起始顶点
     * @param result 排序结果
     * @return
     */
    void BFS(Object start, List<Object> result);
}
