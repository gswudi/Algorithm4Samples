package com.example._4graphs;

import com.example._4graphs.model._08_Digraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by gaosheng on 16-3-7.
 */
public class _4_4_DirectedDFS {

    private boolean[] marked;

    public _4_4_DirectedDFS(_08_Digraph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    public _4_4_DirectedDFS(_08_Digraph g, Iterable<Integer> sources) {
        marked = new boolean[g.V()];
        for (Integer s : sources) {
            if (!marked[s]) dfs(g, s);
        }
    }

    private void dfs(_08_Digraph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        _08_Digraph g = new _08_Digraph(new In(args[0]));
        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        _4_4_DirectedDFS dfs = new _4_4_DirectedDFS(g, sources);

        for (int i = 0; i < g.V(); i++) {
            if(dfs.marked(i)) StdOut.print(i + " ");
        }
        StdOut.println();
    }

}
