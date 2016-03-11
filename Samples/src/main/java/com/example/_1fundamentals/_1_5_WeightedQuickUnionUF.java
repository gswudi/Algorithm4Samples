package com.example._1fundamentals;

/**
 * Created by 升 on 2016/3/5.
 */
public class _1_5_WeightedQuickUnionUF extends UnionFind {

    private final int N;
    private int[] parent;
    private int count;
    private int[] size;


    public _1_5_WeightedQuickUnionUF(int N) {
        super(N);
        this.N = N;
        parent = new int[N];
        size = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int root(int v){
        while (parent[v]!=v){
            v = parent[v];
        }
        return v;
    }

    @Override
    public boolean connected(int v, int w) {
        return root(v)==root(w);
    }

    @Override
    public void union(int v, int w) {
        int rootV = root(v);
        int rootW = root(w);
        if(rootV!=rootW){
            if(size[rootV]>size[rootW]){
                parent[rootW] = rootV;
                size[rootV]+=size[rootW];
            }else {
                parent[rootW] = rootV;
                size[rootV]+=size[rootW];
            }
            count--;
        }
    }

    @Override
    public int find(int v) {
        return root(v);
    }

    @Override
    public int count() {
        return count;
    }
}
