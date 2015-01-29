/*
	Climbing Stairs
	You are climbing a stair case. It takes n steps to reach to the top.
	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	Tag:Dynamic Programming
*/


/*
	Solution:
		假设梯子有n层，那么如何爬到第n层呢，因为每次只能趴爬1或2步，那么爬到第n层的方法要么是从第n-1层一步上来的，要不就是从n           -2层2步上来的，所以递推公式非常容易的就得出了：dp[n] = dp[n-1] + dp[n-2]
    	如果梯子有1层或者2层，dp[1] = 1, dp[2] = 2，如果梯子有0层，自然dp[0] = 0
*/
public int climbStairs(int n) {
	//create dp array,the length equal n + 1
	int[] dp = new dp[n + 1];

	//corner case
	if (n == 0 || n == 1 || n == 2)
		return n;

	//init dp[]
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 2;

	for (int i = 3; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}
	return dp[n];
}