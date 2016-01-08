/*
	Dynamic Programming
*/


/*
	Preliminary

	Dynamic Programming is an algorithmic paradigm that solves a given complex problem by breaking it into subproblems 
	and stores the results of subproblems to avoid computing the same results again. 
	Following are the two main properties of a problem that suggest that the given problem can be solved using Dynamic programming.

	1) Overlapping Subproblems
		Like Divide and Conquer, Dynamic Programming combines solutions to sub-problems. 
		Dynamic Programming is mainly used when solutions of same subproblems are needed again and again. 
		In dynamic programming, computed solutions to subproblems are stored in a table so that these don’t have to recomputed. 
		So Dynamic Programming is not useful when there are no common (overlapping) subproblems because there is no point storing the solutions if they are not needed again. 
		For example, Binary Search doesn’t have common subproblems. If we take example of following recursive program for Fibonacci Numbers, there are many subproblems which are solved again and again.
	2) Optimal Substructure
*/
	1.1 Fibonacci
		the function is F(n) = F(n - 1) + F(n - 2)
		1.1.1 Recursive:
			/*
										  fib(5)
				                     /             \
				               fib(4)                fib(3)
				             /      \                /     \
				         fib(3)      fib(2)         fib(2)    fib(1)
				        /     \        /    \       /    \
				  fib(2)   fib(1)  fib(1) fib(0) fib(1) fib(0)
				  /    \
				fib(1) fib(0)

				We can see that the function f(3) is being called 2 times. If we would have stored the value of f(3), 
				then instead of computing it again, we would have reused the old stored value. 
				There are following two different ways to store the values so that these values can be reused.

				a) Memoization (Top Down): 
				b) Tabulation (Bottom Up):
			*/
			public class Solution {
				public int fib(int n) {
					if (n <= 1) {
						return n;
					}
					return fib(n - 2) + fib(n - 1);
				}
			}
		1.1.2 Memoization (Top Down)
			/*
				a) Memoization (Top Down): 
				The memoized program for a problem is similar to the recursive version with a small modification that 
				it looks into a lookup table before computing solutions. We initialize a lookup array with all initial values as NIL. 
				Whenever we need solution to a subproblem, we first look into the lookup table. If the precomputed value is there then we return that value, 
				otherwise we calculate the value and put the result in lookup table so that it can be reused later.
			*/
			public class Solution {
				int[] dp = new int[99];
				// Arrays.fill(memo, Integer.MAX_VALUE);
				public int fib(int n) {
					if (n <= 1) {
						return n;
					}
					if (dp[n] != 0) {
						return dp[n];
					}
					dp[n] = fib(n - 1) + fib(n - 2);
					return dp[n];
				}
			}

		1.1.3 Tabulation (Bottom Up)
			/*
				 The tabulated program for a given problem builds a table in bottom up fashion and returns the last entry from table.
			*/
			public class Solution {
				public int fib(int n) {
					int[] dp = new int[n + 1];
					dp[0] = 0;
					dp[1] = 1;
					for (int i = 2; i <= n; i++) {
						dp[i] = dp[i - 1] + dp[i - 2];
					}
					return dp[n];
				}
			}












/*
	Dynamic Programming Problem
*/


0. Array Problem
		0.1 Maximum Subarray
			/* 	
				Maximum Subarray 
				Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

				For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
				the contiguous subarray [4,−1,2,1] has the largest sum = 6.

				More practice:
					If you have figured out the O(n) solution, 
					try coding another solution using the divide and conquer approach, which is more subtle.
				Tags: Dp, Array, Divide and Conquer
			*/

			//Solution1: DP
			public class Solution {
				public int maxSubArray(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int[] dp = new int[nums.length];
			        dp[0] = nums[0];
			        int res = dp[0];
			        for (int i = 1; i < nums.length; i++) {
			            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
			            res = Math.max(dp[i], res);
			        }
			        return res;
			    }
			}
			//Solution2: Greedy
			public class Solution {
				public int maxSubArray(int[] A) {
					// use a local to store a local maximum,
					// and use a global to store a global maximum.
					int local = A[0];
					int global = A[0]
					for (int i = 1; i < A.length; i++) {
						local = Math.max(A[i], A[i] + local);
						global = Math.max(local, global);
					}
					return max;
				}
			}
		0.2  Maximum Product Subarray
			/*
				Find the contiguous subarray within an array (containing at least one number) which has the largest product.
				For example, given the array [2,3,-2,4],
				the contiguous subarray [2,3] has the largest product = 6.
			*/
			public class Solution {
			    public int maxProduct(int[] nums) {
			        int max = nums[0];
			        int min = nums[0];
			        int res = nums[0];
			        for (int i = 1; i < nums.length; i++) {
			            int tempMax = max;
			            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
			            min = Math.min(nums[i], Math.min(tempMax * nums[i], min * nums[i]));//Since we need to use max here, 
			            															  		//So we need tempMax to store the orginal value max
			            res = Math.max(max, res);
			        }
			        return res;
			    }
			}

		0.2 Unique Paths I
			//Solution1: O(m * n) space
			public class Solution {
				public int uniquePaths(int m, int n) {
			        int[][] dp = new int[m][n];
			        for (int i = 0; i < m; i++) {
			            dp[i][0] = 1;
			        }
			        for (int i = 0; i < n; i++) {
			            dp[0][i] = 1;
			        }
			        for (int i = 1; i < m; i++) {
			            for (int j = 1; j < n; j++) {
			                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			            }
			        }
			        return dp[m - 1][n - 1];
			    }
			}
			//Solution2: O(n) space
			public class Solution {
				public int uniquePaths(int m, int n) {
			        int[] dp = new int[n];
			        dp[0] = 1;
			        for (int i = 0; i < m; i++) {
			            for (int j = 1; j < n; j++) {
			                dp[j] = dp[j] + dp[j - 1] ;
			            }
			        }
			        return dp[n - 1];
			    }
			}
		0.3 Unique Paths II
			//Solution1: O(m * n) space
			public class Solution {
				public int uniquePathsWithObstacles(int[][] obstacleGrid) {
			        if (obstacleGrid == null || obstacleGrid.length == 0) {
			            return 0;
			        }
			        int m = obstacleGrid.length;
			        int n = obstacleGrid[0].length;
			        int[][] dp = new int[m][n];
			        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
			        for (int i = 1; i < m; i++) {
			            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
			        }
			        for (int j = 1; j < n; j++) {
			            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
			        }			        
			        for (int i = 1; i < m; i++) {
			            for (int j = 1; j < n; j++) {
			                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
			            }
			        }
			        return dp[m - 1][n - 1];
			    }
			}
			//Solution2: O(n) space
			public class Solution {
				public int uniquePathsWithObstacles(int[][] obstacleGrid) {
			        // write your code here
			        int m = obstacleGrid.length;
			        int n = obstacleGrid[0].length;
			        
			        int[] dp = new int[n];
			        dp[0] = 1;
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (obstacleGrid[i][j] == 1) {
			                    dp[j] = 0;
			                } else if (j > 0) {
			                    dp[j] = dp[j] + dp[j - 1];
			                }
			            }
			        }
			        return dp[n - 1];
			    }
			}
		0.4 Minimum Path Sum
			public class Solution {
			    public int minPathSum(int[][] grid) {
			        int m = grid.length;
			        int n = grid[0].length;
			        
			        int[][] dp = new int[m][n];
			        dp[0][0] = grid[0][0];
			        for (int i = 1; i < m; i++) {
			            dp[i][0] = dp[i - 1][0] + grid[i][0];
			        }
			        for (int i = 1; i < n; i++) {
			            dp[0][i] = dp[0][i - 1] + grid[0][i];
			        }
			        for (int i = 1; i < m; i++) {
			            for (int j = 1; j < n; j++) {
			                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			            }
			        }
			        return dp[m - 1][n - 1];
			    }
			}
			public class Solution {
			    public int minPathSum(int[][] grid) {
			        int m = grid.length;
			        int n = grid[0].length;
			        int[] dp = new int[n];
			        dp[0] = grid[0][0];
			        for (int i = 1; i < n; i++) {
			            dp[i] = dp[i - 1] + grid[0][i];
			        }
			        for (int i = 1; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                if (j == 0) {
			                    dp[j] += grid[i][j];
			                } else {
			                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
			                }
			            }
			        }
			        return dp[n - 1];
			    }
			}
			
1. Word And String Problem
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
			        boolean[] dp = new boolean[s.length() + 1];
			        dp[0] = true;
			        for (int i = 1; i <= s.length(); i++) {
			            for (int j = 0; j < i; j++) {
			                if (dp[j] && wordDict.contains(s.substring(j, i))) {
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
		1.2 Palindrome Partitioning II
			/*
				Given a string s, partition s such that every substring of the partition is a palindrome.
				Return the minimum cuts needed for a palindrome partitioning of s.

				For example, given s = "aab",
				Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
			*/
			//Solution1: 
			public class Solution {
			    public int minCut(String s) {
			        int n = s.length();
			        int[] dp = new int[n + 1];
			        for (int i = 0; i < n; i++) {
			            dp[i] = Integer.MAX_VALUE;
			        }
			        dp[n] = -1;// number of cuts for the first k characters
			        for (int i = n - 1; i >= 0; i--) {
			            for (int a = i, b = i; a >= 0 && b < n && s.charAt(a) == s.charAt(b); a--, b++) {
			                dp[a] = Math.min(dp[a], 1 + dp[b + 1]);//even length palindrome
			            }
			            for (int a = i, b = i + 1; a >= 0 && b < n && s.charAt(a) == s.charAt(b); a--, b++) {
			                dp[a] = Math.min(dp[a], 1 + dp[b + 1]);//odd length palindrome

			            }
			        }
			        return dp[0];
			    }
			}
			//Solution2:
			public class Solution {
				public int minCut(String s) {
					if (s == null || s.length() == 0)
						return 0;
					int min = 0;
					int len = s.length();
					//isPalindrome[i][j] means from the substring position i to position j, is palindrome or not
					boolean[][] isPalindrome = new boolean[len][len];
					//cuts[i] mean ths substring from i to lenghth ,the minimum cuts
					int minCuts[] = new int[len + 1];
					
					//cut[i] means  from i position to the length of String s include how many cuts, that can make minimum palindrome partition
					for (int i = 0; i < len; i++)
						minCuts[i] = len - i;//in the worst case, every character need to cut, 

					//we need to update the state from len - 1 ~ len  to 0 ~ len
					for (int i = len - 1; i >= 0; --i) {
						//j is the right side of the i ,     (i ~ j), (j + 1 ~ len - 1)
						for (int j = i; j < len; ++j) {
							if ((s.charAt(i) == s.charAt(j) && (j - i) <= 1) || 
								(s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1])) {
								isPalindrome[i][j] = true;
								//  [i,j] | [j+1,len - 1]  因为i-j已经是回文不需要切割，因此最小的从i 到 length 的切割数应该是
								//       中间这里的一割，再加上j + 1 到len - 1的最小切割。
								minCuts[i] = Math.min(minCuts[i], minCuts[j + 1] + 1);
								//                 [i,j] | [j+1,len - 1]
							}
						}
					}
					min = minCuts[0] - 1;//把多余加的那个对于第len位置的切割去掉，即为最终结果
					return min;
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

		1.4 Scramble String
			public class Solution {
			    public boolean isScramble(String s1, String s2) {
			        if (s1 == null || s2 == null || s1.length() != s2.length()) {
			            return false;
			        }
			        if (s1.length() == 0) {
			            return true;
			        }
			        boolean[][][] check = new boolean[s1.length()][s2.length()][s1.length() + 1];
			        for (int i = 0; i < s1.length(); i++) {
			            for (int j = 0; j < s2.length(); j++) {
			                check[i][j][1] = s1.charAt(i) == s2.charAt(j);
			            }
			        }
			        for (int len = 2; len <= s1.length(); len++) {
			            for (int i = 0; i + len - 1 < s1.length(); i++) {
			                for (int j = 0; j + len - 1 < s2.length(); j++) {
			                    for (int k = 1; k < len; k++) {
			                        check[i][j][len] |= check[i][j][k] && check[i + k][j + k][len - k] ||
			                                            check[i][j + len - k][k] && check[i + k][j][len - k];
			                    }
			                }
			            }
			        }
			        return check[0][0][s1.length()];
			    }
			}
		1.5 Interleaving String
			/*
				Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
				For example,
				Given:
				s1 = "aabcc",
				s2 = "dbbca",
				When s3 = "aadbbcbcac", return true.
				When s3 = "aadbbbaccc", return false.
			*/
			public class Solution {
			    public boolean isInterleave(String s1, String s2, String s3) {
			        int m = s1.length();
			        int n = s2.length();
			        if (m + n != s3.length()) {
			            return false;
			        }
			        boolean[][] dp = new boolean[m + 1][n + 1];
			        dp[0][0] = true;
			        for (int i = 1; i <= m; i++) {
			            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
			        }
			        for (int i = 1; i <= n; i++) {
			            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
			        }
			        for (int i = 1; i <= m; i++) {
			            for (int j = 1; j <= n; j++) {
			                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
			                           dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
			            }
			        }
			        return dp[m][n];
			    }
			}
		1.6 Decode Ways
			/*
				A message containing letters from A-Z is being encoded to numbers using the following mapping:

				'A' -> 1
				'B' -> 2
				...
				'Z' -> 26
				Given an encoded message containing digits, determine the total number of ways to decode it.

				For example,
				Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

				The number of ways decoding "12" is 2.
			*/
			/*
				输入的字符是数字，比如  123-> ABC, LC, AW, 要判断长度为1-2的字符串是否在“1” ~ “26”的范围，
				比如 27就只有一种code，BG， 27超出26 不能code
			*/
			public class Solution {
			    public int numDecodings(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        int m = s.length();
			        int[] dp = new int[m + 1];
			        dp[0] = 1;
			        if (isValid(s.substring(0, 1))) {
			            dp[1] = 1;
			        } else {
			            dp[1] = 0;
			        }
			        for (int i = 2; i <= m; i++) {
			            if (isValid(s.substring(i - 1, i))) {
			                dp[i] += dp[i - 1];
			            }
			            if (isValid(s.substring(i - 2, i))) {
			                dp[i] += dp[i - 2];
			            }
			        }
			        return dp[m];
			    }
			    
			    public boolean isValid(String s) {
			        if (s.charAt(0) == '0') {
			            return false;
			        }
			        return Integer.valueOf(s) >= 1 && Integer.valueOf(s) <= 26;
			    }
			}
		1.6 Wildcard Matching
			/*
				Implement wildcard pattern matching with support for '?' and '*'.
				'?' Matches any single character.
				'*' Matches any sequence of characters (including the empty sequence).

				The matching should cover the entire input string (not partial).

				The function prototype should be:
				bool isMatch(const char *s, const char *p)

				Some examples:
				isMatch("aa","a") → false
				isMatch("aa","aa") → true
				isMatch("aaa","aa") → false
				isMatch("aa", "*") → true
				isMatch("aa", "a*") → true
				isMatch("ab", "?*") → true
				isMatch("aab", "c*a*b") → false
			*/
			//Solution1: DP
			public class Solution {
				public boolean isMatch(String str, String pattern) {
			        int m = str.length();
			        int n = pattern.length();
			        boolean[][] dp = new boolean[m + 1][n + 1];
			        dp[0][0] = true;
			        for (int i = 1; i <= n; i++) {
			            if (pattern.charAt(i - 1) == '*') {
			                dp[0][i] = dp[0][i - 1];
			            }
			        }
			        
			        for (int i = 1; i <= m; i++) {
			            for (int j = 1; j <= n; j++) {

			                if (pattern.charAt(j - 1) == str.charAt(i - 1) || pattern.charAt(j - 1) == '?') {
			                    dp[i][j] = dp[i - 1][j - 1];
			                } else if (pattern.charAt(j - 1) == '*') {
			                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
			                    //dp[i][j - 1] 		为true，p上多出的* 也可以匹配i
								//dp[i - 1][j] 		为true，s上多出的i 也肯定被*匹配
								///dp[i - 1][j - 1] 为true，p上多出的* 和 s上多出i也肯定匹配
			                }
			            }
			        }
			        return dp[m][n];
			    }
			}
			//Solution2: Greedy
			public class Solution {
			    public boolean isMatch(String str, String pattern) {
			        int s = 0;
			        int p = 0;
			        int starMatch = 0;
			        int starIndex = -1;
			        int len = pattern.length();
			        while (s < str.length()) {
			            if (p < len && (str.charAt(s) == pattern.charAt(p) || pattern.charAt(p) == '?')) {
			                p++;
			                s++;
			            } else if (p < len && pattern.charAt(p) == '*') {
			                starIndex = p;
			                starMatch = s;
			                p++;
			            } else if (starIndex != -1) {
			                p = starIndex + 1;
			                starMatch++;
			                s = starMatch;
			            } else {
			                return false;
			            }
			        }
			        
			        while (p < pattern.length() && pattern.charAt(p) == '*') {
			            p++;
			        }
			        return p == len;
			    }
			}

		1.7 Regular Expression Matching
			/*
				Implement regular expression matching with support for '.' and '*'.

				'.' Matches any single character.
				'*' Matches zero or more of the preceding element.

				The matching should cover the entire input string (not partial).

				The function prototype should be:
				bool isMatch(const char *s, const char *p)

				Some examples:
				isMatch("aa","a") → false
				isMatch("aa","aa") → true
				isMatch("aaa","aa") → false
				isMatch("aa", "a*") → true
				isMatch("aa", ".*") → true
				isMatch("ab", ".*") → true
				isMatch("aab", "c*a*b") → true
			*/
			/*
				Key Point: 
			*/
			//Solution1: DP
			public class Solution {
			    public boolean isMatch(String s, String p) {
			        int m = s.length();
			        int n = p.length();
			        boolean[][] dp = new boolean[m + 1][n + 1];
			        dp[0][0] = true;
			        for (int i = 2; i <= n; i++) {
			            if (p.charAt(i - 1) == '*') {
			                dp[0][i] = dp[0][i - 2];
			            }
			        }
			        
			        for (int i = 1; i <= m; i++) {
			            for (int j = 1; j <= n; j++) {
			                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
			                    dp[i][j] = dp[i - 1][j - 1];
			                } else if (j > 1 && p.charAt(j - 1) == '*') {
			                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
			                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
			                    } else {
			                        dp[i][j] = dp[i][j - 2];
			                    }
			                } else {
			                    dp[i][j] = false;
			                }
			            }
			        }
			        return dp[m][n];
			    }
			}
			//Solution2: Recursive
			public class Solution {
			    public boolean isMatch(String s, String p) {
			        if (p.isEmpty()) {
			            return s.isEmpty();
			        }
			        if (p.length() == 1 || p.charAt(1) != '*') {
			            if (s.isEmpty() || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0))) {
			                return false;
			            } else {
			                return isMatch(s.substring(1), p.substring(1));
			            }
			        }
			        
			        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
			            if (isMatch(s, p.substring(2))) {
			                return true;
			            }
			            s = s.substring(1);
			        }
			        return isMatch(s, p.substring(2));
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
			        int[] dp = new int[nums.length];//do[i] 表示以nums[i]作为结尾的序列的longest increasing subsequence
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
			    The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, 
			    for all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. Having this info,
			     for each new number we iterate to, we can determine the longest subsequence where it can be appended using binary search. 
			*/
			public class Solution {
			    public int lengthOfLIS(int[] nums) {   
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int n = nums.length;
			        int len = 0;
			        int[] increasingSequence = new int[n];
			        increasingSequence[len++] = nums[0];
			        for (int i = 1; i < n; i++) {
			            if (nums[i] > increasingSequence[len - 1]) {
			                increasingSequence[len++] = nums[i];
			            } else {
			                int position = binarySearch(increasingSequence, 0, len - 1, nums[i]);
			                increasingSequence[position] = nums[i];
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
			        return start;
			    }
			}
		

3. Adjacent Limited, Costs And Benefits Problem
		3.1 Paint House
			/*
				There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. 
				You have to paint all the houses such that no two adjacent houses have the same color.

				The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, 
				costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
			*/
			//Solution1: no space cost
			public class Solution {
			    public int minCost(int[][] costs) {
			        if (costs == null || costs.length == 0) {
			            return 0;
			        }
			        int m = costs.length;
			        int r = 0, b = 0, g = 0;
			        for (int i = 0; i < m; i++) {
			            int preRed = r;
			            int preBlue = b;
			            int preGreen = g;
			            r = costs[i][0] + Math.min(preBlue, preGreen);
			            b = costs[i][1] + Math.min(preRed, preGreen);
			            g = costs[i][2] + Math.min(preRed, preBlue);
			        }
			        
			        return Math.min(r, Math.min(b, g));
			    }
			}
			//Solution2: O(m * n) space
			public class Solution {
			    public int minCost(int[][] costs) {
			        if (costs == null || costs.length == 0) {
			            return 0;
			        }
			        int m = costs.length;
			        int n = costs[0].length;
			        
			        int[][] dp = new int[m + 1][n];
			        
			        for (int i = 1; i <= m; i++) {
			            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i - 1][0];
			            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i - 1][1];
			            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i - 1][2];
			        }
			        return Math.min(dp[m][0], Math.min(dp[m][1], dp[m][2]));
			        
			    }
			}
		3.2 Paint House II
			//Solution1: time: O(m * n * n), space: O(m * n)
			public class Solution {
			    public int minCostII(int[][] costs) {
			        if (costs == null || costs.length == 0) {
			            return 0;
			        }
			        int m = costs.length;
			        int n = costs[0].length;
			        if (m == 1 && n == 1) {
			            return costs[0][0];
			        }
			        int[][] dp = new int[m + 1][n];
			        for (int i = 1; i <= m; i++) {
			            for (int j = 0; j < n; j++) {
			                dp[i][j] = Integer.MAX_VALUE;
			            }
			        }
			        
			        for (int i = 1; i <= m; i++) {
			            for (int j = 0; j < n; j++) {
			                for (int k = 0; k < n; k++) {
			                    if (j != k) {
			                    	//in the i - 1 house, we try all the colors k, and the color k do not equals j, to find the minimum cost
			                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
			                    }
			                }
			            }
			        }
			        int res = Integer.MAX_VALUE;
			        for (int i = 0; i < n; i++) {
			            res = Math.min(dp[m][i], res);
			        }
			        return res;
			    }
			}
			//Solution2: space :O(1)
			public class Solution {
				public int minCostII(int[][] costs) {
			        if (costs == null || costs.length == 0) {
			            return 0;
			        }
			        int m = costs.length;
			        int n = costs[0].length;


			        int min1 = -1;// min1 is the index of the 1st-smallest cost till previous house
			        int min2 = -1;// min2 is the index of the 2nd-smallest cost till previous house
			        for (int i = 0; i < m; i++) {
			            int last1 = min1;
			            int last2 = min2;
			            min1 = -1;
			            min2 = -1;
			            for (int j = 0; j < n; j++) {
			                if (j != last1) {// current color j is different to last min1
			                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
			                } else {
			                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
			                }

			                // find the indices of 1st and 2nd smallest cost of painting current house i
			                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
			                    min2 = min1;
			                    min1 = j;
			                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
			                    min2 = j;
			                }
			            }
			        }
			        return costs[m- 1][min1];
			    }
			}
			//Solution3: space: O(n), time o(m*n),prefer
				//只用记录每次值最小的时候
			public class Solution {
			    public int minCostII(int[][] costs) {
			        if (costs == null || costs.length == 0) {
			            return 0;
			        }
			        int m = costs.length;
			        int n = costs[0].length;
			        int min1 = 0;
			        int min2 = 0;
			        int[] dp = new int[n];
			        for (int i = 0; i < m; i++) {
			            int last1 = min1;
			            int last2 = min2;
			            min1 = Integer.MAX_VALUE;
			            min2 = Integer.MAX_VALUE;
			            for (int j = 0; j < n; j++) {//traverse the
			                dp[j] = (dp[j] == last1 ? last2 : last1) + costs[i][j];
			                if (min1 <= dp[j]) {
			                    min2 = Math.min(min2, dp[j]);
			                } else {
			                    min2 = min1;
			                    min1 = dp[j];
			                }
			            }
			        }
			        return min1;
			    }
			}
		3.3 Paintt Fence
			//Solution1: space : O(1), prefer
			public class Solution {
			    public int numWays(int n, int k) {
			        if (n == 0){
			            return 0;
			        } else if (n == 1) {
			            return k;
			        }
			        
			        int diffColorCounts = k * (k - 1);// 前面有K种可能的颜色，后面就只能放除了前面的颜色的颜色，就是K - 1种可能, k * (k - 1)
			        int sameColorCounts = k; //前面有K种颜色，但是必须和前面的相同因此只有1种可选 k * 1
			        
			        for (int i = 2; i < n; i++) {
			            int temp = diffColorCounts;
			            diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);// k * (k - 1) * (k - 1) + k * 1 * (k - 1) 
			            sameColorCount = temp;//如何追朔连续俩相同， 只要将diffColor赋给sameColor，就意味着肯定不会出现连续3个重复的情况，因为之前的2种颜色都是diff
			        }
			        return diffColorCounts + sameColorCounts;
			    }
			}

			//Solution2: space: O(n)
			public int numWays(int n, int k) {
		        if(n == 0 || k == 0) {
		            return 0;
		        }
		        int[] dp = new int[n];
		        for(int i = 0; i < n; i++){
		            if(i == 0){
		                dp[i] = k;
		            }
		            else if(i == 1){
		                dp[i] = k * k;
		            }
		            else{
		                dp[i] = dp[i-1] * (k-1) + dp[i-2] * (k-1);
		            }
		        }
		        return dp[n-1];
		    }
		3.4 House Robber
			/*
				You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
				the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and 
				it will automatically contact the police if two adjacent houses were broken into on the same night.

				Given a list of non-negative integers representing the amount of money of each house, 
				determine the maximum amount of money you can rob tonight without alerting the police.
			*/
			//O(n) space,
			public class Solution {
			    public int rob(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int m = nums.length;
			        int[] dp = new int[m + 1];
			        dp[0] = 0;
			        dp[1] = nums[0];
			        for (int i = 2; i <= m; i++) {
			            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
			        }
			        return dp[m];
			    }
			}

			//O(1) space
			public class Solution {
			    public int rob(int[] nums) {
			        int preRob = 0;
			        int preNoRob = 0;
			        for (int i = 0; i < nums.length; i++) {
			            int curRob = preNoRob + nums[i];
			            int curNoRob = Math.max(preRob, preNoRob);
			            
			            preRob = curRob;
			            preNoRob = curNoRob;
			            
			        }
			        return Math.max(preRob, preNoRob);
			    }
			}
		3.5 House Robber II
			//O(1) Space, use the above method, one start from 0 to length - 1, 
			//The other start from 1 to length, get the maximum one
			public class Solution {
			    public int rob(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        if (nums.length == 1) {
			            return nums[0];
			        }
			        int robFromStart = rob(nums, 0, nums.length - 1);
			        int robNoFromStart = rob(nums, 1, nums.length); 
			        return Math.max(robFromStart, robNoFromStart);
			    }
			    public int rob(int[] nums, int start, int end) {
			        int preRob = 0;
			        int preNoRob = 0;
			        for (int i = start; i < end; i++) {
			            int curRob = preNoRob + nums[i];
			            int curNoRob = Math.max(preRob, preNoRob);
			            
			            preRob = curRob;    
			            preNoRob = curNoRob;
			        }
			        return Math.max(preRob, preNoRob);
			    }  
			}
		3.6 Dungeon Game
		/*
		    用一个二维数组dp[][]表示到每个格子时，勇士到每一步时至少需要的魔力，如dp[i][j]表示勇士在[i, j]处至少需要dp[i][j]魔力才能到达[m, n]救出皇后。

		    技巧：从[m, n]往回遍历到[1, 1]

		    动规方程：如果当前位置魔力正且大于等于右边/下边需要的魔力，则该处不需要额外的魔力，否则，勇士到达该处时需有一定的魔力来满足该处和右边/下边需要的魔力。
		*/
		public class Solution {
		    public int calculateMinimumHP(int[][] dungeon) {
		        if (dungeon == null || dungeon.length == 0) {
		            return 0;
		        }
		        int m = dungeon.length;
		        int n = dungeon[0].length;
		        int[][] dp = new int[m][n];
		        dp[m - 1][n - 1] = Math.max(0, -dungeon[m - 1][n - 1]);
		        for (int i = m - 2; i >= 0; i--) {
		            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 0);
		        }
		        for (int i = n - 2; i >= 0; i--) {
		            dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 0);
		        }
		        
		        for (int i = m - 2; i >= 0; i--) {
		            for (int j = n - 2; j >= 0; j--) {
		                  dp[i][j] = Math.max(Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j], 0);
		            }
		        }
		        return dp[0][0] + 1;
		    }
		}
		3.7 Burst Balloons
		/*
			Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

			Find the maximum coins you can collect by bursting the balloons wisely.

			Note: 
			(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
			(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

			Example:

			Given [3, 1, 5, 8]

			Return 167

			nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
			coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
		*/
		//Solution1: DP
		public class Solution {
		    public int maxCoins(int[] iNums) {
		        int[] nums = new int[iNums.length + 2];
		        int n = 1;
		        for (int x : iNums) {
		            if (x > 0) {
		                nums[n++] = x;
		            }
		        }
		        nums[0] = nums[n++] = 1;


		        int[][] dp = new int[n][n];
		        for (int k = 2; k < n; ++k)
		            for (int left = 0; left < n - k; ++left) {
		                int right = left + k;
		                for (int i = left + 1; i < right; ++i)
		                    dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
		            }
		        return dp[0][n - 1];
		    }
		}

4. Buy And Sell Stock Problem
		4.1 Best Time To Buy And Sell Stock I
			/*
				Say you have an array for which the ith element is the price of a given stock on day i.
				If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
			*/
			//每次记录更新最小买入价格，最大的利润一定是减去最小价格
			public class Solution {
			    public int maxProfit(int[] prices) {
			        if (prices == null || prices.length == 0) {
			            return 0;
			        }
			        int minBuy = prices[0];
			        int maxProfit = 0;
			        for (int i = 1; i < prices.length; i++) {
			            maxProfit = Math.max(prices[i] - minBuy, maxProfit);
			            minBuy = Math.min(minBuy, prices[i]);
			        }
			        return maxProfit;
			    }
			}
		4.2 Best Time To Buy And Sell Stock II
			/*
				能进行任意多次交易，但是买入必须在上一次卖完以后才能实现
			*/
			public class Solution {
			    public int maxProfit(int[] prices) {
			        int res = 0;
			        for (int i = 1; i < prices.length; i++) {
			            if (prices[i] - prices[i - 1] > 0) {
			                res += prices[i] - prices[i - 1];
			            }
			        }
			        return res;
			    }
			}
		4.3 Best Time To Buy And Sell Stock III
			/*
				Say you have an array for which the ith element is the price of a given stock on day i.
				Design an algorithm to find the maximum profit. You may complete at most two transactions.

				Note:
				You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
			*/
			//Solution1:
			public class Solution {
			    public int maxProfit(int[] prices) {
			        if (prices == null || prices.length == 0) {
			            return 0;
			        }
			        int m = prices.length;
			        int[] left = new int[m];
			        int[] right = new int[m];
			        int minBuy = prices[0];
			        int profit = 0;
			        //维护一个最小买入价
			        for (int i = 1; i < m; i++) {
			            left[i] = Math.max(left[i - 1], prices[i] - minBuy);
			            minBuy = Math.min(minBuy, prices[i]);
			        }
			        int maxSell = prices[m - 1];
			        //维护一个最大卖价
			        for (int i = m - 2; i >= 0; i--) {
			            right[i] = Math.max(right[i + 1], maxSell - prices[i]);
			            maxSell = Math.max(maxSell, prices[i]);
			        }
			        for (int i = 0; i < m - 1; i++) {
			            profit = Math.max(profit, left[i] + right[i]);
			        }
			        return profit;
			    }
			}
			//Solution2: space:O(1)
			public int maxProfit(int[] prices) {
			    int oneBuy = Integer.MIN_VALUE;
			    int oneBuyOneSell = 0;
			    int twoBuy = Integer.MIN_VALUE;
			    int twoBuyTwoSell = 0;
			    for(int i = 0; i < prices.length; i++){
			        oneBuy = Math.max(oneBuy, -prices[i]);//we set prices to negative, so the calculation of profit will be convenient
			        oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
			        twoBuy = Math.max(twoBuy, oneBuyOneSell - prices[i]);//we can buy the second only after first is sold
			        twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
			    }

			    return Math.max(oneBuyOneSell, twoBuyTwoSell);
			}
		4.4 Best Time To Buy And Sell Stock IV
			/*
				Say you have an array for which the ith element is the price of a given stock on day i.

				Design an algorithm to find the maximum profit. You may complete at most k transactions.

				Note:
				You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
			*/
			/*
		    	dp[i, j] represents the max profit up until prices[j] using at most i transactions.

		  		dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj])       { jj in range of [0, j-1] }
		           		 = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
		 		dp[0, j] = 0; 0 transactions makes 0 profit
		  		dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
		 	*/
			public class Solution {
			    public int maxProfit(int k, int[] prices) {
			         int m = prices.length;
			         if (m <= 1) {
			             return 0;
			         }
			         if (k >= m / 2) {
			             int res = 0;
			             for (int i = 1;i < m; i++) {
			                 if (prices[i] - prices[i - 1] > 0) {
			                     res += prices[i] - prices[i - 1];
			                 }
			             }
			             return res;
			         }
			         int[][] dp = new int[k + 1][m];
			         for (int i = 1; i <= k; i++) {
			             int localMax = dp[i - 1][0] - prices[0];
			             for (int j = 1; j < m; j++) {
			                 //localMax means the maximum profit of just doing at most i-1 transactions, 
			                 //using at most first j-1 prices, and buying the stock at price[j]  this is used for the next loop.
			                 dp[i][j] = Math.max(dp[i][j - 1], prices[j] + localMax);
			                 localMax = Math.max(localMax, dp[i - 1][j - 1] - prices[j]);
			             }
			         }
			         return dp[k][m - 1];
			    }
			}
		4.5 Best Time To Buy And Sell Stock With Cooldown (V)
			/*
				Say you have an array for which the ith element is the price of a given stock on day i.
				Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

				You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
				After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
				Example:

				prices = [1, 2, 3, 0, 2]
				maxProfit = 3
				transactions = [buy, sell, cooldown, buy, sell]
			*/
			/*
				Analysis: 
					1.
						buy[i] means before day i what is the maxProfit for any sequence end with buy.
						sell[i] means before day i what is the maxProfit for any sequence end with sell.
						rest[i] means before day i what is the maxProfit for any sequence end with rest.

					2.
						buy[i]  = max(rest[i-1]-price, buy[i-1]) 
						sell[i] = max(buy[i-1]+price, sell[i-1])
						rest[i] = max(sell[i-1], buy[i-1], rest[i-1])

					3.
						Well, the answer lies within the fact that buy[i] <= rest[i] which means rest[i] = max(sell[i-1], rest[i-1]). 
						That made sure [buy, rest, buy] is never occurred.

					4.
						A further observation is that and rest[i] <= sell[i] is also true therefore
						rest[i] = sell[i-1]
						Substitute this in to buy[i] we now have 2 functions instead of 3:
						buy[i] = max(sell[i-2]-price, buy[i-1])
						sell[i] = max(buy[i-1]+price, sell[i-1])

			*/
			public class Solution {
			    public int maxProfit(int[] prices) {
			        if (prices == null || prices.length == 0) {
			            return 0;
			        }
			        int m = prices.length;
			        int sell = 0;
			        int preSell = 0;
			        int buy = Integer.MIN_VALUE;
			        int preBuy = 0;
			        for (int i = 0; i < prices.length; i++) {
			            preBuy = buy;
			            buy = Math.max(preSell - prices[i], preBuy);
			            preSell = sell;
			            sell = Math.max(preSell, preBuy + prices[i]);
			        }
			        return sell;
			    }
			}

5. Math Problem
		5.0 Perfect Squares
			/*
				Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
				For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
			*/
			public class Solution {
			    public int numSquares(int n) {
			        int[] dp = new int[n + 1];
			        dp[1] = 1;
			        for (int i = 2; i <= n; i++) {
			            dp[i] = Integer.MAX_VALUE;
			            for (int j = 1; j <= Math.sqrt(i); j++) {
			                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
			            }
			        }
			        return dp[n];
			    }
			}
		5.1 Ugly Number II
			/*
				Write a program to find the n-th ugly number.
				Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

				Note that 1 is typically treated as an ugly number.
			*/
			public class Solution {
			    public int nthUglyNumber(int n) {
			        if (n <= 1) {
			            return n;
			        }
			        int[] ugly = new int[n];
			        ugly[0] = 1;
			        int index2, index3, index5;
			        int factor2 = 2, factor3 = 3, factor5 = 5;
			        for (int i = 1; i < n; i++) {
			            ugly[i] = Math.min(Math.min(factor2, factor3), factor5);
			            if (ugly[i] == factor2) {
			                factor2 = factor2 * ugly[++index2];
			            }
			            if (ugly[i] == factor3) {
			                factor3 = factor3 * ugly[++index3];
			            }
			            if (ugly[i] == factor5) {
			                factor5 = factor5 * ugly[++index5];
			            }
			        }
			    }
			}
		5.1 Triangle
			/*
				Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

				For example, given the following triangle
				[
				     [2],
				    [3,4],
				   [6,5,7],
				  [4,1,8,3]
				]
				The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

				Note:
				Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
			*/
			//Key Point: calculate from the lowest row， dp[i][j] = dp[i - 1][j] + dp[i - 1][j + 1]
			//Solution1: O(1) spcae
			public class Solution {
			    public int minimumTotal(List<List<Integer>> triangle) {
			        if (triangle == null || triangle.size() == 0) {
			            return 0;
			        }
			        int m = triangle.size();
			        for (int i = m - 2; i >= 0; i--) {
			            for (int j = 0; j < triangle.get(i).size(); j++) {
			                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1))) ;
			            }
			        }
			        return triangle.get(0).get(0);
			    }
			}
			//Solution2: O(n) space
			public class Solution {
				public int minimumTotal(List<List<Integer>> triangle) {
				    int[] dp = new int[triangle.size()+1];
				    for(int i = triangle.size() - 1; i >= 0; i--){
				        for(int j = 0; j < triangle.get(i).size(); j++){
				            dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
				        }
				    }
				    return dp[0];
				}
			}
		5.2 Maximal Square
			/*
				Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
				For example, given the following matrix:

				1 0 1 0 0
				1 0 1 1 1
				1 1 1 1 1
				1 0 0 1 0
				Return 4.
			*/
			/*	
				Key Point:dp[i][j] 表示以 坐标(i - 1, j - 1)为最右下顶点的正方形的最长边长
			*/
			//O(m * n) space
			public class Solution {
			    public int maximalSquare(char[][] matrix) {
			        if (matrix == null || matrix.length == 0) {
			            return 0;
			        }
			        int m = matrix.length;
			        int n = matrix[0].length;
			        int[][] dp = new int[m + 1][n + 1];
			        int maxLen = dp[0][0];
			        for (int i = 1; i <= m; i++) {
			            for (int j = 1; j <= n; j++) {
			                if (matrix[i - 1][j - 1] == '1') {
			                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
			                    maxLen = Math.max(maxLen, dp[i][j]);
			                }
			            }
			        }
			        return maxLen * maxLen;
			    }
			}
			//O(n) space
			public class Solution {
				public int maximalSquare(char[][] matrix) {
			        if (matrix == null || matrix.length == 0) {
			            return 0;
			        }
			        int m = matrix.length;
			        int n = matrix[0].length;
			        int[] dp = new int[n + 1];
			        int topLeft = 0;
			        int maxLen = 0;
			        for (int i = 1; i <= m; i++) {
			            for (int j = 1; j <= n; j++) {
			                int temp = dp[j];//dp[j] 此时是 dp[i - 1][j], 也就是后一个遍历的dp[i][j + 1] 的 dp[i - 1][j + 1 - 1]
			                if (matrix[i - 1][j - 1] == '1') {
			                    dp[j] = Math.min(topLeft, Math.min(dp[j], dp[j - 1])) + 1;
			                    maxLen = Math.max(dp[j], maxLen);
			                } else {
			                    dp[j] = 0;
			                }
			                topLeft = temp;
			            }
			        }
			        return maxLen * maxLen;
			    }
			}

6. Tree
		6.1 Unique Binary Search Trees I
			/*
				Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

				For example,
				Given n = 3, there are a total of 5 unique BST's.

				   1         3     3      2      1
				    \       /     /      / \      \
				     3     2     1      1   3      2
				    /     /       \                 \
				   2     1         2                 3
			*/
			public class Solution {
			    public int numTrees(int n) {
			        if (n == 0 || n == 1) {
			            return n;
			        }
			        int[] dp = new int[n + 1];
			        dp[0] = 1;
			        dp[1] = 1;
			        for (int i = 2; i <= n; i++) {
			            for (int j = 0; j < i; j++) {
			                dp[i] += dp[j] * dp[i - j - 1];
			            }
			        }
			        return dp[n];
			    }
			}
		6.2 Unique Binary Search Trees II
			public class Solution {
			    public List<TreeNode> generateTrees(int n) {
			        if (n == 0) {
			            return new ArrayList<>();
			        }
			        return helper(1, n);
			    }
			    public List<TreeNode> helper (int left, int right) {
			        List<TreeNode> res = new ArrayList<>();
			        if (left > right) {
			            res.add(null);
			            return res;
			        }
			        for (int i = left; i <= right; i++) {
			            List<TreeNode> leftList = helper(left, i - 1);
			            List<TreeNode> rightList = helper(i + 1, right);
			            for (int j = 0; j < leftList.size(); j++) {
			                for (int k = 0; k < rightList.size(); k++) {
			                    TreeNode root = new TreeNode(i);
			                    root.left = leftList.get(j);
			                    root.right = rightList.get(k);
			                    res.add(root);
			                }
			            }
			        }
			        return res;
			    }
			}


7. Backpack Problem
			7.1 Coin Seris
				7.1.1 Coin Change
				/*
					You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

					Example 1:
					coins = [1, 2, 5], amount = 11
					return 3 (11 = 5 + 5 + 1)

					Example 2:
					coins = [2], amount = 3
					return -1.

					Note:
					You may assume that you have an infinite number of each kind of coin.
				*/
				//Key Point: (1)保证硬币要比amount面额要小 （2)保证可以找回amount - coins[j]这样的面额 (3)综合以上两点求最小值
				public class Solution {
				    public int coinChange(int[] coins, int amount) {
				        int[] dp = new int[amount + 1];
				        dp[0] = 0;
				        for (int i = 1; i <= amount; i++) {
				            dp[i] = Integer.MAX_VALUE;
				            for (int j = 0; j < coins.length; j++) {
				                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE) {
				                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				                }
				            }
				        }
				        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
				    }
				}


9. Others
		9.1 Range Sum Query - Immutable 
			public class NumArray {
				private int[] dp;
				public NumArray(int[] nums) {
					dp = Arrays.copyOf(nums, nums.length);
					for (int i = 1; i < dp.length; i++) {
						dp[i] += dp[i - 1];
					}
				}
				public int sumRange(int i, int j) {
					 if (i == 0) {
					 	return dp[j];
					 } else {
					 	return dp[j] - dp[i - 1];
					 }
				}
			}
		9.2 Range Sum Query 2D - Immutable
			//Best Solution1
			/*
				Complexity analysis:
				The pre-computation takes O(mn) time. 
				It uses O(mn) space to store the cumulative region sum. 
				Each sumRegion query takes O(1) time.
			*/
			public class NumMatrix {
			    private int[][] dp;
			    public NumMatrix(int[][] matrix) {
			        if (matrix == null || matrix.length == 0) {
			            return;
			        }
			        int m = matrix.length;
			        int n = matrix[0].length;
			        dp = new int[m + 1][n + 1];
			        for (int i = 0; i < m; i++) {
			            for (int j = 0; j < n; j++) {
			                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j]; 
			            }
			        }
			    }

			    public int sumRegion(int row1, int col1, int row2, int col2) {
			        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
			    }
			}
			//Solution2
			/*
				Complexity analysis:
				The pre-computation takes O(mn) time. 
				It uses O(mn) space to store the cumulative sum of all rows. 
				The sumRegion query takes O(m) time.
			*/
			public class NumMatrix {
			    private int[][] dp;
			    public NumMatrix(int[][] matrix) {
			        dp = matrix;
			        for (int i = 0; i < dp.length; i++) {
			            for (int j = 1; j < dp[0].length; j++) {
			                dp[i][j] += dp[i][j - 1];
			            }
			        }
			    }
			    public int sumRegion(int row1, int col1, int row2, int col2) {
			        int res = 0;
			        for (int i = row1; i <= row2; i++) {
			           res += dp[i][col2];
			           if (col1 != 0) {
			              res -= dp[i][col1 - 1];
			           }
			        }
			        return res;
			    }
			}










