/*
	Path Sum II 
	Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

	For example:
	Given the below binary tree and sum = 22,

	          5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

    return
	[
	   [5,4,11,2],
	   [5,8,4,5]
	]
*/


public class Solution {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return res;
		ArrayList<Integer> item = new ArrayList<Integer>();
		dfs(root, sum, res);
		return res;
	}

	public void dfs(TreeNode root, int sum, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item) {
		if (root == null)
			return ;
		item.add(root.val);
		sum = sum - root.val;
		if (root.left == null && root.right == null) {
			if (sum == 0) {
				res.add(new ArrayList<Integer>(item));
			}
		} else {
			if (root.left != null) {
				dfs(root.left, sum, res, item);
			}
			if (root.right != null) {
				dfs(root.right, sum, res, item);
			}
		}
		item.remove(item.size() - 1);
	}
}