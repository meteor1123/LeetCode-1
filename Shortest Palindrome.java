/*
	Shortest Palindrome
	Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

	For example:
	Given "aacecaaa", return "aaacecaaa".
	Given "abcd", return "dcbabcd".
	Tags: String
*/

/*

	1. 记原始字符串为s，s的反转字符串为rev_s。
	2. 构造字符串l = s + '#' + rev_s，其中'#'为s中不会出现的字符，添加'#'是为了处理输入为空字符串的情形。

	3. 对字符串l执行KMP算法，可以得到“部分匹配数组”p（也称“失败函数”）

	4. 我们只关心p数组的最后一个值p[-1]，因为它表明了rev_s与s相互匹配的最大前缀长度。

	5. 最后只需要将rev_s的前k个字符与原始串s拼接即可，其中k是s的长度len(s)与p[-1]之差。
*/
/*
	We can construct the following string and run KMP algorithm on it: 
	(s) + (some symbol not present in s) + (reversed string)
	1. After running KMP on that string as result we get a vector p with values of a prefix function for each character 
	   (for definition of a prefix function see KMP algorithm description). 

	2. We are only interested in the last value because it shows us the largest suffix of the reversed string 
	   that matches the prefix of the original string.

	3. So basically all we left to do is to add the first k characters of 
	   the reversed string to the original string

	4. where k is a difference between original string size and the prefix function
	   for the last character of a constructed string.
*/
public class Solution {
	public String shortestPalindrome(String s) {
        if (s.length() <=1 ) {
            return s;
        }
        String cur = s + " " + new StringBuilder(s).reverse().toString();
        int[] next = new int[cur.length()];
        for (int i = 1; i < cur.length(); i++) {
            int curindex = next[i - 1];
            while (curindex > 0 && cur.charAt(curindex) != cur.charAt(i)) {
                curindex = next[curindex - 1];
            }
            if (cur.charAt(curindex) == cur.charAt(i)) {
                next[i] = curindex + 1;
            }
        }
        return new StringBuilder(s.substring(next[cur.length() -1])).reverse().toString() + s;
    }
}