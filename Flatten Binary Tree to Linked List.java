/*
	Flatten Binary Tree to Linked List leetcode
	Given a binary tree, flatten it to a linked list in-place.

	For example,
	Given

	         1
	        / \
	       2   5
	      / \   \
	     3   4   6
	The flattened tree should look like:
	   1
	    \
	     2
	      \
	       3
	        \
	         4
	          \
	           5
	            \
	             6
*/

/*
	Solution1:
		，这道题就是使用先序遍历，遍历到的值作为新的右孩子存起来，左孩子变为空。
		注意的是，因为右孩子会更新，所以为了递归右子树，要在更新之前提前保存右孩子。
		整个程序需要维护一个全局变量，保存当前所遍历的节点。
*/
public class Solution {
	//recursive
	TreeNode lastvisited = null;
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}

		//save the realright treenode
		TreeNode realright = root.right;
		if (lastvisited != null) {
			lastvisited.left = null;
			lastvisited.right = root;
		}
		lastvisited = root;
		flatten(root.left);
		flatten(realright);
	}

	//non-recursive
	/*
		此题还有不用递归方法解决的方法，那就是使用栈。
		对整棵树一直向右子树方向遍历。当遍历的节点有右孩子时，就将其入栈。
		有左孩子时，将其更新为当前节点的右孩子，左孩子置空。当左孩子为空时而栈不空时，
		就弹出栈，作为右孩子。
	*/

	public void flatten(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		while (p != null || !stack.empty()) {
			if (p.right != null) {
				stack.push(p.right);
			}

			if (p.left != null) {
				p.right = p.left;
				p.left = null;
			} else if (!stack.isEmpty()) {
				p.right = stack.pop();
			}

			p = p.right;
		}
	}
}









