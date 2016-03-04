package com.example._4graphs;

/**
 * Created by gaosheng on 16-3-4.
 */
public abstract class Paths {

    public Paths(Graph g, int s) {
    }

    /**
     * has path from s to v
     */
    public abstract boolean hasPathTo(int v);

    /**
     * path from s to v
     * @return null if so such path
     */
    public abstract Iterable<Integer> pathTo(int v);

}
