package com.example._3search;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * ordered symbol table
 */
public class SymbolTable<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    public SymbolTable() {
        st = new TreeMap<Key, Value>();
    }

    public void put(Key key, Value value) {
        if (key == null) throw new NullPointerException("null key");
        if (value == null) st.remove(key);
        else st.put(key, value);
    }

    public Value get(Key key) {
        return st.get(key);
    }


    public void delete(Key key) {
        st.remove(key);
    }

    public boolean contains(Key key) {
        return st.containsKey(key);
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public int size() {
        return st.size();
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("called ceiling() with null key");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("called floor() with null key");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    public static void main(String[] args) {
//        testClient();
        performanceClient();
    }

    private static void performanceClient() {
        int minLen = 1;
        SymbolTable<String, Integer> st = new SymbolTable<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word));
        }

        String max = " ";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;
        StdOut.println(max + " " + st.get(max));
    }

    private static void testClient() {
        SymbolTable<String, Integer> st = new SymbolTable<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}
