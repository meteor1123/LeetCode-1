/*
	Paint House
	There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
    The cost of painting each house with a certain color is different. 
    You have to paint all the houses such that no two adjacent houses have the same color.

	The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
    For example, costs[0][0] is the cost of painting house 0 with color red; 
    costs[1][2] is the cost of painting house 1 with color green, and so on... 
    Find the minimum cost to paint all houses.

	Note:
	All costs are positive integers.

	Tags: Dynamic Programming
*/


public class Solution {

	//Solution1 这其实是Paint HouseII的解法，
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        if (costs.length == 1 && costs[0].length == 1) { //加上这一句就可以用于
            return costs[0][0];
        }
        int m = costs.length;
        int n = costs[0].length;
        int[][] dp = new int[m + 1][n];


        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[m][i]);
        }
        return res;
    }
    

    //Solution2
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int m = costs.length;
        int r = 0, g = 0, b = 0;
        for (int i = 0; i < m; i++) {
           int preRed = r, preBlue = b, preGreen = g;
           r = costs[i][0] + Math.min(preBlue, preGreen);
           b = costs[i][1] + Math.min(preRed, preGreen);
           g = costs[i][2] + Math.min(preBlue, preRed);
        }
        return Math.min(r, Math.min(b, g));
    }
}
