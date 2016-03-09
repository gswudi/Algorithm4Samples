package com.example._4graphs.model;

import com.example._4graphs._4_2_BreadthFirstPaths;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by gaosheng on 16-3-4.
 */
public class _07_DegreeOfSeparation {

    public static void main(String[] args) {
        _05_SymbolGraph sg = new _05_SymbolGraph(args[0],args[1]);
        _01_Graph g= sg.g();

        String source = args[2];
        if(!sg.contains(source)){
            StdOut.println(source + "not in database");
            return;
        }

        int s= sg.index(source);
        _4_2_BreadthFirstPaths bfs = new _4_2_BreadthFirstPaths(g,s);

        while (!StdIn.isEmpty()){
            String sink = StdIn.readLine();
            if(sg.contains(sink)){
                int t = sg.index(sink);
                if(bfs.hasPathTo(t))
                    for (int v :bfs.pathTo(t))
                        StdOut.println("    "+sg.name(v));
                else StdOut.println("Not connected");
            }
            else StdOut.println("Not in database");
        }
    }
}
