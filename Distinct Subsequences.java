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
//     r a b b b i t（S.length,j）列     （T.length, i）行
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
// public int numDistinct(String S, String T) {
//         int[][] dp = new int[T.length() + 1][S.length() + 1];
//         dp[0][0] = 1;
//         //if String S is empty, all of the dp[i][0] is zero
//         for(int i = 0; i <= T.length(); i++) {
//             dp[i][0] = 0;
//         }
//         //if String T is empty, all of the dp[0][j] is 1, since we need to delete 
//         //all the subsequnce with String S
//         for(int j = 0; j <= S.length(); j++) {
//             dp[0][j] = 1;
//         }
        
//         for(int i = 1; i <= T.length(); i++) {
//             for(int j = 1; j <=S.length(); j++) {
//                 dp[i][j] = dp[i][j - 1];
//                 if(T.charAt(i - 1) == S.charAt(j - 1)) {
//                     dp[i][j] += dp[i - 1][j - 1];
//                 }
//             }
//         }
//         return dp[T.length()][S.length()];
//     }
// }

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
动态规划，定义dp[i][j]为字符串i变换到j的变换方法。
如果S[i]==T[j]，那么dp[i][j] = dp[i-1][j-1] + dp[i-1][j]。
意思是: 如果当前S[i]==T[j]，那么当前这个字母即可以保留也可以抛弃，means this character can be retained or drop
       所以变换方法等于保留这个字母的变换方法加上不用这个字母的变换方法。
       so the convert amount equals retain the character plus do not retain this character.

如果S[i]!=T[i]，那么dp[i][j] = dp[i-1][j]，
意思是: 如果当前字符不等，那么就只能抛弃当前这个字符。
       递归公式中用到的dp[0][0] = 1，dp[i][0] = 0（把任意一个字符串变换为一个空串只有一个方法）
 */
    public int numDistinct(String S, String T) {
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        dp[0][0] = 1;//initial
        
        for(int j = 1; j <= T.length(); j++)//S is empty
            dp[0][j] = 0;
            
        for (int i = 1; i <= S.length(); i++)//T is empty
            dp[i][0] = 1;
           
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                dp[i][j] = dp[i - 1][j];
                if (S.charAt(i - 1) == T.charAt(j - 1)) 
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }
     
        return dp[S.length()][T.length()];
    }