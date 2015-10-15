/*
	Count Univalue Subtrees

	Given a binary tree, count the number of uni-value subtrees.

	A Uni-value subtree means all nodes of the subtree have the same value.

	For example:
	Given binary tree,
	              5
	             / \
	            1   5
	           / \   \
	          5   5   5
	return 4.
*/


//Solution1
public class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1];
        helper(root, count);
        return count[0];
    }

    private boolean helper(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }
        boolean left = helper(node.left, count);
        boolean right = helper(node.right, count);
        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }
}


//Solution2
public class Solution {
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        checkUniSubTree(root, 0);
        return count;
    }
    
    public boolean checkUniSubTree(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        if (!checkUniSubTree(root.left, root.val) | !checkUniSubTree(root.right, root.val)) {
            return false;
        }
        count++;
        return root.val == val;
    }
}