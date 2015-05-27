/*
	House Robber II 
	Note: This is an extension of House Robber.

	After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

	Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

	Credits:
	Special thanks to @Freezen for adding this problem and creating all test cases
*/

public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        //store the maximun profit
        int maxProfit = 0;
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        //in the first loop, we calculate the first house, and just ignore the last house.
        for (int i = 2; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        maxProfit = dp[nums.length - 2];
        
        //in the second loop, we calculate the last house, and just ignore the first house.
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        maxProfit = Math.max(maxProfit, dp[nums.length - 1]);
        return maxProfit;
    }
}