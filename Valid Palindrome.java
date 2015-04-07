/*
	Valid Palindrome
	Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

	For example,
	"A man, a plan, a canal: Panama" is a palindrome.
	"race a car" is not a palindrome.

	Note:
	Have you consider that the string might be empty? This is a good question to ask during an interview.

	For the purpose of this problem, we define empty string as valid palindrome.
*/

/*
	除了数字和英文字母以外的字符都不算进palindrome的比较，所以遇到其他字符就跳过，对下个字符进行比较
	//except from number and alphbet,other characters are not included to the compare, just skip
*/

public class Solution {
     public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        s = s.toLowerCase();
        int low1 = 'a';
        int high1 = 'z';
        int low2 = '0';
        int high2 = '9';
        
        int low = 0;
        int high = s.length() - 1;
        
        while (low < high) {
            if ((s.charAt(low) < low1 || s.charAt(low) > high1)
                && (s.charAt(low) < low2 || s.charAt(low) > high2)) {
                    low++;
                    continue;
                }
            if ((s.charAt(high) < low1 || s.charAt(high) > high1) 
                && (s.charAt(high) < low2 || s.charAt(high) > high2)) {
                    high--;
                    continue;
                }
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                return false;
            }
        }
        return true;
    }
}