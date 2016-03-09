package com.example._4graphs.model;

/**
 * 强连通分量
 * Created by gaosheng on 16-3-8.
 */
public abstract class _11_SCC {

    public _11_SCC(_08_Digraph g) {
    }

    abstract public boolean stronglyConnected(int v, int w);

    abstract public int count();

    abstract public int id(int v);

}
