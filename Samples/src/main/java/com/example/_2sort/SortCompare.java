package com.example._2sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {

    public static double time(com.example._2sort.Sort alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        alg.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(com.example._2sort.Sort alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        int N = 1000;//数组长度
        int T = 1000;//数组数量
        final com.example._2sort.Sort alg1 = new com.example._2sort.Sort.Insertion();
        final com.example._2sort.Sort alg2 = new com.example._2sort.Sort.Selection();
        final double t1 = timeRandomInput(alg1, N, T);
        final double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles\n    %s is", N, alg1.getClass().getSimpleName());
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2.getClass().getSimpleName());
    }

}
