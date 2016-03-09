package com.example._4graphs;

import com.example._4graphs.model._08_Digraph;
import com.example._4graphs.model._10_DepthFirstOrder;
import com.example._4graphs.model._11_SCC;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Kosaraju算法 计算强连通分量
 * Created by gaosheng on 16-3-8.
 */
public class _4_6_KosarajuSCC extends _11_SCC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public _4_6_KosarajuSCC(_08_Digraph g) {
        super(g);
        marked = new boolean[g.V()];
        id = new int[g.V()];
        _10_DepthFirstOrder order = new _10_DepthFirstOrder(g.reverse());
        for (Integer v : order.reversePost()) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    private void dfs(_08_Digraph g, Integer v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    @Override
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        _08_Digraph dg = new _08_Digraph(new In(args[0]));
        _11_SCC scc = new _4_6_KosarajuSCC(dg);
        int count = scc.count();
        StdOut.println(count + " components");
        Queue<Integer>[] components = new Queue[count];
        for (int i = 0; i < count; i++) {
            components[i] = new Queue<>();
        }
        for (int i = 0; i < dg.V(); i++) {
            components[scc.id(i)].enqueue(i);
        }

        for (int i = 0; i < count; i++) {
            for (Integer v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
