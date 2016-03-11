package com.example._4graphs;

import com.example._4graphs.model._13_Edge;
import com.example._4graphs.model._13_EdgeWeightedGraph;
import edu.princeton.cs.algs4.*;

/**
 * Kruskal算法
 * Queue保存MST中的所有边
 * PQ保存未被检查的边
 * UF判断无效的边
 * Created by gaosheng on 16-3-11.
 */
public class _4_8_KruskalMST extends MST {

    public static void main(String[] args) {
        In in = new In(args[0]);
        _13_EdgeWeightedGraph g = new _13_EdgeWeightedGraph(in);
        _4_8_KruskalMST mst = new _4_8_KruskalMST(g);
        for (_13_Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f",mst.weight());
    }

    private Queue<_13_Edge> mst;
    private double weight;

    public _4_8_KruskalMST(_13_EdgeWeightedGraph g) {
        super(g);
        mst = new Queue<>();
        MinPQ<_13_Edge> pq = new MinPQ<>();
        for (_13_Edge e : g.edges()) {
            pq.insert(e);
        }

        //run greedy algorithm
        UF uf = new UF(g.V());
        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            _13_Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
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
