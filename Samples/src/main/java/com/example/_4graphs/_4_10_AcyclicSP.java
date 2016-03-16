package com.example._4graphs;

import com.example._4graphs.model._14_DirectedEdge;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Topological;

/**
 * 无环加权有向图的最短路径算法(基于拓扑排序，省去优先级队列的操作)
 * Created by 升 on 2016/3/12.
 */
public class _4_10_AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public _4_10_AcyclicSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0;


//TODO        _4_5_Topological top = new _4_5_Topological(g);
        Topological top = new Topological(g);
        assert top.hasOrder();  //断言拓扑有序  图中没有环
        for (Integer v : top.order()) {
            for (DirectedEdge e : g.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

}
