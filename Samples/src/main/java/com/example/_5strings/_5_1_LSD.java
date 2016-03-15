package com.example._5strings;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 低位优先排序 least-significant-digit first string sort
 * Created by gaosheng on 16-3-14.
 */
public class _5_1_LSD {

    public static void sort(String[] a, int w) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            for (int i = 0; i < N; i++) {
                int dest = count[a[i].charAt(d)]++;
                aux[dest] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        int N = a.length;

        int W = a[0].length();
        for (int i = 0; i < N; i++) {
            assert a[i].length() == W;
        }

        sort(a, W);

        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
    }

}
