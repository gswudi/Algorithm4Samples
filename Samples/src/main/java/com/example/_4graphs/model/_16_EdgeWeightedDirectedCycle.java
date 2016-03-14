package com.example._4graphs.model;

import edu.princeton.cs.algs4.Stack;

/**
 * 加权有向图的环
 * Created by gaosheng on 16-3-14.
 */
public class _16_EdgeWeightedDirectedCycle {

    private boolean[] marked;
    private _14_DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<_14_DirectedEdge> cycle;


    public _16_EdgeWeightedDirectedCycle(_15_EdgeWeightedDiagraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new _14_DirectedEdge[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v])
                dfs(g, v);
        }
    }

    private void dfs(_15_EdgeWeightedDiagraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (_14_DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (cycle != null) return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                while (e.from() != w) {
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<_14_DirectedEdge> cycle() {
        return cycle;
    }

}
