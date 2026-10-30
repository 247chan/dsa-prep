package basics;

public class AVL {
    static class Node {
        int key, h;
        Node left, right;

        Node(int k) {
            key = k;
            h = 0;
        }
    }

    Node root;

    static int height(Node n) {
        return n == null ? -1 : n.h;
    }

    static void update(Node n) {
        n.h = 1 + Math.max(height(n.left), height(n.right));
    }

    static int bf(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static Node findMax(Node n) {
        if (n == null)
            return null;
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    // LL heavy
    static Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        update(y); // child first
        update(x);

        return x;
    }

    // RR heavy
    static Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        update(x); // child first
        update(y);

        return y;
    }

    static Node rebalance(Node n) {
        update(n); // update before calculation of the balance factor

        int bf = bf(n);

        // left heavy
        if (bf > 1) {
            if (bf(n.left) < 0) {
                n.left = rotateLeft(n.left);
            }
            return rotateRight(n);
        }

        // right heavy
        if (bf < -1) {
            if (bf(n.right) > 0) {
                n.right = rotateRight(n.right);
            }
            return rotateLeft(n);
        }

        return n;
    }

    // insert k in the tree with this node
    void insert(int k) {
        root = insert(root, k);
    }

    static Node insert(Node n, int k) {
        if (n == null)
            return new Node(k);

        if (k < n.key) {
            n.left = insert(n.left, k);
        } else if (k > n.key) {
            n.right = insert(n.right, k);
        } else {
            return n; // if exists, return that node
        }

        return rebalance(n); // always rebalance after insert one node
    }

    void delete(int k) {
        root = delete(root, k);
    }

    static Node delete(Node n, int k) {
        if (n == null)
            return null;
        if (k < n.key) {
            n.left = delete(n.left, k);
        } else if (k > n.key) {
            n.right = delete(n.right, k);
        } else {
            if (n.left == null)
                return n.right;
            if (n.right == null)
                return n.left;

            Node leftGreatest = findMax(n.left);
            n.key = leftGreatest.key;
            n.left = delete(n.left, leftGreatest.key);
        }
        return rebalance(n);
    }
}
