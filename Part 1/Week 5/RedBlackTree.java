public class RedBlackTree<Key extends Comparable<Key>, Value> {
    Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        boolean color;

        Node(Key k, Value v, boolean c, Node l, Node r) {
            key = k;
            val = v;
            color = c;
            left = l;
            right = r;
        }

        Node(Key k, Value v, boolean c) {
            key = k;
            val = v;
            color = c;
        }
    }

    // checks if a given node is red or not
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // gives me a value of a given key(null if doesn't exist)
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    // rotates a given node's color to the left
    private Node rotateLeft(Node h) {
        if (!isRed(h.right))
            return h;
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // rotates a given node's color to the right
    private Node rotateRight(Node h) {
        if (!isRed(h.left))
            return h;
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // checks if both childrens are red and flip their colors
    private void flipColors(Node h) {
        if (isRed(h)) return;
        else if (!isRed(h.left)) return;
        else if (!isRed(h.right)) return;
        h.color = RED;
        h.right.color = BLACK;
        h.left.color = BLACK;
    }

    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    private Node put(Node h, Key k, Value v) {
        if (h == null) return new Node(k, v, RED);
        int cmp = k.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, k, v);
        else if (cmp > 0) h.right = put(h.right, k, v);
        else h.val = v;

        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        return h;

    }

}
