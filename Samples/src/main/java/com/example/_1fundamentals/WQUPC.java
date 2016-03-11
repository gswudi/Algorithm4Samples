package com.example._1fundamentals;

/**
 * weight quick union find with path compression
 * Created by 升 on 2016/3/5.
 */
public class WQUPC extends UnionFind{

    final int N;
    int count;
    int[] parent;
    int[] rank;

    public WQUPC(int N) {
        super(N);
        this.N = N;
        count = N;
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    @Override
    public boolean connected(int v, int w) {
        return find(v)==find(w);
    }

    @Override
    public void union(int v, int w) {
        int rootV = find(v);
        int rootW = find(w);
        if(rootV==rootW) return;
        if(rank[rootV]>rank[rootW]){
            parent[rootW] = rootV;
        }else if(rank[rootV]<rank[rootW]){
            parent[rootV] = rootW;
        }else {
            parent[rootW] = rootV;
            rank[rootV]++;
        }
        count--;
    }

    @Override
    public int find(int v) {
        while (v!=parent[v]){
            parent[v] = parent[parent[v]]; //父指针指向父节点的父节点
            v = parent[v];  //把父节点作为根节点
        }
        return v;
    }

    @Override
    public int count() {
        return count;
    }
}
