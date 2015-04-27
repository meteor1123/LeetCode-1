/*
	Longest Valid Parentheses 
	Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

	For "(()", the longest valid parentheses substring is "()", which has length = 2.

	Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
	Tags: DP, String, Stack
*/

public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;  
        }
        Stack<Integer> stack = new Stack<Integer>();
        int start = 0;
        int maxLen = 0;
        //use a stack to store the unvalid parentheses
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                ////if stack is empty, it means that we already found a complete valid combo and pop to the stack
                // or since we meet a right parenthesesm so we need to update the last index. to store the unmatched  position
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    maxLen = stack.isEmpty() ? Math.max(maxLen, i - start + 1) : Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}