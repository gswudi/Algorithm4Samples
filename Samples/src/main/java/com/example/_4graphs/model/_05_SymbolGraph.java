package com.example._4graphs.model;

import com.example._3search.SymbolTable;
import edu.princeton.cs.algs4.In;

/**
 * 符号图(顶点用字符串表示)
 * Created by gaosheng on 16-3-4.
 */
public class _05_SymbolGraph {

    private SymbolTable<String, Integer> st;
    private String[] keys;
    private _01_Graph g;

    public _05_SymbolGraph(String stream, String sp) {
        st = new SymbolTable<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);

            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        g = new _01_Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 0; i < a.length; i++) {
                g.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public _01_Graph g() {
        return g;
    }

}
