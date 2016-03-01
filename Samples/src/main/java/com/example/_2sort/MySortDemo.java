package com.example._2sort;

import edu.princeton.cs.algs4.StdOut;


public class MySortDemo {

    public static void main(String[] args) {
        String[] array = "S O R T E X A M P L E".split(" ");
        Sort sorter = new Sort.Heapsort();
        sorter.sort(array);
        show(array);
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

}
