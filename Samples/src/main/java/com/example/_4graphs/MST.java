package com.example._4graphs;

import com.example._4graphs.model._13_Edge;
import com.example._4graphs.model._13_EdgeWeightedGraph;

/**
 * 最小生成树
 * Created by gaosheng on 16-3-11.
 */
public abstract class MST {

    public MST(_13_EdgeWeightedGraph g) {
    }

    public abstract Iterable<_13_Edge> edges();

    public abstract double weight();

}
