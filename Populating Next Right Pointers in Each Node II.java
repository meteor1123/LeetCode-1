/*
	Populating Next Right Pointers in Each Node II 
	Follow up for problem "Populating Next Right Pointers in Each Node".

	What if the given tree could be any binary tree? Would your previous solution still work?

	Note:

	You may only use constant extra space.
	For example,
	Given the following binary tree,
				1
		       /  \
		      2    3
		     / \    \
		    4   5    7
    After calling your function, the tree should look like:
    	1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/
public class Solution {

	//based on level order traversal
	public void connect(TreeLinkNode root) {
		TreeLinkNode head = root; //The left most node in the lower level
		TreeLinkNode pre = null; //The previous node in the lower level
		TreeLinkNode cur = null; //The current node in the upper level

		while (head != null) {
			//move to next level
			cur = head;
			head = null;
			pre = null;
			while (cur != null) { //iterate on the current level
				//left child
				if (cur.left != null) {
					if (pre != null) {
						pre.next = cur.left;
					} else {
						head = cur.left;
					}
					pre = cur.left;
				}

				//right child
				if (cur.right != null) {
					if (pre != null) {
						pre.next = cur.right;
					} else {
						head = cur.right;
					}
					pre = cur.right;
				}
				//move to next node
				cur = cur.next;
			}

		}
	}
}