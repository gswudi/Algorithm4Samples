package com.example._4graphs.model;

/**
 * Created by gaosheng on 16-3-11.
 */
public class _13_Edge implements Comparable<_13_Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public _13_Edge(int v, int w, double weight) {
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
    public int compareTo(_13_Edge e) {
        if (this.weight() < e.weight()) return -1;
        else if (this.weight() > e.weight()) return +1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
