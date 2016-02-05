/*
	House Robber 
	You are a professional robber planning to rob houses along a street. 
	Each house has a certain amount of money stashed, the only constraint stopping you 
	from robbing each of them is that adjacent houses have security system connected 
	and it will automatically contact the police if two adjacent houses were broken into on the same night.

	Given a list of non-negative integers representing the amount of money of each house, 
	determine the maximum amount of money you can rob tonight without alerting the police.
*/

//Solution1 space:O(n)
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        //dp[i] means there are i house can be robbered, the maximum money
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[nums.length];
    }
}


//Solution2: prefer
public class Solution {
    public int rob(int[] nums) {
        int include = 0, exclude = 0;
        for (int j = 0; j < nums.length; j++) {
            int i = include;
            int e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
}




//Solution2 space: O(1)
public static int rob(int[] nums) {
    int ifRobbedPrevious = 0;   // max monney can get if rob current house
    int ifDidntRobPrevious = 0; // max money can get if not rob current house

    // We go through all the values, we maintain two counts, 1) if we rob this cell, 2) if we didn't rob this cell
    for(int i = 0; i < nums.length; i++) {
        // If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
        int currRobbed = ifDidntRobPrevious + nums[i];

        // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
        int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious); 

        // Update values for the next round
        ifDidntRobPrevious  = currNotRobbed;
        ifRobbedPrevious = currRobbed;
    }
    return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
}
