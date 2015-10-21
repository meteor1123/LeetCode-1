/*
	Distinct Subsequences
	Given a string S and a string T, count the number of distinct subsequences of T in S.

	A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

	Here is an example:
	S = "rabbbit", T = "rabbit"

	Return 3.

	Show Tags:DP, String
*/

/*
	Solution: 
	 //题意：只可以用删除字符的方法从第一个字符串变换到第二个字符串，求出一共有多少种变换方法。
	 (注意是S变换成T，并且变换后S是完全 匹配T)
    //     r a b b b i t（S.length 行     （T.length, i）列
    //   1 1 1 1 1 1 1 1
    // r 0 1 1 1 1 1 1 1
    // a 0 0 1 1 1 1 1 1
    // b 0 0 0 1 2 3 3 3
    // b 0 0 0 0 1 3 3 3
    // i 0 0 0 0 0 0 3 3
    // t 0 0 0 0 0 0 0 3  
// 从这个表可以看出，无论T的字符与S的字符是否匹配，dp[i][j] = dp[i][j - 1].
   就是说，假设S已经匹配了j - 1个字符，得到匹配个数为dp[i][j - 1].
   现在无论S[j]是不是和T[i]匹配，匹配的个数至少是dp[i][j - 1]。
*/

/*
    At first, we set the dynamic programming array dp[i][j],
    dp[i][j] means ,the String T start from 0 to index i position ,
                    and the String S start from 0 to index j position.
                    the matched subsequence amount.

*/
public class Solution {

    /*
     首先设置动态规划数组dp[i][j]，表示S串中从开始位置到第i位置与T串从开始位置到底j位置匹配的子序列的个数。

     如果S串为空，那么dp[0][j]都是0；
     如果T串为空，那么dp[i][j]都是1，因为空串为是任何字符串的字串。

     可以发现规律，dp[i][j] 至少等于 dp[i][j-1]。

     当i=2，j=1时，S 为 ra，T为r，T肯定是S的子串；这时i=2，j=2时，S为ra，T为rs，
     T现在不是S的子串，当之前一次是子串所以现在计数为1.
     同时，如果字符串S[i-1]和T[j-1]（dp是从1开始计数，字符串是从0开始计数）匹配的话，
     dp[i][j]还要加上dp[i-1][j-1]

     例如对于例子： S = "rabbbit", T = "rabbit"

     当i=2，j=1时，S 为 ra，T为r，T肯定是S的子串；当i=2，j=2时，S仍为ra，T为ra，这时T也是S的子串，
     所以子串数在dp[2][1]基础上加dp[1][1]。
    */




    /*  
        这里我们维护res[i][j]，对应的值是S的前i个字符和T的前j个字符有多少个可行的序列（注意这道题是序列，不是子串，
        也就是只要字符按照顺序出现即可，不需要连续出现）。
        下面来看看递推式，假设我们现在拥有之前的历史信息，我们怎么在常量操作时间内得到res[i][j]。
        1.假设S的第i个字符和T的第j个字符不相同:
            那么就意味着res[i][j]的值跟res[i-1][j]是一样的，前面该是多少还是多少，而第i个字符的加入也不会多出来任何可行结果。

        2.如果S的第i个字符和T的第j个字符相同，
            那么所有res[i-1][j-1]中满足的结果都会成为新的满足的序列，当然res[i-1][j]的也仍是可行结果，
            所以res[i][j]=res[i-1][j-1]+res[i-1][j]。所以综合上面两种情况，
            res[i][j]= (S[i] == T[j] ? res[i-1][j-1] : 0) + res[i-1][j]
    */
    //time complexity: O(m * n)

    /*
        At first, we set the dynamic programming array dp[i][j],
        dp[i][j] means ,the String T start from 0 to index i position ,
                    and the String S start from 0 to index j position.
                    the matched subsequence amount.
    */

    // two-dimension dp 
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() < T.length()) {
            return 0;
        }
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        dp[0][0] = 1;//initial
        
        for(int j = 1; j <= T.length(); j++)//S is empty
            dp[0][j] = 0;
            
        for (int i = 1; i <= S.length(); i++)//T is empty
            dp[i][0] = 1;
           
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {//means this character can be retained or drop
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];//so the convert amount equals retain the character plus do not retain this character.
                } else {
                    dp[i][j] = dp[i - 1][j];//no matter the char i and char j equals or not, at least has dp[i - 1][j]
                }

                // dp[i][j] = dp[i - 1][j];  简化版本
                // if (s.charAt(i - 1) == t.charAt(j - 1)) {
                //     dp[i][j] += dp[i - 1][j - 1];
                // } 
            }
        }
     
        return dp[S.rlength()][T.length()];
    }

    //one -dimension dp
    public int numDistinct(String S, String T) {
        // write your code here
        if (S == null || T == null || S.length() < T.length()) {
            return 0;
        }
        int[] dp = new int[T.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= S.length(); i++) {
            for (int j = T.length(); j > 0; j--) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        return dp[T.length()];
    }