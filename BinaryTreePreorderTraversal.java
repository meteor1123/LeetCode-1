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

    //不是bfs，而是非递归的方法
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);//只要有左边的叶子节点就一直遍历入栈
                res.add(root.val);
                root = root.left;
            } else {
                root = stack.pop();//左边的遍历完了再出栈给root，将右节点的值                                       //加入结果
                root = root.right;
            }
        }
        return res;
    }
}