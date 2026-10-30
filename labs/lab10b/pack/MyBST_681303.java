package pack;

public class MyBST_681303 extends MyBST_Basic_681303 {
    public MyBST_681303() {
        super();
    };

    public MyBST_681303(Integer[] input) {
        super(input);
    }

    public BNode getRoot() {
        return root;
    }

    public BNode findMin(BNode node) {
        if (node == null)
            throw new IllegalArgumentException("Empty Tree");
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public BNode findMax(BNode node) {
        if (node == null)
            throw new IllegalArgumentException("Empty Tree");
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void delete(BNode node, int d) {
        if (node == null)
            return;
        if (d < node.data) {
            delete(node.left, d);
        } else if (d > node.data) {
            delete(node.right, d);
        } else { // found
            if (node.left == null || node.right == null) { // 0 or 1 child
                BNode q = node.left == null ? node.right : node.left;
                if (node.parent != null) {
                    if (node.parent.left == node)
                        node.parent.left = q;
                    else
                        node.parent.right = q;

                    if (q != null)
                        q.parent = node.parent;
                } else {
                    root = q;
                    if (q != null)
                        q.parent = null; // Reset parent of the new root to null
                }
            } else { // 2 children
                BNode q = findMax(node.left);
                delete(node.left, q.data);

                if (node.parent == null) {
                    root = q;
                } else if (node.parent.left == node) {
                    node.parent.left = q;
                } else {
                    node.parent.right = q;
                }
                q.parent = node.parent;

                // migrate node's children
                q.left = node.left;
                if (q.left != null)
                    q.left.parent = q;

                q.right = node.right;
                if (q.right != null)
                    q.right.parent = q;
            }
        }
    }

    public BNode inorderPredecessor(int val) {
        BNode node = root;
        BNode pred = null;
        while (node != null) {
            if (val > node.data) {
                pred = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return pred;
    }

    public BNode inorderSuccessor(int val) {
        BNode node = root, succ = null;
        while (node != null) {
            if (val < node.data) {
                succ = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return succ;
    }

    public static void leftRotate(MyBST_681303 bst, BNode node) {
        if (node == null || node.right == null)
            return;
        BNode rightChild = node.right;
        BNode tmp = rightChild.left;

        // rotate
        rightChild.left = node;
        node.right = tmp;

        // update parents
        rightChild.parent = node.parent;
        node.parent = rightChild;
        if (tmp != null)
            tmp.parent = node;

        // update children
        if (rightChild.parent == null) {
            bst.root = rightChild; // node was root
        } else if (rightChild.parent.left == node) {
            rightChild.parent.left = rightChild;
        } else {
            rightChild.parent.right = rightChild;
        }
    }

    public static void rightRotate(MyBST_681303 bst, BNode node) {
        if (node == null || node.left == null)
            return;

        BNode leftChild = node.left;
        BNode tmp = leftChild.right;

        // rotate
        leftChild.right = node;
        node.left = tmp;

        // update parents
        leftChild.parent = node.parent;
        node.parent = leftChild;
        if (tmp != null) {
            tmp.parent = node;
        }

        // update children
        if (leftChild.parent == null) {
            bst.root = leftChild;
        } else if (leftChild.parent.left == node) {
            leftChild.parent.left = leftChild;
        } else {
            leftChild.parent.right = leftChild;
        }
    }

    public static void rightLeftRotate(MyBST_681303 bst, BNode node) {
        if (node == null || node.right == null)
            return;

        rightRotate(bst, node.right);
        leftRotate(bst, node);
    }
}
