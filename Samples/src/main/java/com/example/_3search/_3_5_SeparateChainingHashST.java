package com.example._3search;

import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * 基于拉链法的散列表
 * Created by gaosheng on 16-3-3.
 */
public class _3_5_SeparateChainingHashST<Key, Value> {

    private int N;  //element count
    private int M;  //hashtable size
    private SequentialSearchST<Key, Value>[] st;

    public _3_5_SeparateChainingHashST() {
        this(997);
    }

    public _3_5_SeparateChainingHashST(int M) {
        this.M = M;
        st = new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    public void delete(Key key){
        st[hash(key)].delete(key);
    }


    public Iterable<Key> keys() {
        //TODO
        return null;
    }

}
