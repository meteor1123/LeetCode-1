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
    	//Corner case
    	if (num == null || num.length <= 0)
    		return null; 
    	//use a dfs helper to  generate the bst
    	return dfs(num, 0, num.length - 1);
	}
	//as we know,  using inorder traverse the bst ,we will get a ascending array,
	public TreeNode dfs(int[] num, int start, int end) {
		//this is the termination condition.
		if (start > end) //mean we reach the null node
			return null;
		int mid = (start + end) / 2; //every time we let mid = start + end / 2
		TreeNode root = new TreeNode(num[mid]);//generate a root node
		//and recursive genarate the left tree and right tree.
		root.left = dfs(num, start, mid - 1);
		root.right = dfs(num, mid + 1, end);
		return root; 
	}
}