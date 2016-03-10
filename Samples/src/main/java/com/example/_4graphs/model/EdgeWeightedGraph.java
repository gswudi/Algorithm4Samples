package com.example._4graphs.model;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权无向图
 * Created by gaosheng on 16-3-10.
 */
public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e= new Edge(v,w,weight);
            addEdge(e);
        }
    }

    private void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }


    public static class Edge implements Comparable<Edge> {
        private final int v;
        private final int w;
        private final double weight;

        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public double weight() {
            return weight;
        }

        public int either() {
            return v;
        }

        public int other(int vertex) {
            if (v == vertex) return w;
            else if (w == vertex) return v;
            else throw new IllegalArgumentException("Inconsistent edge");
        }

        @Override
        public int compareTo(Edge e) {
            if (this.weight() < e.weight()) return -1;
            else if (this.weight() > e.weight()) return +1;
            else return 0;
        }

        @Override
        public String toString() {
            return String.format("%d~%d %.2f", v, w, weight);
        }
    }


}
