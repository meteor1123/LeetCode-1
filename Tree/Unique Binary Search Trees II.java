/*
	Unique Binary Search Trees II
	Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

	For example,
	Given n = 3, your program should return all 5 unique BST's shown below.

	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
*/

/* Solution :
	Start by noting that 1..n is the in-order traversal for any BST with nodes 1 to n. 
	So if I pick i-th node as my root, the left subtree will contain elements 1 to (i-1), 
	and the right subtree will contain elements (i+1) to n.
	 I use recursive calls to get back all possible trees for left and right subtrees 
	 and combine them in all possible ways with the root.
*/

/*
    Solution:
    http://www.cnblogs.com/EdwardLiu/p/3960079.html
*/
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return helper(1, n);
    }
    public List<TreeNode> helper (int left, int right) {
    	List<TreeNode> res = new ArrayList<TreeNode>();
    	if (left > right) {
    		res.add(null);
    		return res;
    	}
    	for (int i = left; i <= right; i++) {
    		List<TreeNode> leftList = helper(left, i - 1);
    		List<TreeNode> rightList = helper(i + 1, right);
    		for (int j = 0; j < leftList.size(); j++) {
    			for (int k = 0; k < rightList.size(); k++) {
    				TreeNode root = new TreeNode(i);
    				root.left = leftList.get(j);
    				root.right = rightList.get(k);
    				res.add(root);
    			}
    		}
    	}
    	return res;
    }
}