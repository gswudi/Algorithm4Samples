package com.example._4graphs.model;

import com.example._4graphs._4_9_DijkstraSP;

/**
 * Dijkstra算法计算任意2点间的距离
 * Created by 升 on 2016/3/12.
 */
public class DijkstraAllPairsSP {

    private _4_9_DijkstraSP[] all;

    public DijkstraAllPairsSP(_15_EdgeWeightedDigraph g) {
        all = new _4_9_DijkstraSP[g.V()];
        for (int v = 0; v < g.V(); v++) {
            all[v] = new _4_9_DijkstraSP(g,v);
        }
    }

    public Iterable<_14_DirectedEdge> path(int from,int to){
        return all[from].pathTo(to);
    }

    public double dist(int from ,int to){
        return all[from].distTo(to);
    }


}
