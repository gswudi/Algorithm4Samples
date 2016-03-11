package com.example._1fundamentals;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by 升 on 2016/3/5.
 */
public abstract class UnionFind {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        UnionFind unionFind = new _1_5_WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (unionFind.connected(p, q)) continue;
            unionFind.union(p, q);
            StdOut.println(p + " " + q);
        }
        int i = 1;
    }

    public UnionFind(int N){
    }

    public abstract boolean connected(int v, int w);

    public abstract void union(int v, int w);

    public abstract int find(int v);

    public abstract int count();
}
