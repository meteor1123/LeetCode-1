-/*
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
    //Solution1
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                if (i - left + 1 < minLen) {
                    minLen = i - left + 1;
                }
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    //Solution2
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0;
        while (i < nums.length) {
            sum += nums[i];
            while (sum >= s) {
                if (i - left + 1 < minLen) {
                    minLen = i - left + 1;
                }
                sum -= nums[left++];
            }
            i++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}