/*
    Binary Search Tree Iterator
    Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
    Calling next() will return the next smallest number in the BST.
    Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    private Queue<Integer> queueOfInOrder = new LinkedList<Integer>();
    public BSTIterator(TreeNode root) {
        InOrder(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (queueOfInOrder.isEmpty())
            return false;
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        Integer headOfQueue = queueOfInOrder.poll();
        return headOfQueue;
    }
    
    private void InOrder(TreeNode root) {
        if (root != null) {
            InOrder(root.left);
            queueOfInOrder.add(root.val);
            InOrder(root.right);
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */