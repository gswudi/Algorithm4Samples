package com.example._4graphs.model;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 给定图是否有环
 * Created by gaosheng on 16-3-4.
 */
public class _04_Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public _04_Cycle(_01_Graph g) {
        marked = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s])
                dfs(g, s, -1);
        }
    }

    /**
     *
     * @param g graph
     * @param v 当前结点
     * @param d 父节点
     */
    private void dfs(_01_Graph g, int v, int d) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w, v);
            else if (w != d)
                hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        _01_Graph G = new _01_Graph(in);
        int s = Integer.parseInt(args[1]);
        _04_Cycle cycle = new _04_Cycle(G);
        cycle.hasCycle();
        StdOut.print(cycle.hasCycle()?"Graph is cyclic":"Graph is acyclic");

    }

}
