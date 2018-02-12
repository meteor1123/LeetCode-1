/*
	House Robber III

	The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

	Determine the maximum amount of money the thief can rob tonight without alerting the police.

	Example 1:
	     3
	    / \
	   2   3
	    \   \ 
	     3   1
	Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
	Example 2:
	     3
	    / \
	   4   5
	  / \   \ 
	 1   3   1
	Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

/*
	树形动规。设状态 f(root) 表示抢劫root为根节点的二叉树，root可抢也可能不抢，能得到的最大金钱，g(root)表示抢劫root为根节点的二叉树，但不抢root，能得到的最大金钱，
	则状态转移方程为:
		f(root) = max{f(root.left) + f(root.right), g(root.left) + g(root.right) + root.val}
		g(root) = f(root.left) + f(root.right)
*/

//Solution1
public class Solution {
	public int rob(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }
	    int val = 0;
	    if (root.left != null) {
	        val += rob(root.left.left) + rob(root.left.right);
	    }
	    if (root.right != null) {
	        val += rob(root.right.left) + rob(root.right.right);
	    }
	    return Math.max(val + root.val, rob(root.left) + rob(root.right));
	}
}


//Solution2
public class Solution {
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    public int[] helper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        // [0] is max value if not rob current one
        // [1] is max value if rob current one
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int[] res = new int[2];
        
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}