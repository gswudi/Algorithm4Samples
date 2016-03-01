package com.example._2sort;

import edu.princeton.cs.algs4.StdRandom;

public abstract class Sort {

    public abstract void sort(Comparable[] array);

    protected boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    protected void exchange(Comparable[] array, int i, int j) {
        Comparable t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1]))
                return false;
        }
        return true;
    }


    public static class Selection extends Sort {
        @Override
        public void sort(Comparable[] array) {
            for (int i = 0; i < array.length - 1; i++) {
                int min = i;
                for (int j = i; j < array.length; j++) {
                    if (less(array[j], array[min])) {
                        min = j;
                    }
                }
                exchange(array, i, min);
            }
            assert isSorted(array);
        }
    }

    public static class Insertion extends Sort {
        @Override
        public void sort(Comparable[] array) {
            for (int i = 0; i < array.length; i++) {
                for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
                    exchange(array, j, j - 1);
                }
            }
        }
    }

    public static class Shell extends Sort {
        @Override
        public void sort(Comparable[] array) {
            int len = array.length;
            int h = 1;
            while (h < len / 3) h = 3 * h + 1;
            while (h >= 1) {
                for (int i = h; i < len; i++) {
                    for (int j = i; j >= h && less(array[j], array[j - h]); j -= h)
                        exchange(array, j, j - h);
                }
                h /= 3;
            }
        }
    }

    public static abstract class Merge extends Sort {
        protected static Comparable[] aux;

        public void merge(Comparable[] a, int lo, int mid, int hi) {
            int i = lo, j = mid + 1;
            System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }

    }

    public static class MergeTD extends Merge {
        @Override
        public void sort(Comparable[] array) {
            aux = new Comparable[array.length];
            sort(array, 0, array.length - 1);
        }

        private void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return;
            int mid = (lo + hi) / 2;
            sort(a, lo, mid);
            sort(a, mid + 1, hi);
            merge(a, lo, mid, hi);
        }
    }

    public static class MergeBU extends Merge {

        @Override
        public void sort(Comparable[] array) {
            final int length = array.length;
            aux = new Comparable[length];
            for (int size = 1; size < length; size *= 2)
                for (int lo = 0; lo < length - size; lo += size * 2)
                    merge(array, lo, lo + size - 1, Math.min(lo + 2 * size - 1, length - 1));
        }
    }

    public static class Quicksort extends Sort {

        private int M;

        @Override
        public void sort(Comparable[] array) {
            StdRandom.shuffle(array);
            sort(array, 0, array.length - 1);
        }

        private void sort(Comparable[] array, int lo, int hi) {
            M = 3;
            if (hi <= lo + M) {
                insertionSort(array, lo, hi);
            } else {
                int j = partition(array, lo, hi);
                sort(array, lo, j - 1);
                sort(array, j + 1, hi);
            }
        }

        private int partition(Comparable[] a, int lo, int hi) {
            int left = lo, right = hi + 1;
            Comparable v = a[lo];
            while (true) {
                while (less(a[++left], v)) if (left == hi) break;
                while (less(v, a[--right])) if (right == lo) break;
                if (left >= right) break;
                exchange(a, left, right);
            }
            exchange(a, lo, right);
            return right;
        }

        private void insertionSort(Comparable[] array, int lo, int high) {
            for (int i = lo; i < high; i++) {
                for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
                    exchange(array, j, j - 1);
                }
            }
        }
    }

    public static class Quicksort3way extends Quicksort {
        @Override
        public void sort(Comparable[] array) {
            sort(array, 0, array.length - 1);
        }

        private void sort(Comparable[] array, int lo, int hi) {
            if (hi <= lo) return;
            int lt = lo, i = lo + 1, gt = hi;
            Comparable v = array[lo];
            while (i <= gt) {
                int cmp = array[i].compareTo(v);
                if (cmp < 0) exchange(array, lt++, i++);
                else if (cmp > 0) exchange(array, i, gt--);
                else i++;
            }
            sort(array, lo, lt - 1);
            sort(array, gt + 1, hi);
        }

    }

    public static class Heapsort extends Sort {

        @Override
        public void sort(Comparable[] array) {
            int N = array.length;
            for (int k = N / 2 - 1; k >= 0; k--)
                sink(array, k, N);
            while (N > 1) {
                exchange(array, 0, --N);
                sink(array, 0, N);
            }
        }

        private void sink(Comparable[] pq, int k, int N) {
            while (2 * k + 1 < N) {
                int j = 2 * k + 1;
                if (j < N - 1 && less(pq, j, j + 1)) j++;
                if (!less(pq, k, j)) break;
                exchange(pq, k, j);
                k = j;
            }
        }

        private void swim(Comparable[] pq, int k) {
            while (k > 1 && less(pq, (k - 1) / 2, k)) {
                exchange(pq, (k - 1) / 2, k);
                k = (k - 1) / 2;
            }
        }

        private boolean less(Comparable[] pq, int j, int i) {
            return less(pq[j], pq[i]);
        }

    }

}
