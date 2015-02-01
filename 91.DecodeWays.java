/*
	Decode Ways 
	A message containing letters from A-Z is being encoded to numbers using the following mapping:
	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26

	Given an encoded message containing digits, determine the total number of ways to decode it.

	For example,
	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
	The number of ways decoding "12" is 2.
	Tags:Dp, String
*/


/*
    当我们知道了n-2长度的字符串能够解释的数目以及n-1长度的字符串能够解释的数目时，我们可以判读如下两个条件：
        1）若第n个字符在1到9之间，则n长度的字符串能够解释的数目包含n-1长度字符串能够解释的数目。例ABZ[n-1]A[n] = ABZ[n-1] + 1
        2）若第n-1个字符与第n个字符可以解释为一个字母时，则n长度的字符串能够解释的数目包含n-2长度字符串能够解释的数目。
            例  ABA[n-1]A[n] = AB[n-2] + AA ,因为AA = 11 等于 K ,所以ABAA = ABK
*/

public class Solution {
	public int numDecodings(String s) {
         if (s.length() == 0 || s == null || s == "0")
            return 0;
         int[] dp = new int[s.length() + 1];
         dp[0] = 1;
         //dp[i] mean the string has how many decode amount of s.substring(0, i)
         if (isValid(s.substring(0, 1)))
            dp[1] = 1;
         else 
            dp[1] = 0;
        
         for (int i = 2; i <= s.length(); i++) {
            if (isValid(s.substring(i - 1, i)))
                dp[i] += dp[i - 1];
            if (isValid(s.substring(i - 2, i)))
                dp[i] += dp[i - 2];
         }
         return dp[s.length()];
     }
     
     public boolean isValid(String s) {
         if (s.charAt(0) == '0')
            return false;
         int code = Integer.parseInt(s);
         return code >= 1 && code <= 26;
     }
}