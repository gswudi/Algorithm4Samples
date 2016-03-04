package com.example._4graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/**
 * Created by gaosheng on 16-3-3.
 */
public class _4_1_DepthFirstPaths extends Paths {

    private boolean[] marked;   //has dfs() been called for this vertex?
    private int[] edgeTo;       //last vertex on known path to this vertex
    private final int s;        //source


    /**
     * 深度优先搜索该graph,并且在edgeTo中指定每个搜索到的结点的上一个结点
     * @param g graph
     * @param s source of graph
     */
    public _4_1_DepthFirstPaths(Graph g, int s) {
        super(g, s);
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x = v;
        while (x!=s){
            path.push(x);
            x = edgeTo[x];
        }
        path.push(s);
        return path;
    }

    /**
     * Unit tests the <tt>DepthFirstPaths</tt> data type.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        _4_1_DepthFirstPaths dfs = new _4_1_DepthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}
