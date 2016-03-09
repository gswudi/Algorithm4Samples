package com.example._4graphs.model;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by gaosheng on 16-3-3.
 */
public class _03_DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public _03_DepthFirstSearch(_01_Graph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(_01_Graph g, int v) {
        marked[v] = true;
        count++;
        for (Integer w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w);
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        _01_Graph G = new _01_Graph(in);
        int s = Integer.parseInt(args[1]);
        _03_DepthFirstSearch search = new _03_DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count() != G.V()) StdOut.println("NOT connected");
        else                         StdOut.println("connected");
    }

}
