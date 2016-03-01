package com.example._3search;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private Node mRoot;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        Color color;

        public Node(Key key, Value val, int n, Color color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }

    }

    enum Color {
        RED, BLACK
    }

    Node rotateLeft(Node root) {
        Node x = root.right;
        root.right = x.left;
        x.left = root;
        x.color = root.color;
        root.color = Color.RED;
        x.N = root.N;
        root.N = 1 + size(root.left) + size(root.right);
        return x;
    }

    Node rotateRight(Node root) {
        Node x = root.left;
        root.left = x.right;
        x.right = root;
        x.color = root.color;
        root.color = Color.RED;
        x.N = root.N;
        root.N = size(root.left) + size(root.right) + 1;
        return x;
    }

    void flipColors(Node node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    public int size() {
        return size(mRoot);
    }

    private int size(Node root) {
        if (root == null) return 0;
        return root.N;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == Color.RED;
    }

    public void put(Key key, Value val) {
        mRoot =null;
    }

    private Node put(Node root, Key key, Value val) {
        if (root == null) return new Node(key, val, 1, Color.RED);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = put(root.left, key, val);
        else if (cmp > 0) root.right = put(root.right, key, val);
        else root.val = val;

        if (isRed(root.right) && !isRed(root.left)) root = rotateLeft(root);
        if (isRed(root.left) && isRed(root.left.left)) root = rotateRight(root);
        if (isRed(root.left) && isRed(root.right)) flipColors(root);

        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

}
