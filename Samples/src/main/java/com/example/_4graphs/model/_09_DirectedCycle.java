package com.example._4graphs.model;

import edu.princeton.cs.algs4.*;

/**
 * Created by gaosheng on 16-3-7.
 */
public class _09_DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;   //有向环中的所有顶点
    private boolean[] onStack;      //递归调用的栈上的所有顶点

    public _09_DirectedCycle(_08_Digraph g) {
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) dfs(g, i);
        }
    }

    private void dfs(_08_Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (Integer w : g.adj(v)) {
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                int x = v;
                while (x != w) {
                    cycle.push(x);
                    x = edgeTo[x];
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        _08_Digraph dg = new _08_Digraph(new In(args[0]));

        _09_DirectedCycle finder = new _09_DirectedCycle(dg);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }

}
