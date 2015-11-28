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
		1.2 Longest Palindromic Substring
			public class Solution {
				public String longestPalindrome(String s) {
			        if (s == null || s.length() == 0) {
			            return "";
			        }
			        //isPalin[i][j], i and j are two indices of the string, 
			        //denote whether substring from i to j is palindrome;
			        //isPalin[i][i] is always palindrome, since s.charAt(i) == s.charAt(i)
			        boolean[][] isPalind = new boolean[s.length()][s.length()];
			        String res = "";
			        int maxLen = 0;
			        for (int i = s.length() - 1; i >= 0; i--) {
			            for (int j = i; j < s.length(); j++) {
			                // j - i <= 2 --> aba or aa 肯定palindrome
			                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalind[i + 1][j - 1])) {
			                    isPalind[i][j] = true;
			                    if (maxLen < j - i + 1) {
			                        maxLen = j - i + 1;
			                        res = s.substring(i, j + 1);
			                    }
			                }
			            }
			        }
			        return res;
			    }
			}
		1.3 Edit Distance
			/*
				Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
			*/
			//2D DP
			public class Solution {
			    public int minDistance(String word1, String word2) {
			        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
			        for (int i = 0; i <= word1.length(); i++) {
			            dp[i][0] = i;
			        }
			        for (int i = 0; i <= word2.length(); i++) {
			            dp[0][i] = i;
			        }
			        for (int i = 1; i <= word1.length(); i++) {
			            for (int j = 1; j <= word2.length(); j++) {
			                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
			                    int delete = dp[i][j - 1] + 1;
			                    int insert = dp[i - 1][j] + 1;
			                    int replace = dp[i - 1][j - 1] + 1;
			                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
			                } else {
			                    dp[i][j] = dp[i - 1][j - 1];
			                }
			            }
			        }
			        return dp[word1.length()][word2.length()];
			    }
			}
			//1D DP
			public class Solution {
			    public int minDistance(String word1, String word2) {
			        int[] dp = new int[word2.length() + 1];
			        for (int i = 0; i <= word2.length(); ++i) {
			        	dp[i] = i;
			        }
			        for (int i = 1; i <= word1.length(); ++i) {
			            int pre = dp[0];
			            dp[0] = i;
			            for (int j = 1; j <= word2.length(); ++j) {
			                int temp = dp[j];
			                dp[j] = Math.min(dp[j - 1], dp[j]) + 1;
			                dp[j] = Math.min(dp[j], pre + (word1.charAt(i -1) == word2.charAt(j - 1) ? 0: 1));
			                pre = temp;
			            }
			        }
			        return dp[word2.length()];
			    }
			}



		


2. Subsequence Problem	 	
		2.1 Longest Common Subsequence
			public class Solution {
			    /**
			     * @param A, B: Two strings.
			     * @return: The length of longest common subsequence of A and B.
			     */
			    public int longestCommonSubsequence(String A, String B) {
			        // write your code here
			        int[][] dp = new int[A.length() + 1][B.length() + 1];
			        for (int i = 1; i <= A.length(); i++) {
			            for (int j = 1; j <= B.length(); j ++) {
			                if(A.charAt(i - 1) == B.charAt(j - 1)) {
			                    dp[i][j] = dp[i - 1][j - 1] + 1;
			                } else {
			                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
			                }
			            }
			        }
			        return dp[A.length()][B.length()];
			    }
			}
		2.2 Distinct Subsequences
			/*
				Given a string S and a string T, count the number of distinct subsequences of T in S.
				(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
				Here is an example:
					S = "rabbbit", T = "rabbit"
				eturn 3.
			*/
			//Solution1: 2D dp
			public class Solution {
			    public int numDistinct(String s, String t) {
			        if (s == null || t == null || s.length() < t.length()) {
			            return 0;
			        }
			        int[][] dp = new int[s.length() + 1][t.length() + 1];
			        for (int i = 0; i <= s.length(); i++) {
			            dp[i][0] = 1;
			        }
			        for (int i = 1; i <= s.length(); i++) {
			            for (int j = 1; j <= t.length(); j++) {
			                if (s.charAt(i - 1) == t.charAt(j - 1)) {
			                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			                } else {
			                    dp[i][j] = dp[i - 1][j];
			                }
			            }
			        }
			        return dp[s.length()][t.length()];
			    }
			}
			//Solution2: 1D dp
			public class Solution {
				public int numDistinct(String s, String t) {
			        if (s == null || t == null || s.length() < t.length()) {
			            return 0;
			        }
			        int[] dp = new int[t.length() + 1];
			        dp[0] = 1;
			        //dp[j] 在每一层i loop中，就意味着dp[i][j]
			        for (int i = 1; i <= s.length(); i++) {
			            int pre = 1;
			            for (int j = 1; j <= t.length(); j++) {
			                int temp = dp[j];//dp[i - 1][j]
			                if (s.charAt(i - 1) == t.charAt(j - 1)) {
			                    dp[j] = dp[j] + pre;//dp[j] = dp[i - 1][j], pre = dp[i - 1][j - 1]
			                }
			                pre = temp;//到下一个j的时候 dp[i - 1][j] 就变成了dp[i - 1][j - 1]
			                
			            }
			        }
			        return dp[t.length()];
			    }
			}
		2.3 Longest Increasing Subsequence
			//O(n*n) dp
			public class Solution {
			    public int lengthOfLIS(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int[] dp = new int[nums.length];
			        int max = 0;
			        Arrays.fill(dp, 1);
			        for (int i = 0; i < nums.length; i++) {
			            for (int j = 0; j < i; j++) {
			                if (nums[i] > nums[j]) {
			                    dp[i] = Math.max(dp[j] + 1, dp[i]);
			                }
			            }
			            max = Math.max(max, dp[i]);
			        }
			        return max;
			    }
			}
			//O(n),binarySearch and Dp
			/*
			    The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, f
			    or all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. Having this info,
			     for each new number we iterate to, we can determine the longest subsequence where it can be appended using binary search. 
			*/
			public class Solution {
			    public int lengthOfLIS(int[] nums) {
			        int[] dp = new int[nums.length];
			        int len = 0;
			        for (int num : nums) {
			            int i = binarySearch(dp, 0, len, num);
			            dp[i] = num;
			            if (i == len) {
			                len++;
			            }
			        }
			        return len;
			    }
			    private int binarySearch(int[] nums, int start, int end, int target) {
			        while (start < end) {
			            int mid = start + (end - start)/2;
			            if (nums[mid] >= target) {
			                end = mid;
			            } else {
			                start = mid + 1;
			            }
			        }
			        return end;
			    }
			}
		
		