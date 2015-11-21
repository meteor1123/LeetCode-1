/*
	Range Sum Query - Mutable
	Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

	The update(i, val) function modifies nums by updating the element at index i to val.
	Example:
	Given nums = [1, 3, 5]

	sumRange(0, 2) -> 9
	update(1, 2)
	sumRange(0, 2) -> 8
	Note:
	The array is only modifiable by the update function.
	You may assume the number of calls to update and sumRange function is distributed evenly.
	Tags: Segment Tree,Binary Indexed Tree
*/

/*
	http://bookshadow.com/weblog/2015/08/13/segment-tree-set-1-sum-of-given-range/
*/
//Solution1 Segment Tree
public class NumArray {
    class SegmentTreeNode {
        int start;
        int end;
        int sum;
        SegmentTreeNode left;
        SegmentTreeNode right;
     
        public SegmentTreeNode (int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }
    SegmentTreeNode root = null;
    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }
    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } 
        SegmentTreeNode res = new SegmentTreeNode(start, end);
        if (start == end) {
            res.sum = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            res.left = buildTree(nums, start, mid);
            res.right = buildTree(nums, mid + 1, end);
            res.sum = res.left.sum + res.right.sum;
        }
        return res;
    }
    void update(int i, int val) {
        update(root, i, val);
    }
    
    void update(SegmentTreeNode root, int pos, int val) {
        if (root.start == root.end) {
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (pos <= mid) {
                update(root.left, pos, val);
            } else {
                update(root.right, pos, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    
    public int sumRange(SegmentTreeNode root, int start, int end) {
        if (root.end == end && root.start == start) {
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) { 
                return sumRange(root.left, start, end);
            } else if (start >= mid + 1) {
                return sumRange(root.right, start, end);
            } else {
                return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
            }
        }
    }
}

//Solution2 Binary Indexed Tree
public class NumArray {
    int[] tree;
    int[] nums;
    int size;
    public NumArray(int[] nums) {
        this.size = nums.length;
        this.nums = new int[size];
        this.tree = new int[size];
        for (int i = 0; i < size; ++i)
            update(i, nums[i]);
    }
    public void update(int i, int val) {
        int delta = val - nums[i];
        nums[i] = val;
        for (; i < size; i |= i + 1)
            tree[i] += delta;
    }
    public int sumRange(int i, int j) {
        return sum(j) - sum(i - 1);
    }
    public int sum(int ind) {
        int ans = 0;
        while (ind >= 0) {
            ans += tree[ind];
            ind &= ind + 1;
            ind--;
        }
        return ans;
    }
}
// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);