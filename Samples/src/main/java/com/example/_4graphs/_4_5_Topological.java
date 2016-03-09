package com.example._4graphs;

import com.example._4graphs.model._08_Digraph;
import com.example._4graphs.model._08_SymbolDigraph;
import com.example._4graphs.model._09_DirectedCycle;
import com.example._4graphs.model._10_DepthFirstOrder;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by gaosheng on 16-3-7.
 */
public class _4_5_Topological {

    private Iterable<Integer> order;

    public _4_5_Topological(_08_Digraph dg) {
        _09_DirectedCycle cycleFinder = new _09_DirectedCycle(dg);
        if (!cycleFinder.hasCycle()) {
            _10_DepthFirstOrder depthFirstOrder = new _10_DepthFirstOrder(dg);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String separator = args[1];
        _08_SymbolDigraph sg = new _08_SymbolDigraph(filename, separator);

        _4_5_Topological top = new _4_5_Topological(sg.g());

        for (Integer v : top.order) {
            StdOut.println(sg.name(v));
        }

    }

}
