/*
	Wildcard Matching 
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
	Tags:Dynamic Programming,BackTracking,Greedy,string
*/

/*
	在这里'?'相当于那边的'.'，而'*'相当于那边的'.*'，因为这里'*'就可以代替任何字符串，不需要看前面的字符，所以处理起来更加简单。
*/
 
//如果p[i]！='*'，dp[i][j] == true 当 dp[i-1][j-1]==true &&（p[i]==s[j]||p[i]='.'）
//如果p[i]=='*'，dp[i][j]== true 当 其中一个m使得 dp[i-1][m]==true，where 0 <= m < j.
	public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                 dp[0][i] = dp[0][i - 1];
            }
        }
	    //dp[i][j - 1] 为true，自然*也可以匹配j
		//dp[i - 1][j] 为true，自然+上*也可以匹配j(empty)
		///dp[i - 1][j - 1] 为true，自然*和j 也匹配
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
            	if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }
	// public boolean isMatch(String s , String p){
 //        if( s.length()>300 && p.charAt(0) == '*'&& p.charAt(p.length()-1)=='*')
 //            return false;
	// 	int len1 = s.length();
	// 	int len2 = p.length();
	// 	boolean[][] dp = new boolean[len1 + 1][len2 + 1];
	// 	dp[0][0] = true;
	// 	for (int i = 0; i < len2; i++) {
	// 		dp[0][i + 1] = p.charAt(i) == '*' ? dp[0][i] :false;
	// 	}

	// 	for (int i = 0; i < len1; i++) {
	// 		for (int j = 0; j < len2; j++) {
	// 			if (p.charAt(j) == '*') {
	// 			    //dp[i + 1][j] 为true，自然*也可以匹配j + 1
	//  				//dp[i][j + 1] 为true，自然+上*也可以匹配j + 1(empty)
	//  				///dp[i][j] 为true，自然*和j+1 也匹配
	// 				dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j] || dp[i][j];
	// 			} else if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
	// 				dp[i + 1][j + 1] = dp[i][j];
	// 			} else {
	// 				dp[i + 1][j + 1] = false;
	// 			}
	// 		}
	// 	}
	// 	return dp[len1][len2];
	// }

//Greedy solution
/*
	  
        //主要是*的匹配问题。p每遇到一个*，就保留住当前*的坐标和s的坐标，然后s从前往后扫描，如果不成功，则s++,重新扫描。
        //1.*s==*p or *p == ? ：匹配，s++ p++。
        //2. p=='*'：也是一个匹配，但可能有0个或者多个字符用于匹配，
        	 所以将'*'所在位置保存(star)，并且将s的位置也保存下来(ss)。
        //3. 如果不匹配，查看之前是否有'*'。没有则返回false，有则对ss的下一个位置和start之后的重新匹配。


*/
	public boolean isMatch(String str, String pattern) {
        int s = 0;
        int p = 0;
        int match = 0;
        int starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            } else if (p < pattern.length() && pattern.charAt(p) == '*'){// * found, only advancing pattern pointer
                starIdx = p;
                match = s;
                p++;
            } else if (starIdx != -1){ // last pattern pointer was *, advancing string pointer
                p = starIdx + 1;
                match++;
                s = match;
                
            //current pattern pointer is not star, last patter pointer was not *
            } else {//characters do not match
                return false;
            }
           
            
           
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

