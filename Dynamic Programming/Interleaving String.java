/*
	Interleaving String
	Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

	For example,
	Given:
	s1 = "aabcc",
	s2 = "dbbca",

	When s3 = "aadbbcbcac", return true.
	When s3 = "aadbbbaccc", return false.
	Tags: DP
*/

/*

    dp[i+1][j+1]:表示s1[0...i]与s2[0...j]能否交替形成s3[0...i+j+1]部分.

    状态转移方程:

    dp[i+1][j+1] = (dp[i][j+1] && s1[i] == s3[i+j+1]) | (dp[i+1][j] && s2[j] == s3[i+j+1]);

*/

//Prefer solution
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        
        if (m + n != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        //initialize s1 = 0, dp[0][i + 1] 完全取决于 dp[0][i] 以及 s2.charAt(i) == s3.charAt(i);
        for (int i = 0; i < n; i++) {
            dp[0][i + 1] = dp[0][i] && s2.charAt(i) == s3.charAt(i);
        }
        //同上所述
        for (int i = 0; i < m; i++) {
            dp[i + 1][0] = dp[i][0] && s1.charAt(i) == s3.charAt(i);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                dp[i + 1][j + 1] = (dp[i][j + 1] && s1.charAt(i) == s3.charAt(i + j + 1)) ||
                                   (dp[i + 1][j] && s2.charAt(j) == s3.charAt(i + j + 1));
            }
        }
        return dp[m][n];
    }
}
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        ////dp[i][j]表示用s1的前i个字符和s2的前j个字符能不能按照规则表示出s3的前i+j个字符
        //dp[i][j] means use (0 ~ i-1) length i string and (0 ~ j - 1) length j string,
        //whether can generate i + j string of s3
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1.length(); i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            } else {
                break;
            }
        }
        
        for (int j = 1; j <= s2.length(); j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                char c = s3.charAt(i + j - 1);
                if (c == s1.charAt(i - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                if (c == s2.charAt(j - 1) && dp[i][j - 1]){
                    dp[i][j] = true;
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

