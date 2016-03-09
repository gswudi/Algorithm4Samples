package com.example._4graphs.model;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 有向图基于深度有向搜索的顶点排序
 * Created by gaosheng on 16-3-7.
 */
public class _10_DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public _10_DepthFirstOrder(_08_Digraph dg) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[dg.V()];

        for (int i = 0; i < dg.V(); i++) {
            if (!marked[i]) dfs(dg, i);
        }
    }

    private void dfs(_08_Digraph dg, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (Integer w : dg.adj(v)) {
            if (!marked[w])
                dfs(dg, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

}
