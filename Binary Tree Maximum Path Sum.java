/*
	Binary Tree Maximum Path Sum

	Given a binary tree, find the maximum path sum.

	The path may start and end at any node in the tree.

	For example:
	Given the below binary tree,

	   1
      / \
     2   3 == 6

     Tags: tree, dfs
*/


public class Solution {
    public int maxPathSum(TreeNode root) {
    	int[] max = new int[1];
    	max[0] = Integer.MIN_VALUE;
    	findmax(root, max);
    	//if we use array ,we can recursively update the max value
    	return max[0];
    }

    public int findmax(TreeNode root, int[] max) {
    	if (root == null)
    		return 0;
    	int left = findmax(root.left, max);
    	int right = findmax(root.right, max);
    	//the maximum value of current level
    	int current = Math.max(root.val, Math.max(root.val + left, root.val + right));

    	//the maximum value of whole level
    	max[0] = Math.max(max[0], Math.max(current, root.val + left + right));
    	return current;
    }
}