/*
	House Robber 
	You are a professional robber planning to rob houses along a street. 
	Each house has a certain amount of money stashed, the only constraint stopping you 
	from robbing each of them is that adjacent houses have security system connected 
	and it will automatically contact the police if two adjacent houses were broken into on the same night.

	Given a list of non-negative integers representing the amount of money of each house, 
	determine the maximum amount of money you can rob tonight without alerting the police.
*/


public class Solution {
    public int rob(int[] num) {
        if (num.length == 0 || num == null) {
            return 0;
        }
        if (num.length == 1) {
            return num[0];
        }
        if (num.length == 2) {
            return Math.max(num[0], num[1]);
        }
        //我们维护一个一位数组dp，其中dp[i]表示到i位置时不相邻数能形成的最大和
        int[] dp = new int[num.length];
        dp[0] = num[0];
        dp[1] = Math.max(num[0], num[1]);
        for (int i = 2; i < num.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + num[i]);
        }
        return dp[num.length - 1];
    }
}