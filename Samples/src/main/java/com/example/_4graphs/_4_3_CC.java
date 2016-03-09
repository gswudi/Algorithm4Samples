package com.example._4graphs;

import com.example._4graphs.model._01_Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * connected components 连通分量
 * Created by gaosheng on 16-3-4.
 */
public class _4_3_CC {

    private boolean[] marked;
    private int[] id;       //记录该结点所属连通分量的id
    private int count;      //连通分量数

    public _4_3_CC(_01_Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(_01_Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w);
        }
    }

    public boolean connected(int v, int w){
        return id[v]==id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        _01_Graph G = new _01_Graph(in);
        _4_3_CC cc = new _4_3_CC(G);

        // number of connected components
        int M = cc.count();
        StdOut.println(M + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }

}
