/*
	Sliding Window Maximum
	Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

	For example,
	Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

	Window position                Max
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	Therefore, return the max sliding window as [3,3,5,5,6,7].

	Note: 
	You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

	Follow up:
	Could you solve it in linear time?
*/

public class Solution {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		if (nums == null || k <= 0) {
			int[] res = new int[0];
			return res;
		}
		int[] res = new int[n - k + 1];
		int ri = 0;

		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < nums.length; i++) {
			while (!queue.isEmpty() && queue.peek() < i - k + 1) {
				queue.poll();
			}

			while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
				queue.pollLast();
			}

			queue.offer(i);
			if (i >= k - 1) {
				res[ri ++] = nums[queue.peek()];
			}
		}
		return res;
	}
}