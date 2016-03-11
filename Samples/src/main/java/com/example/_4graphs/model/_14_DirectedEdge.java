package com.example._4graphs.model;

/**
 * 有向图的边
 * Created by gaosheng on 16-3-11.
 */
public class _14_DirectedEdge {

    private final int v;
    private final int w;
    private final double weight;

    public _14_DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }

}
