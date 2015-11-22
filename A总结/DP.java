/*
	Dynamic Programming
*/



1. Word Problem
	1.1 Word Break I
		/*
			s = "leetcode",
			dict = ["leet", "code"].
			Return true because "leetcode" can be segmented as "leet code".
			note that: dict can be used mutiple times.
			O(n*n), dp
		*/
		public class Solution {
		    public boolean wordBreak(String s, Set<String> wordDict) {
		        if (s == null || s.length() == 0) {
		            return true;
		        }
		        if (wordDict == null || wordDict.size() == 0) {
		            return false;
		        }
		        boolean[] dp = new boolean[s.length() + 1];
		        dp[0] = true;
		        // for (int i = 0; i <= s.length(); i++) {
		        //     for (int j = i + 1; j <= s.length(); j++) {
		        //         if (wordDict.contains(s.substring(i, j)) && dp[i]) {
		        //             dp[j] = true;
		        //         }
		        //     }
		        // }
		        for (int i = 1; i <= s.length(); i++) {
		            for (int j = 0; j <= i; j++) {
		                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
		                    dp[i] = true;
		                    break;
		                }
		            }
		        }
		        return dp[s.length()];
		    }
		}