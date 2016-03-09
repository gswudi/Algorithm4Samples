package com.example._4graphs.model;

import com.example._4graphs._4_4_DirectedDFS;

/**
 * 传递闭包
 * Created by gaosheng on 16-3-8.
 */
public class _12_TransitiveClosure {

    private _4_4_DirectedDFS[] all;

    public _12_TransitiveClosure(_08_Digraph g) {
        all = new _4_4_DirectedDFS[g.V()];
        for (int i = 0; i < g.V(); i++) {
            all[i] = new _4_4_DirectedDFS(g,i);
        }
    }

    public boolean reachable(int v,int w){
        return all[v].marked(w);
    }

}
