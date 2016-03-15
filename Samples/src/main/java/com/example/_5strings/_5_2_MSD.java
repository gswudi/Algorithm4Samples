package com.example._5strings;

/**
 * most-signification-digit first string sort
 * Created by gaosheng on 16-3-15.
 */
public class _5_2_MSD {

    private static final int R = 256;   //radix
    private static final int M = 15;    //小数组的切换阈值
    private static String[] aux;        //数据分类的辅助数组

    /**
     * @return -1 if d >= s.length()
     */
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        aux = new String[a.length];
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi < lo) return;
        int[] count = new int[R + 2];   //count[0] = 0, count[1] is index of "",count[r+2] is index of r
        for (int i = lo; i <= hi; i++) {
            int index = charAt(a[i], d) + 2;
            count[index]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {
            int index = count[charAt(a[i], d) + 1];
            aux[index] = a[i];
            count[charAt(a[i], d) + 1]++;
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

}
