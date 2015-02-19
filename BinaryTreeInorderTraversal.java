/*
	Binary Tree Inorder Traversal 
	Given a binary tree, return the inorder traversal of its nodes' values.

	For example:
	Given binary tree {1,#,2,3},
		 1
	      \
	       2
	      /
	     3 
   return [1,3,2].
*/


//左 中 右， left -> val -> right
public class Solution {
	//Recursive version 1
	public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        dfs(root, res);
        return res;
    }
    
    public void dfs(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return ;
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }

    //Recursive version 2
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> treeVal = new ArrayList<Integer>();
        if(root != null){
            treeVal.addAll(inorderTraversal(root.left));
            treeVal.add(root.val);
            treeVal.addAll(inorderTraversal(root.right));
        }
        return treeVal;
    }

    //Iterative version 1
    public List<Integer> inorderTraversal(TreeNode root) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (root == null)
    		return res;
    	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    	while (root != null || !stack.isEmpty()) {
    		if (root != null) {
    			stack.push(root);
    			root = root.left;
    		} else {
    			root = stack.pop();
    			res.add(root.val);
    			root = root.right;
    		}
    	}
    }

    //Iterative version 2
    public List<Integer> inorderTraversal(TreeNode root) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (root == null)
    		return res;
    	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    	TreeNode cur = root;

    	while (cur != null || !stack.isEmpty()) {
    		while (cur != null) {
    			stack.push(cur);
    			cur = cur.next;
    		}
    		cur = stack.pop();
    		res.add(cur.val);
    		cur = cur.right;
    	}
    }

    //Morris traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        Treenode pre = null;

        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            }
            else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                else {
                    pre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
        
    }


}