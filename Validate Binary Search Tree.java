/*
    Validate Binary Search Tree 
    Given a binary tree, determine if it is a valid binary search tree (BST).

    Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.
    confused what "{1,#,2,3}" means? 
*/

public class Solution {
    //Recursive
    public boolean isValidBST(TreeNode root) {  
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }  
      
    public boolean isBST(TreeNode node, long low, long high){  
        if(node == null)  
            return true;  
            
        if(low < node.val && node.val < high)
            return isBST(node.left, low, node.val) && isBST(node.right, node.val, high);  
        else  
            return false;  
    }

    //Non- recursive
    public boolean isValidBST(TreeNode root) {  
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (pre != null && node.val <= pre.val) {
                    return false;
                }
                pre = node;
                cur = node.right;
            }
        }
        return true;
    }

}