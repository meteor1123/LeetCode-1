/*
	Binary Tree Paths 
	Given a binary tree, return all root-to-leaf paths.

	For example, given the following binary tree:
		   1
		 /   \
		2     3
		 \
		  5
	All root-to-leaf paths are:
	["1->2->5", "1->3"]
*/



public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if (root != null) {
            dfs(root, "", res);
        }
        return res;
    }
    
    public void dfs(TreeNode root, String path, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }
        if (root.left != null) {
            dfs(root.left, path + root.val + "->", res);
        }
        if (root.right != null) {
            dfs(root.right, path + root.val + "->", res);
        }
    }
}