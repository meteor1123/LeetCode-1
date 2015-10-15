/*
	Convert Sorted List to Binary Search Tree
	Given a singly linked list where elements are sorted in ascending order, 
	convert it to a height balanced BST.
*/

public class Solution {
	//solution3 小丸子，最容易理解
	static ListNode h;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        int size = 0;
        h = head;
        ListNode cur = head;
        //caculate the size of ListNode
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        return sortedListToBSTHelper(0, size - 1);
    }
    
    //divide and Conquer
    public TreeNode sortedListToBSTHelper(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        //recursive the most left node,
        TreeNode left = sortedListToBSTHelper(start, mid - 1);
        TreeNode root = new TreeNode(h.val);
        h = h.next;
        TreeNode right = sortedListToBSTHelper(mid + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }
	//static ListNode head;
	public TreeNode sortedListToBST(ListNode head) {
		//this.head = head;
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
		list.set(0, list.get(0).next);//head = head.next;
		root.right = dfs(list, mid + 1, end);
		return root;
	}

	//Chapter 9
	private ListNode cur;
	private int getListLength(ListNode head) {
		int size = 0;
		while (head != null) {
			size++;
			head = head.next;
		}
		return size;
	}

	public TreeNode sortedListToBST(ListNode head) {
		int size;
		cur = head;
		size = getListLength(head);
		return sortedListToBSTHelper(size);
	}

	public TreeNode sortedListToBSTHelper(int size) {
		if (size <= 0) {
			return null;
		}
		TreeNode left = sortedListToBSTHelper(size / 2);
		TreeNode root = new TreeNode(cur.val);
		cur = cur.next;
		TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);
		root.left = left;
		root.right = right;
		return root;
	}



    //use O(n) space
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        
        ArrayList<Integer> listNode = new ArrayList<Integer>();
        while(head != null){
            listNode.add(head.val);
            head = head.next;
        }
        int left = 0;
        int right= listNode.size()-1;
        return helper(listNode, left, right);
    }
    
    public TreeNode helper(ArrayList<Integer> listNode, int left, int right){
        if(left>right) return null;
        
        TreeNode node = new TreeNode(0);
        int mid = (left+right)/2;
        node.val = listNode.get(mid);
        node.left = helper(listNode, left, mid-1);
        node.right = helper(listNode, mid+1, right);            
        return node;
    }
}
}







