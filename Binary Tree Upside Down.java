/*
	Binary Tree Upside Down
	Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

	For example:
	Given a binary tree {1,2,3,4,5},
		   1
		  / \
		 2   3
		/ \
	   4   5

	return the root of the binary tree [4,5,2,#,#,3,1].
		 4
		/ \
	   5   2
		  / \
		 3   1
*/

/*
	这题有一个重要的限制就是，整个数的任何一个右孩子都不会再生枝节，基本就是一个梳子的形状。对于树类型的题目，
	首先可以想到一种递归的思路：把左子树继续颠倒，颠倒完后，原来的那个左孩子的左右孩子指针分别指向原来的根节点以及原来的右兄弟节点即可。
*/

public class Solution {
	public TreeNode UpsideDownBinaryTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode parent = root;
		TreeNode left = root.left;
		TreeNode right = root.right;
		if (left != null) {
			TreeNode res = UpsideDownBinaryTree(left);
			left.left = right;
			left.right = parent;
			return ret;
		}
		return root;
	}
}