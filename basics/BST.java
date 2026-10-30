package basics;

import java.util.*;

/*
 * insert 7,20,5,15,10,4,33,2,25,6
 *
 *            7
 *          /   \
 *         5     20
 *        / \    / \
 *       4   6  15  33
 *      /      /    /
 *     2      10   25
 */

public class BST {
    static class Node {
        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    Node root = null;

    // ---------------- insert --------------------
    // ITERATIVE
    void insert(int d) {
        if (root == null) {
            root = new Node(d);
            return;
        }

        Node cur = root;
        while (true) {
            if (d < cur.val) {
                if (cur.left == null) {
                    cur.left = new Node(d);
                    return;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Node(d);
                    return;
                }
                cur = cur.right;
            }
        }
    }

    // RECURSIVE
    Node insertRecursive(Node node, int d) {
        if (node == null)
            return new Node(d);

        if (d < node.val) {
            node.left = insertRecursive(node.left, d);
        } else {
            node.right = insertRecursive(node.right, d);
        }

        return node;
    }

    // ----------------------- search -------------------------
    Node searchIteration(int d) {
        Node cur = root;
        while (cur != null && cur.val != d) {
            cur = d < cur.val ? cur.left : cur.right;
        }

        return cur;
    }

    Node searchRecursion(Node node, int d) {
        if (node == null)
            return null;
        if (d == node.val)
            return node;

        if (d < node.val)
            return searchRecursion(node.left, d);

        return searchRecursion(node.right, d);

    }

    // ---------------- traversal printing ---------------
    void printPreOrder(Node n) {
        if (n == null)
            return;
        System.out.println(n.val + " ");
        printPreOrder(n.left);
        printPreOrder(n.right);
    }

    void printInOrder(Node n) {
        if (n == null)
            return;
        printPreOrder(n.left);
        System.out.println(n.val + " ");
        printPreOrder(n.right);
    }

    void printPostOrder(Node n) {
        if (n == null)
            return;
        printPreOrder(n.left);
        printPreOrder(n.right);
        System.out.println(n.val + " ");
    }

    // ------------------ Traversal ------------------------
    // collect into list and use it
    void preOrder(Node n, List<Integer> out) {
        if (n == null)
            return;
        out.add(n.val);
        preOrder(n.left, out);
        preOrder(n.right, out);
    }

    void inOrder(Node n, List<Integer> out) {
        if (n == null)
            return;
        inOrder(n.left, out);
        out.add(n.val);
        inOrder(n.right, out);
    }

    void postOrder(Node n, List<Integer> out) {
        if (n == null)
            return;
        postOrder(n.left, out);
        postOrder(n.right, out);
        out.add(n.val);
    }

    // ----------------- MIN / MAX -------------
    Node findMin(Node n) {
        if (n == null)
            return null;
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    Node findMax(Node n) {
        if (n == null)
            return null;
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    // ------------ Height, Size, Leaves ----------------
    int height(Node n) {
        if (n == null)
            return -1; // empty tree is -1
        return 1 + Math.max(height(n.left), height(n.right));
    }

    int size(Node n) {
        if (n == null)
            return 0;
        return 1 + size(n.left) + size(n.right);
    }

    int countLeaves(Node n) {
        if (n == null)
            return 0;
        if (n.left == null && n.right == null)
            return 1;
        return countLeaves(n.left) + countLeaves(n.right);
    }

    // -------------- level order ------------
    List<Integer> levelOrder() {
        List<Integer> out = new ArrayList<>();
        if (root == null)
            return out;
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            out.add(cur.val);
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
        return out;
    }

    // ----------------- DELETE (leaf, one child, two child) -----------
    Node delete(Node node, int d) {
        if (node == null)
            return null;

        if (d < node.val) {
            node.left = delete(node.left, d);
        } else if (d > node.val) {
            node.right = delete(node.right, d);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node leftOldest = findMax(node.left);
            node.val = leftOldest.val;
            node.left = delete(node.left, leftOldest.val);
        }

        return node;
    }

    // Code challenges
    int rangeSum(Node n, int low, int high) {
        if (n == null)
            return 0;
        if (n.val < low)
            return rangeSum(n.right, low, high);
        if (n.val > high)
            return rangeSum(n.left, low, high);
        return n.val + rangeSum(n.left, low, high) + rangeSum(n.right, low, high);
    }

    boolean isValidBST(Node n, long low, long high) {
        if (n == null)
            return true;
        if (n.val < low || n.val > high)
            return false;

        return isValidBST(n.left, low, (long) n.val - 1) && isValidBST(n.right, n.val, high);
    }

    // k-th Smallest
    // int kthSmallest(int k) {
    // int[] count = { 0 };
    // int[] ans = { -1 };
    // kth(root, k, count, ans);
    // return ans[0];
    // }

    // private void kth(Node n, int k, int[] count, int[] ans) {
    // if (n == null || ans[0] != -1)
    // return;

    // kth(n.left, k, count, ans);
    // if (++count[0] == k) {
    // ans[0] = n.val;
    // return;
    // }
    // kth(n.right, k, count, ans);
    // }

    int kthSmallest(int k) { // 1-based
        int[] count = { 0 };
        int[] ans = { -1 };
        kth(root, k, count, ans);
        return ans[0];
    }

    private void kth(Node n, int k, int[] count, int[] ans) {
        if (n == null || ans[0] != -1)
            return;
        kth(n.left, k, count, ans);
        if (++count[0] == k) {
            ans[0] = n.val;
            return;
        }
        kth(n.right, k, count, ans);
    }

    // lowest common ancestor
    Node lca(Node n, int a, int b) {
        if (n == null)
            return null;
        if (a < n.val && b < n.val) {
            return lca(n.left, a, b);
        }
        if (a > n.val && b > n.val) {
            return lca(n.right, a, b);
        }

        return n;
    }

    // ================= TESTER =================
    static int pass = 0, fail = 0;

    static void check(String name, Object actual, Object expected) {
        boolean ok = Objects.equals(actual, expected);
        if (ok)
            pass++;
        else
            fail++;
        System.out.println((ok ? "[PASS] " : "[FAIL] ") + name
                + (ok ? "" : "  got " + actual + "  expected " + expected));
    }

    static BST build() {
        BST t = new BST();
        for (int v : new int[] { 7, 20, 5, 15, 10, 4, 33, 2, 25, 6 })
            t.insert(v);
        return t;
    }

    static List<Integer> in(BST t) {
        List<Integer> l = new ArrayList<>();
        t.inOrder(t.root, l);
        return l;
    }

    public static void main(String[] args) {
        BST t = build();

        List<Integer> l = new ArrayList<>();
        t.preOrder(t.root, l);
        check("preorder", l, Arrays.asList(7, 5, 4, 2, 6, 20, 15, 10, 33, 25));
        check("inorder", in(t), Arrays.asList(2, 4, 5, 6, 7, 10, 15, 20, 25, 33));
        l = new ArrayList<>();
        t.postOrder(t.root, l);
        check("postorder", l, Arrays.asList(2, 4, 6, 5, 10, 15, 25, 33, 20, 7));
        check("levelOrder", t.levelOrder(), Arrays.asList(7, 5, 20, 4, 6, 15, 33, 2, 10, 25));

        check("searchRecursion(15) found", t.searchRecursion(t.root, 15) != null, true);
        check("searchRecursion(99) not found", t.searchRecursion(t.root, 99) == null, true);
        check("searchIteration(25) found", t.searchIteration(25) != null, true);
        check("searchIteration(8) not found", t.searchIteration(8) == null, true);

        check("findMin", t.findMin(t.root).val, 2);
        check("findMax", t.findMax(t.root).val, 33);
        check("height", t.height(t.root), 3);
        check("height(empty)", t.height(null), -1);
        check("size", t.size(t.root), 10);
        check("countLeaves", t.countLeaves(t.root), 4);

        BST r = new BST();
        for (int v : new int[] { 7, 20, 5, 15, 10, 4, 33, 2, 25, 6 })
            r.root = r.insertRecursive(r.root, v);
        check("insertRecursive same tree", in(r), in(t));

        check("rangeSum(4,8)", t.rangeSum(t.root, 4, 8), 22);
        check("kthSmallest(3)", t.kthSmallest(3), 5);
        check("kthSmallest(4)", t.kthSmallest(4), 6);
        check("kthSmallest(10)", t.kthSmallest(10), 33);
        check("lca(2,6)", t.lca(t.root, 2, 6).val, 5);
        check("lca(6,10)", t.lca(t.root, 6, 10).val, 7);
        check("isValidBST(good)", t.isValidBST(t.root, Long.MIN_VALUE, Long.MAX_VALUE), true);

        BST bad = new BST();
        bad.root = new Node(5);
        bad.root.left = new Node(3);
        bad.root.right = new Node(8);
        bad.root.right.left = new Node(4); // 4 < 5 but sits in right subtree

        check("isValidBST(bad)", bad.isValidBST(bad.root, Long.MIN_VALUE, Long.MAX_VALUE), false);

        // delete
        t = build();
        t.root = t.delete(t.root, 6); // leaf
        check("delete leaf 6", in(t), Arrays.asList(2, 4, 5, 7, 10, 15, 20, 25, 33));
        t = build();
        t.root = t.delete(t.root, 4); // one child
        check("delete one-child 4", in(t), Arrays.asList(2, 5, 6, 7, 10, 15, 20, 25, 33));
        t = build();
        t.root = t.delete(t.root, 20); // two children
        check("delete two-children 20", in(t), Arrays.asList(2, 4, 5, 6, 7, 10, 15, 25, 33));
        check("20 replaced by predecessor 15", t.root.right.val, 15);
        t = build();
        t.root = t.delete(t.root, 7); // root
        check("delete root 7", in(t), Arrays.asList(2, 4, 5, 6, 10, 15, 20, 25, 33));
        check("new root is predecessor 6", t.root.val, 6);
        t = build();
        t.root = t.delete(t.root, 99); // missing
        check("delete missing 99", in(t).size(), 10);

        BST p = build();
        System.out.print("\nprintPreOrder : ");
        p.printPreOrder(p.root);
        System.out.print("\nprintInOrder  : ");
        p.printInOrder(p.root);
        System.out.print("\nprintPostOrder: ");
        p.printPostOrder(p.root);
        System.out.println("\n\n" + pass + " passed, " + fail + " failed");
    }

}
