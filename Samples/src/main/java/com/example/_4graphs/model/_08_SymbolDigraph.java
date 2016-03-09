package com.example._4graphs.model;

import com.example._3search.SymbolTable;
import edu.princeton.cs.algs4.In;

/**
 * Created by gaosheng on 16-3-8.
 */
public class _08_SymbolDigraph {

    private SymbolTable<String, Integer> st;
    private String[] keys;
    private _08_Digraph dg;

    public _08_SymbolDigraph(String stream, String separator) {
        st = new SymbolTable<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] split = in.readLine().split(separator);
            for (int i = 0; i < split.length; i++) {
                if(!st.contains(split[i])){
                    st.put(split[i], st.size());
                }
            }
        }
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        dg = new _08_Digraph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] split = in.readLine().split(separator);
            int v = st.get(split[0]);
            for (int i = 1; i < split.length; i++) {
                int w = st.get(split[i]);
                dg.addEdge(v, w);
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

    public _08_Digraph g() {
        return dg;
    }

}
