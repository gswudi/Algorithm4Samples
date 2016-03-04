package com.example._3search;

/**
 * 线性探测法的散列表
 * Created by gaosheng on 16-3-3.
 */
public class _3_6_LinearProbingHashST<Key, Value> {
    private int N;          //total element count
    private int M = 16;     //probing hashtable size
    private Key[] keys;
    private Value[] vals;

    public _3_6_LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public _3_6_LinearProbingHashST(int initialCapacity){
        M = initialCapacity;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & Integer.MAX_VALUE) % M;
    }

    private void resize(int capacity) {
        _3_6_LinearProbingHashST<Key, Value> t;
        t = new _3_6_LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = next(i)) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = next(i)) {
            if (key.equals(keys[i]))
                return vals[i];
        }
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = next(i);
        }
        keys[i] = null;
        vals[i] = null;
        i = next(i);
        while (keys[i] != null) {
            Key keyToReInsert = keys[i];
            Value valTOReInsert = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToReInsert, valTOReInsert);
            i = next(i);
        }
        N--;
        if (N > 0 && N <= M / 8) resize(M / 2);

    }

    private int next(int i) {
        return (i + 1) % M;
    }

    private boolean contains(Key key) {
        if (key == null) throw new NullPointerException("key must not be null");
        return get(key) != null;
    }

}
