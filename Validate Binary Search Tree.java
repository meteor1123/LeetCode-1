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
    public boolean isValidBST(TreeNode root) {  
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }  
      
    public boolean isBST(TreeNode node, int low, int high){  
        if(node == null)  
            return true;  
            
        if(low < node.val && node.val < high)
            return isBST(node.left, low, node.val) && isBST(node.right, node.val, high);  
        else  
            return false;  
    }

}