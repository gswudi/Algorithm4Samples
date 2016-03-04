package com.example._4graphs;

/**
 * 判断graph是否为二分图
 * Created by gaosheng on 16-3-4.
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i])
                dfs(g, i);
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v])
                isTwoColor = false;
        }
    }

    public boolean isTwoColor() {
        return isTwoColor;
    }

}
