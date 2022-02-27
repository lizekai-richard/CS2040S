/**
 * ScapeGoat Tree class
 * <p>
 * This class contains some basic code for implementing a ScapeGoat tree. This version does not include any of the
 * functionality for choosing which node to scapegoat. It includes only code for inserting a node, and the code for
 * rebuilding a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     * <p>
     * This class holds the data for a node in a binary tree.
     * <p>
     * Note: we have made things public here to facilitate problem set grading/testing. In general, making everything
     * public like this is a bad idea!
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;

        public int weight;

        TreeNode(int k) {
            key = k;
            weight = 1;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    public int countHelper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftCount = countHelper(root.left);
        int rightCount = countHelper(root.right);
        return 1 + leftCount + rightCount;
    }
    /**
     * Counts the number of nodes in the specified subtree.
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node, Child child) {
        // TODO: Implement this
        if(child == Child.LEFT) {
            return countHelper(node.left);
        } else {
            return countHelper(node.right);
        }
    }

    public int enumerateHelper(TreeNode root, TreeNode[] nodeList, int size) {
        if(root == null) {
            return size;
        }
        size = enumerateHelper(root.left, nodeList, size);
        nodeList[size++] = root;
        return enumerateHelper(root.right, nodeList, size);

    }
    /**
     * Builds an array of nodes in the specified subtree.
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */
    TreeNode[] enumerateNodes(TreeNode node, Child child) {
        // TODO: Implement this
        int subTreeSize = countNodes(node, child);
        TreeNode[] nodeList = new TreeNode[subTreeSize];

        if(child == Child.LEFT) {
            enumerateHelper(node.left, nodeList, 0);
        } else {
            enumerateHelper(node.right, nodeList, 0);
        }

        return nodeList;
    }

    /**
     * Builds a tree from the list of nodes Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */

    public TreeNode buildTreeHelper(int l, int r, TreeNode[] nodeList) {
        if(l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        TreeNode root = nodeList[mid];

        root.left = buildTreeHelper(l, mid - 1, nodeList);
        root.right = buildTreeHelper(mid + 1, r, nodeList);

        return root;
    }

    TreeNode buildTree(TreeNode[] nodeList) {
        // TODO: Implement this
        int n = nodeList.length;
        return buildTreeHelper(0, n - 1, nodeList);
    }

    public void fixWeightsHelper(TreeNode node) {
        if(node == null) {
            return ;
        }
        int leftWeight = countNodes(node, Child.LEFT);
        int rightWeight = countNodes(node, Child.RIGHT);
        node.weight = leftWeight + rightWeight + 1;

        fixWeightsHelper(node.left);
        fixWeightsHelper(node.right);
    }

    public void fixWeights(TreeNode node, Child child) {
        if(child == Child.LEFT) {
            fixWeightsHelper(node.left);
        } else {
            fixWeightsHelper(node.right);
        }
    }

    /**
     * Determines if a node is balanced. If the node is balanced, this should return true. Otherwise, it should return
     * false. A node is unbalanced if either of its children has weight greater than 2/3 of its weight.
     *
     * @param node a node to check balance on
     * @return true if the node is balanced, false otherwise
     */
    public boolean checkBalance(TreeNode node) {
        // TODO: Implement this
        if(node == null) {
            return true;
        }
        int nodeWeight = node.weight;
        int leftChildWeight =  node.left != null ? node.left.weight : 0;
        int rightChildWeight = node.right != null ? node.right.weight : 0;
        return (leftChildWeight <= 2.0 * nodeWeight / 3) && (rightChildWeight <= 2.0 * nodeWeight / 3);
    }

    /**
     * Rebuilds the specified subtree of a node.
     *
     * @param node  the part of the subtree to rebuild
     * @param child specifies which child is the root of the subtree to rebuild
     */
    public void rebuild(TreeNode node, Child child) {
        // Error checking: cannot rebuild null tree
        if (node == null) return;
        // First, retrieve a list of all the nodes of the subtree rooted at child
        TreeNode[] nodeList = enumerateNodes(node, child);
        // Then, build a new subtree from that list
        TreeNode newChild = buildTree(nodeList);
        // Finally, replace the specified child with the new subtree
        if (child == Child.LEFT) {
            node.left = newChild;
        } else if (child == Child.RIGHT) {
            node.right = newChild;
        }
        fixWeights(node, child);
    }

    /**
     * Inserts a key into the tree.
     *
     * @param key the key to insert
     */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode node = root;

        while (true) {
            if (key <= node.key) {
                if (node.left == null) {
                    break;
                }
                node.weight += 1;
                node = node.left;
            } else {
                if (node.right == null) {
                    break;
                }
                node.weight += 1;
                node = node.right;
            }
        }
        node.weight += 1;
        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }

        node = root;
        while (true) {
            if (key <= node.key) {
                if (node.left != null) {
                    if(!checkBalance(node.left)) {
                        rebuild(node, Child.LEFT);
                        break;
                    } else {
                        node = node.left;
                    }
                } else {
                    break;
                }
            } else {
                if (node.right != null) {
                    if(!checkBalance(node.right)) {
                        rebuild(node, Child.RIGHT);
                        break;
                    } else {
                        node = node.right;
                    }
                } else {
                    break;
                }
            }
        }
    }

    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        tree.rebuild(tree.root, Child.RIGHT);
    }
}
