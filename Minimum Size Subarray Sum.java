/*
	Minimum Size Subarray Sum
	Given an array of n positive integers and a positive integer s, 
    find the minimal length of a subarray of which the sum ≥ s. 
    If there isn't one, return 0 instead.

	For example, given the array [2,3,1,2,4,3] and s = 7,
	the subarray [4,3] has the minimal length under the problem constraint.

	click to show more practice.

	More practice:
	If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/


public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        if (nums[0] > s) {
            return 1;
        }
        int sum = nums[0];
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                if (i - left + 1 < minLen) {
                    minLen = i - left + 1;
                }
                sum -= nums[left];
                left++;
            } 
        }
        if (minLen == Integer.MAX_VALUE) {
            minLen = 0;
        }
        return minLen;
    }
}