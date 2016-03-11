package com.example._4graphs;

import com.example._4graphs.model._13_EdgeWeightedGraph;
import com.example._4graphs.model._13_Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 延时Prim算法
 * Created by gaosheng on 16-3-11.
 */
public class _4_7_LazyPrimMST extends MST {

    public static void main(String[] args) {
        In in = new In(args[0]);
        _13_EdgeWeightedGraph g = new _13_EdgeWeightedGraph(in);
        _4_7_LazyPrimMST mst = new _4_7_LazyPrimMST(g);
        for (_13_Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }

    private boolean[] marked;   //marked[v]=true if v on tree
    private Queue<_13_Edge> mst;    //edges in the MST
    private MinPQ<_13_Edge> pq;     //TODO imply MinPQ 横切边edges with one endpoint in tree
    private double weight;      //total weight of MST

    public _4_7_LazyPrimMST(_13_EdgeWeightedGraph g) {
        super(g);
        mst = new Queue<_13_Edge>();
        pq = new MinPQ<>();
        marked = new boolean[g.V()];

        visit(g, 0);    //最初的切分 :将0和其他所有顶点做切分
        while (!pq.isEmpty()) {
            _13_Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) //失效边
                continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) visit(g, v);
            if (!marked[w]) visit(g, w);
        }
    }

    //将所有连接v和未被标记的顶点的边加入pq
    private void visit(_13_EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (_13_Edge e : g.adj(v)) {
            if (!marked[e.other(v)])
                pq.insert(e);
        }
    }


    @Override
    public Iterable<_13_Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }
}
