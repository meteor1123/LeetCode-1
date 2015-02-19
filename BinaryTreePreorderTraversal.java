/*
	Binary Tree Preorder Traversal 
*/

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (root != null) {
    		res.add(root.val);
    		res.addAll(root.left);
    		res.addAll(root.right);
    	}
    	return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (root == null)
    		return res;
    	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    	stack.push(root);
    	while (!stack.isEmpty()) {
    		TreeNode cur = stack.pop();
    		res.add(cur.val);
    		if (cur.right != null)
    			stack.push(cur.right);
    		if (cur.left != null)
    			stack.push(cur.left);
    	}
    }

    public List<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (root == null)
    		return res;
    	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    	while (root != null || !stack.isEmpty()) {
    		if (root != null) {
    			stack.push(root);
    			res.add(root.val);
    			root = root.left;
    		} else {
    			root = stack.pop();
    			root = root.right;
    		}
    	}
    	return res;
    }
}