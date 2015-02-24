/*
	Convert Sorted Array to Binary Search Tree
	Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/


public class Solution {
    /*
        解题思路:
            step1:已排序的数组是升序排列，而BST的性质是 root.left < root < root.right.
            step2:意味着，数组的mid值会是root.val.
            step3:递归生成左结点和右结点.
    */
        
    public TreeNode sortedArrayToBST(int[] num) {
    	if (num == null || num.length <= 0)
    		return null; 
    	return dfs(num, 0, num.length - 1);
	}
	public TreeNode dfs(int[] num, int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = dfs(num, start, mid - 1);
		root.right = dfs(num, mid + 1, end);
		return root; 
	}
}