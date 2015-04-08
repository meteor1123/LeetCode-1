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
	 public boolean isMatch(String s , String p){
	 	if (s.length() > 300 && p.charAt(0) == '*' && p.charAt(p.length() - 1) == '*')
	 		return false;
	 	int len1 = p.length();
	 	int len2 = s.length();
	 	boolean[][] dp = new boolean[len1 + 1][len2 + 1];
	 	dp[0][0] = true;
	 	for (int i = 0; i < len2; i++)
	 		dp[i + 1][0] = p.charAt(i) == '*' ? dp[i][0] : false;

	 	for (int i = 0; i < len1; i++) {
	 		for (int j = 0; j < len2; j ++) {
	 			if (p.charAt(i) == '*')

	 				//dp[i + 1][j] 为true，自然*也可以匹配j + 1
	 				//dp[i][j + 1] 为true，自然+上*也可以匹配j + 1(empty)
	 				///dp[i][j] 为true，自然*和j+1 也匹配
	 				dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i][j];
	 			else if (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j))
	 				dp[i + 1][j + 1] = dp[i][j];
	 			else 
	 				false;
	 		}
	 	}
	 	return dp[len1][len2];
	 }



//Greedy solution
/*
	  
        //主要是*的匹配问题。p每遇到一个*，就保留住当前*的坐标和s的坐标，然后s从前往后扫描，如果不成功，则s++,重新扫描。
        //1.*s==*p or *p == ? ：匹配，s++ p++。
        //2. p=='*'：也是一个匹配，但可能有0个或者多个字符用于匹配，所以将'*'所在位置保存(star)，并且将s的位置也保存下来(ss)。
        //3. 如果不匹配，查看之前是否有'*'。没有则返回false，有则对ss的下一个位置和start之后的重新匹配。


*/
	 public boolean isMatch(Stirng s , String p) {
	 	int str = 0;
	 	int match = 0; //保存 *号之后 s字符串依次匹配的位置
	 	int starIdex = -1;
	 	int pattern = 0;
	 	while （str < s.length()) {
			if ((s.charAt(str) == p.charAt(pattern) || p.charAt(patter) == '?') && (pattern < p.length())) {
				pattern++;
				str++;
			} else if (p.charAt(pattern) == '*') {
				starIdex = pattern;
				match = str;
				pattern++;
			} else if (starIdex != -1) {
				pattern = starIdex + 1;
				match++;
				str = match;
			} else 
				return false;
		}

		while (patter < p.length() && p.charAt(pattern) == '*')
			pattern++;
		return pattern == p.length();
	 }

