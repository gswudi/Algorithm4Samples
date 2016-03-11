package com.example._1fundamentals;

/**
 * Created by 升 on 2016/3/5.
 */
public class QuickUnionUF extends UnionFind {

    private final int N;
    private int count;
    private int[] parent;

    public QuickUnionUF(int N) {
        super(N);
        this.N = N;
        this.count = N;
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    @Override
    public boolean connected(int v, int w) {
        while (parent[w]!= w){
            w = parent[w];
        }
        while (parent[v]!= v){
            v = parent[v];
        }
        return v == w;
    }

    @Override
    public void union(int v, int w) {
        int rootV = find(v);
        int rootW = find(w);
        if(rootV!=rootW){
            parent[rootW]=rootV;
            count--;
        }
    }

    @Override
    public int find(int v) {
        while (parent[v]!= v){
            v = parent[v];
        }
        return v;
    }

    @Override
    public int count() {
        return count;
    }
}
