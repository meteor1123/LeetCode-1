/*
	Kth Largest Element in an Array
	Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

	For example,
	Given [3,2,1,5,6,4] and k = 2, return 5.

	Note: 
	You may assume k is always valid, 1 ≤ k ≤ array's length.
	Tags: Divide and Conquer, Heap
*/

/*
	https://leetcode.com/discuss/36966/solution-explained
*/
public class Solution {
	//1.Using heap
	//  O(N lg K) running time + O(k) memory
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int val : nums) {
			pq.offer(val);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}

	//2.quick select

	public int findKthLargest(int[] nums, int k) {
		k = nums.length - k;
		int low = 0;
		int high = nums.length - 1;
		while (low < high) {
			int j = partition(nums, low, high);
			if (j < k) {
				low = j + 1;
			} else if (j > k) {
				high = j - 1;
			} else {
				break;
			}
		}
		return nums[k];
	}

	private int partition(int[] a, int low, int high) {
		int i = low;
		int j = high + 1;
		while (true) {
			while (i < high && a[++i] < a[low]);
			while (j > low && a[low] < a[--j]);
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		swap(a, low, j);
		return j;
	}
	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] =tmp;
	}
}