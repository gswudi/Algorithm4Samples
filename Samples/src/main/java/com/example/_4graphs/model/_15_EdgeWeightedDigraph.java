package com.example._4graphs.model;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by gaosheng on 16-3-11.
 */
public class _15_EdgeWeightedDigraph {

    private final int V;
    private int E;
    private Bag<_14_DirectedEdge>[] adj;

    public _15_EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public _15_EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            _14_DirectedEdge e = new _14_DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public void addEdge(_14_DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<_14_DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<_14_DirectedEdge> edges(){
        Bag<_14_DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (_14_DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }

}
