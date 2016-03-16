package com.example._4graphs;

import com.example._4graphs.model._14_DirectedEdge;
import com.example._4graphs.model._15_EdgeWeightedDigraph;


import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * 加权有向图的Dijkstra单源最短路径算法
 * Created by 升 on 2016/3/12.
 */
public class _4_9_DijkstraSP {

    private _14_DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public _4_9_DijkstraSP(_15_EdgeWeightedDigraph g, int s) {
        edgeTo = new _14_DirectedEdge[g.V()];
        distTo = new double[g.V()];
        pq = new IndexMinPQ<>(g.V());

        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        pq.insert(s,0.0);
        while (!pq.isEmpty()){
            relax(g,pq.delMin());
        }
    }

    private void relax(_15_EdgeWeightedDigraph g, int v) {
        for (_14_DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if(distTo[w]>distTo[v]+e.weight()){
                distTo[w] = distTo[v]+e.weight();
                edgeTo[w] = e;
                if(pq.contains(w))
                    pq.changeKey(w,distTo[w]);
                else pq.insert(w,distTo[w]);
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v]<Double.POSITIVE_INFINITY;
    }

    public Iterable<_14_DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<_14_DirectedEdge> path = new Stack<>();
        _14_DirectedEdge e = edgeTo[v];
        while (e!=null){
            path.push(e);
            e = edgeTo[e.from()];
        }
        return path;
    }

}
