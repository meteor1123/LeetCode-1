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
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> item = new ArrayList<>();
        dfs(root, sum, res, item);
        return res;
    }
    public void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> item) {
        if (root == null) {
            return;
        }
        item.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(item));
        } else {
            dfs(root.left, sum - root.val, res, item);
            dfs(root.right, sum - root.val, res, item);
        }
        item.remove(item.size() - 1);
    }
}