/*
	Longest Common Prefix
	Write a function to find the longest common prefix string amongst an array of strings.
*/


public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        //Find the minimum length of a string 
        for (String s : strs) {
            if (s.length() < minLen) {
                minLen = s.length();
            }
        }
        //use strs[0] to check the every character is match or not
        String str = strs[0];

        //iterative the string[0],and compare the char with others Strings which in the array
        //Once found is not compared,return the substring,because this is the longest common prefix.
        for (int i = 0; i < minLen; i++) {
            char c = str.charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != c) {
                    return str.substring(0, i);
                }
            }
        }
        return strs[0].substring(0, minLen);
    }
}