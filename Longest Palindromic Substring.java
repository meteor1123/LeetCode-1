/*
	Longest Palindromic Substring
	Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/


public class Solution {
	//Brute force1 by myself
    public String longestPalindrome(String s) {
        String res = "";
        if (s == null || s.length() == 0) {
            return res;
        }
        int maxLen = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = s.length() - 1; j > i; j--) {
                int left = i;
                int right = j;
                if (s.charAt(left) != s.charAt(right)) {
                    continue;
                }
                while (left < right && s.charAt(left) == s.charAt(right)) {
                    if (left + 1 == right) {
                        if (j - i + 1 > maxLen) {
                        	maxLen = j - 1 + 1;
                            res = s.substring(i, j + 1);
                        }
                        // break;
                    }           
                    left++;
                    right--;
                    if (left > s.length() - 1 || right < 0) {
                    	break;
                    }
                }
            }
        }
        return res;
    }

    //Brute force by xiaoyingzi
    public String longestPalindrome(String s) {
        int maxLen = 0;
        String res = null;
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int curLen = j - i;
                String cur = s.substring(i, j + 1);
                if (isPalindromic(cur)){
                	if (curLen > maxLen) {
                		maxLen = Math.max(maxLen, curLen);
                        res = cur;
                	}
                }
            }
        }
        return res;
    }
	
	public boolean isPalindromic(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    //中心回探法
/*
    基本思路是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）往两边同时进行扫描，
    直到不是回文串为止。假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。
    对于每个中心往两边扫描的复杂度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)
    */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        //to store the maxLen of palindrome
        String longest = s.substring(0, 1);
        
        //iterative every center divide point
        for (int i = 0; i < s.length(); i++) {
            // like abba, a , b , b , a is center
            // get longest palindrome with center of i
            String temp = helper(s, i, i);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
            
            // like abba, ab bb ba 's interval is divide interval 
            // get longest palindrome with center of i, i+1
            temp = helper(s, i, i + 1);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
        }
        return longest;
    }
    
    public String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            //notice that, begin go to left, end go to right, from center to edge! 
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }
}

