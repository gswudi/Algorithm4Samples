package com.example._4graphs.model;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 邻接表实现Graph
 * Created by gaosheng on 16-3-3.
 */
public class _01_Graph {

    private int V;  //vertex count
    private int E;  //edge count
    private Bag<Integer>[] adj;

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public _01_Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public _01_Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


}
