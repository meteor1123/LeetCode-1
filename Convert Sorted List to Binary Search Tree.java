/*
	Convert Sorted List to Binary Search Tree
	Given a singly linked list where elements are sorted in ascending order, 
	convert it to a height balanced BST.
*/

public class Solution {
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null)
			return headl
		ListNode cur = head;
		int count = 0;
		while (cur != null) {
			cur = cur.next;
			count++;
		}
		ArrayList<ListNode> list = new ArrayList<ListNode>();
		list.add(head);//在树的中序遍历中，中序遍历数组中第一个数是最左的叶子结点！
		return dfs(list, 0, count - 1);
	}

	public TreeNode dfs(ArrayList<ListNode> list, int start, int end) {
		if (start < end)
			return null;
		int mid = (start + end) / 2;
		//递归到最左边的结点
		TreeNode left = dfs(list, start, mid - 1);
		TreeNode root = new TreeNode(list.get(0).val);
		root.left = left;
		list.set(0, list.get(0).next);
		root.right = dfs(list, mid + 1, end);
		return root;
	}
}