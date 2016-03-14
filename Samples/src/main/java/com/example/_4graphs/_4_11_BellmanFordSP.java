package com.example._4graphs;

import com.example._4graphs.model._14_DirectedEdge;
import com.example._4graphs.model._15_EdgeWeightedDiagraph;
import com.example._4graphs.model._16_EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by gaosheng on 16-3-14.
 */
public class _4_11_BellmanFordSP {

    private _14_DirectedEdge[] edgeTo;  //edgeTo[v] 是到达v最短路径上的最后一条边
    private double[] distTo;   //到达v的最短路径长度
    private boolean[] onQ;  //该顶点是否在队列中
    private Queue<Integer> q;   //需要被放松的顶点队列
    private int cost;   //relax()调用的次数
    private Iterable<_14_DirectedEdge> cycle;   //edgeTo[]中是否有负权重环

    public _4_11_BellmanFordSP(_15_EdgeWeightedDiagraph g, int s) {
        distTo = new double[g.V()];
        edgeTo = new _14_DirectedEdge[g.V()];
        onQ = new boolean[g.V()];
        q = new Queue<>();
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        q.enqueue(s);
        onQ[s] = true;
        while (!q.isEmpty() && this.hasNegativeCycle()) {
            int v = q.dequeue();
            onQ[v] = false;
            relax(g, v);
        }


    }

    private void relax(_15_EdgeWeightedDiagraph g, int v) {
        for (_14_DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    onQ[w] = true;
                    q.enqueue(w);
                }
            }
            if (cost++ % g.V() == 0)
                findNegativeCycle();
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        _15_EdgeWeightedDiagraph spt = new _15_EdgeWeightedDiagraph(V);
        for (int v = 0; v < V; v++) {
            if(edgeTo[v]!=null){
                spt.addEdge(edgeTo[v]);
            }
        }
        //TODO find negative weighted cycle
        _16_EdgeWeightedDirectedCycle cycleFinder = new _16_EdgeWeightedDirectedCycle(spt);
        cycle = cycleFinder.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle!=null;
    }

    public Iterable<_14_DirectedEdge> negativeCycle(){
        return cycle;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<_14_DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<_14_DirectedEdge> stack = new Stack<>();
        while (hasPathTo(v)) {
            _14_DirectedEdge e = edgeTo[v];
            stack.push(e);
            v = e.from();
        }
        return stack;
    }
}
