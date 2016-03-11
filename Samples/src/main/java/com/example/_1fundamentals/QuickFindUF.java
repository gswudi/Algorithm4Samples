package com.example._1fundamentals;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by 升 on 2016/3/5.
 */
public class QuickFindUF extends UnionFind {

    private final int N;
    private int[] id;       //该结点所属连通分量的id
    public int count;

    public QuickFindUF(int N) {
        super(N);
        this.N = N;
        this.count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    @Override
    public boolean connected(int v ,int w){
        return id[w]==id[v];
    }

    @Override
    public void union(int v, int w){
        if(id[v]!=id[w]){
            for (int i = 0; i < N; i++) {
                if(id[i]==id[v]){
                    id[i]=id[w];
                }
            }
            count--;
        }
    }

    @Override
    public int find(int v){
        return id[v];
    }

    @Override
    public int count(){
        return count;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        QuickFindUF uf = new QuickFindUF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.connected(p,q);
        }
        int i=1;
    }

}
