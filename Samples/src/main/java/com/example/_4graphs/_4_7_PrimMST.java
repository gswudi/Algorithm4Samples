package com.example._4graphs;

import com.example._4graphs.model._13_EdgeWeightedGraph;
import com.example._4graphs.model._13_Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 实时Prim算法
 * Created by gaosheng on 16-3-11.
 */
public class _4_7_PrimMST extends MST {

    public static void main(String[] args) {
        In in = new In(args[0]);
        _13_EdgeWeightedGraph g=  new _13_EdgeWeightedGraph(in);
        _4_7_PrimMST mst = new _4_7_PrimMST(g);
        for (_13_Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n",mst.weight());
    }

    private _13_Edge[] edgeTo;      //edgeTo[v] is shortest edge from v to tree
    private double[] distTo;    //distTo[v]=edgeTo[w].weight
    private boolean[] marked;   //marked[v]=true if v on tree
    private IndexMinPQ<Double> pq;  //合格的crossing edges

    public _4_7_PrimMST(_13_EdgeWeightedGraph g) {
        super(g);
        edgeTo = new _13_Edge[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(g.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);          //用顶点0和权重0初始化pq
        while (!pq.isEmpty()) {
            visit(g, pq.delMin());   //将最近的顶点添加到树中
        }
    }

    private void visit(_13_EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (_13_Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w])
                continue;
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    @Override
    public Iterable<_13_Edge> edges() {
        Queue<_13_Edge> mst = new Queue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            _13_Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    @Override
    public double weight() {
        double weight = 0.0;
        for (_13_Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }
}
