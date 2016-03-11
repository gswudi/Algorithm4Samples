package com.example._4graphs.model;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 加权无向图
 * Created by gaosheng on 16-3-10.
 */
public class _13_EdgeWeightedGraph {

    private static final String NEWLINE = System.getProperty("line.separator");
    ;

    public static void main(String[] args) {
        In in = new In(args[0]);
        _13_EdgeWeightedGraph g = new _13_EdgeWeightedGraph(in);
        StdOut.println(g);
    }

    private final int V;
    private int E;
    private Bag<_13_Edge>[] adj;

    public _13_EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<_13_Edge>();
        }
    }

    public _13_EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            _13_Edge e = new _13_Edge(v, w, weight);
            addEdge(e);
        }
    }

    private void addEdge(_13_Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<_13_Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<_13_Edge> edges() {
        Bag<_13_Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (_13_Edge e : adj[v]) {
                if (e.other(v) > v)
                    b.add(e);
            }
        }
        return b;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(":");
            for (_13_Edge e : adj[v]) {
                s.append(e).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


}
