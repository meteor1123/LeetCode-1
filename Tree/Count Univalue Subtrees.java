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
        all(root, 0);
        return count;
    }
    public boolean all(TreeNode root, int val) {
        if (root == null)
            return true;
        if (!all(root.left, root.val) | !all(root.right, root.val))// 要是 || 或，计算第一个的结果如果是正确的话，就不会计算后面的，就会漏算后面的计数，这就是short-circuit
                                                                   // | 或 无论前面的结果如何，都会将所有表达式运行
            return false;
        count++;
        return root.val == val;
    }
}