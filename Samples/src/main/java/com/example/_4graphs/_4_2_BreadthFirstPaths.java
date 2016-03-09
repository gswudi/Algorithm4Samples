package com.example._4graphs;

import com.example._4graphs.model._01_Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by gaosheng on 16-3-4.
 */
public class _4_2_BreadthFirstPaths extends Paths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;


    public _4_2_BreadthFirstPaths(_01_Graph g, int s) {
        super(g, s);
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g,s);
    }

    private void bfs(_01_Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            //当前结点出队列
            int v = queue.dequeue();
            //当前结点所有子节点进队列
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
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

    public static void main(String[] args) {
        In in = new In(args[0]);
        _01_Graph G = new _01_Graph(in);
        int s = Integer.parseInt(args[1]);
        _4_2_BreadthFirstPaths bfs = new _4_2_BreadthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : bfs.pathTo(v)) {
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
