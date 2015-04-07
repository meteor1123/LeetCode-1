/*
	Populating Next Right Pointers in Each Node

	Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }

    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

	Initially, all next pointers are set to NULL.

	Note:

		You may only use constant extra space.
		You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
	For example,
	Given the following perfect binary tree,

		 1
       /  \
      2    3
     / \  / \
    4  5  6  7
 After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/

public class Solution {
	//dfs
    public void connect(TreeLinkNode root) {
        if (root == null) {
        	return ;
        }
        //left isn't empty, so let left node point to the right node/
        if (root.left != null) {
        	root.left.next = root.right;
        }

        //right isn't empty,  we hava two case
        /*

        	case 1: Node has right, but the next point is nnull,like point 3
        			             1  -> NULL
						       /  \
						      2 -> 3 -> NULL
						     / \  / \
						    4->5->6->7 -> NULL
                            
			case 2: Node has left, but the next point is not null, like point 2
						         1 -> NULL
						       /  \
						      2 -> 3 -> NULL
						     / \  / \
						    4->5->6->7 -> NULL,

        */
        if (root.right != null) {
        	if (root.next != null) {
        		root.right.next = root.next.left;
        	} else {
        		root.right.next = null;
        	}
        }
        connect(root.left);
        connect(root.right);
    }

    //BFS
    /*
    	How to use BFS to traverse and make connection?
    	Tha key point is next pointer! Every time we check the node has whether or not next
    	cause next pointer point to the node in the same level,
    	if has we make cur = cur.next;
    	if hasn't we make cur = root.left;

    */
    public void connect(TreeLinkNode root) {
    	if (root == null) {
    		return;
    	}
    	TreeLinkNode cur = root;
    	while (cur != null && cur.left != null) {
    		cur.left.next = cur.right;
    		if (cur.next != null) {
    			cur.right.next = cur.next.left;
    			cur = cur.next;
    		} else {
    			root = root.left;
    			cur = root;
    		}
    	}

    }
}