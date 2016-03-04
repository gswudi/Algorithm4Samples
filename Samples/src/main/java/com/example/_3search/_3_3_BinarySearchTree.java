package com.example._3search;

import java.util.LinkedList;
import java.util.Queue;

public class _3_3_BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node mRoot;

    class Node {
        Key key;
        Value val;
        Node left, right;
        int N;

        public Node(Key key, Value val, Node left, Node right, int n) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
            N = n;
        }
    }

    public Value get(Key key) {
        return get(mRoot, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return get(root.left, key);
        if (cmp > 0) return get(root.right, key);
        return root.val;
    }

    public void put(Key key, Value val) {
        mRoot = put(mRoot, key, val);
    }

    private Node put(Node root, Key key, Value val) {
        if (root == null) return new Node(key, val, null, null, 1);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = put(root.left, key, val);
        else if (cmp > 0) root.right = put(root.right, key, val);
        else root.val = val;
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public Key min() {
        return min(mRoot).key;
    }

    private Node min(Node root) {
        if (root.left == null) return root;
        else return min(root.left);
    }

    private Key max() {
        return max(mRoot).key;
    }

    private Node max(Node root) {
        if (root.right == null) return root;
        return max(root.right);
    }


    public Key floor(Key key) {
        Node x = floor(mRoot, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root;
        if (cmp < 0) return floor(root.left, key);
        Node t = floor(root.right, key);
        if (t != null) return t;
        return root;
    }

    public Key select(int rank) {
        return select(mRoot, rank).key;
    }

    private Node select(Node root, int rank) {
        if (root == null) return null;
        int t = size(root.left);
        if (t > rank) return select(root.left, rank);
        if (t < rank) return select(root.right, rank - t - 1);
        return root;
    }

    public int rank(Key key) {
        return rank(mRoot, key);
    }

    private int rank(Node root, Key key) {
        if (root == null) return 0;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return rank(root.left, key);
        if (cmp > 0) return rank(root.right, key) + size(root.left) + 1;
        return size(root.left);
    }

    public void deleteMin() {
        mRoot = deleteMin(mRoot);
    }

    private Node deleteMin(Node root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public int size() {
        return size(mRoot);
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(mRoot, queue, lo, hi);
        return queue;
    }

    private void keys(Node root, Queue<Key> queue, Key lo, Key hi) {
        if (root == null) return;
        int cmplo = lo.compareTo(root.key);
        int cmphi = hi.compareTo(root.key);
        if (cmplo < 0) keys(root.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 9) queue.add(root.key);
        if (cmphi > 0) keys(root.right, queue, lo, hi);
    }


    public static void main(String[] args) {
    }

}
