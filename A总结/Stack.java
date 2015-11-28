/*
	Dynamic Programming
*/



1. Parentheses Problem
		1.1	Valid Parentheses
			/*
				Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
				determine if the input string is valid.The brackets must close in the correct order, 
				"()" and "()[]{}" are all valid but "(]" and "([)]" are not.
			*/
			public class Solution {
			    public boolean isValid(String s) {
			        if (s == null || s.length() == 0) {
			            return true;
			        }
			        Stack<Character> stack = new Stack<>();
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (c == '(' || c == '[' || c == '{') {
			                stack.push(c);
			            } else {
			                if (stack.isEmpty()) {
			                    return false;
			                }
			                char peek = stack.pop();
			                if (peek == '(' && c == ')') {
			                    continue;
			                } else if (peek == '[' && c == ']') {
			                    continue;
			                } else if (peek == '{' && c == '}') {
			                    continue;
			                } else {
			                    return false;
			                }
			            }
			        }
			        if (!stack.isEmpty()) {
			            return false;
			        }
			        return true;
			    }
			}

		1.2 Generate Parentheses
			//Recursive
		    public List<String> generateParenthesis(int n) {
		        List<String> res = new ArrayList<>();
		        if (n < 1) {
		            return res;
		        }
		        helper(res, "", n, n);
		        return res;
		    }
		    public void helper(List<String> res, String item, int left, int right) {
		        if (left == 0 && right == 0) {
		            res.add(item);
		            return;
		        }
		        if (left > 0) {
		            helper(res, item + '(', left - 1, right);
		        } 
		        if (left < right) {
		            helper(res, item + ')', left, right - 1);
		        }
		    }
		    //Iteration
		    public List<String> generateParenthesis(int n) {
		        List<List<String>> lists = new ArrayList<>();
		        List<String> initList = new ArrayList<>();
		        initList.add("");
		        lists.add(initList);
		        for (int i = 1; i <= n; i++) {
		            List<String> res = new ArrayList<>();
		            for (int j = 0; j < i; j++) {
		                for (String first : lists.get(j)) {
		                    for (String second : lists.get(i - 1 - j)) {
		                        res.add("(" + first + ")" + second);
		                    }
		                }
		            }
		            lists.add(res);
		        }
		        return lists.get(lists.size() - 1);
		    }
		1.3	Longest Valid Parentheses
			/*
				Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
				For "(()", the longest valid parentheses substring is "()", which has length = 2.Another example is ")()())", 
				where the longest valid parentheses substring is "()()", which has length = 4.
			*/
			public class Solution {
			    public int longestValidParentheses(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;  
			        }
			        Stack<Integer> stack = new Stack<Integer>();
			        int start = -1;
			        int maxLen = 0;
			    
			        for (int i = 0; i < s.length(); i++) {
			            if (s.charAt(i) == '(') {
			                stack.push(i);
			            } else {
			                if (stack.isEmpty()) {
			                    start = i;
			                } else {
			                    stack.pop();
			                    maxLen = stack.isEmpty() ? Math.max(maxLen, i - start) : Math.max(maxLen, i - stack.peek());
			                }
			            }
			        }
			        return maxLen;
			    }
			}
		1.4 Balance Parentheses
			public class Solution  {
				public String balance(String s) {
					//use stack to store the position information of parentheses
					//at last , if the stack is not empty, 
					//that's mean these parentheses need to delete from tht orginial string
					Stack<Integer> stack = new Stack<>();
					StringBuilder sb = new StringBuilder(s);
					
					for (int i=0; i< s.length(); i++) {
						int c = s.charAt(i);
						//when the stack is empty and c equals '(', stack push the index of String
						if (stack.isEmpty() || c == '(') {
							stack.push(i);
						} else {
							int top = stack.peek();
							if (s.charAt(top) == ')') {
								stack.push(i);
							} else {
								stack.pop();
							}
						}
					}
					
					while (!stack.isEmpty()) {
						sb.deleteCharAt(stack.pop());
					}
					
					return sb.toString();
				}
			}
		1.5 Remove Invalid Parentheses
			/*
				"()())()" -> ["()()()", "(())()"]
				"(a)())()" -> ["(a)()()", "(a())()"]
				")(" -> [""]
			*/
			public class Solution {
			    public List<String> removeInvalidParentheses(String s) {
			        List<String> res = new ArrayList<>();
			        if (s == null) {
			            return res;
			        }
			        Set<String> visited = new HashSet<>();
			        Queue<String> queue = new LinkedList<>();
			        queue.add(s);
			        visited.add(s);
			        boolean found = false;
			        while (!queue.isEmpty()) {
			            s = queue.poll();
			            
			            if (isValid(s)) {
			                res.add(s);
			                found = true;
			            }
			            //一旦找到了有效的s，一定是最大长度的，因此只需要判断在同一层入队列的s，不需要再往下删除生成子字符串
			            if (found) {
			                continue;
			            }
			            //遍历字符串所有位置，删除'('或者')'之后压入队列
			            for (int i = 0; i < s.length(); i++) {
			                if (s.charAt(i) != '(' && s.charAt(i) != ')') {
			                    continue;
			                }
			                String subStr = s.substring(0, i) + s.substring(i + 1);
			                if (!visited.contains(subStr)) {
			                    queue.add(subStr);
			                    visited.add(subStr);
			                }
			            }
			        }
			        return res;
			    }
			    public boolean isValid(String s) {
			        int count = 0;
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (c == '(') {
			                count++;
			            } else if (c == ')' && count-- == 0) {
			                return false;
			            }
			        }
			        return count == 0;
			    }
			}





2. Math
		2.1	Basic Calculator
			/*
			    Principle:
			        (Sign before '+'/'-') = (This context sign);
			        (Sign after '+'/'-') = (This context sign) * (1 or -1);
			    Algorithm:
			        Start from +1 sign and scan s from left to right;
			        if c == digit: This number = Last digit * 10 + This digit;
			        if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
			        if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
			        if c == '(': Push this context sign to stack;
			        if c == ')': Pop this context and we come back to last context;
			        Add the last num. This is because we only add number after '+' / '-'.
			*/
			public class Solution {
			    public int calculate(String s) {
			        if (s == null) {
			            return 0;
			        }
			        int res = 0;
			        int sign = 1;
			        int num = 0;
			        Stack<Integer> stack = new Stack<Integer>();
			        stack.push(sign);
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (c >= '0' && c <= '9') {
			                num = num * 10 + (c - '0');
			            } else if (c == '+' || c == '-') {
			                res += sign * num;
			                sign = stack.peek() * (c == '+' ? 1 : -1);
			                num = 0;
			            } else if (c == '(') {
			                stack.push(sign);
			            } else if (c == ')') {
			                stack.pop();
			            }
			        }
			        res += sign * num;
			        return res;
			    }
			}
		2.2 Basic Calculator II
    		/*
		    	Solution: 算法逻辑
		             1.运用stack， 遍历String上的所有character，如果遇到数字字符um = num * 10 + s.charAt(i) - '0'，为什么要这样，当遇见多位的数时，需要这样处理
		             2.接下来如果遇到以下两种情况就对栈进行进栈操作
		                1）  如果该字符非数字，且该字符不为空，根据sign，我们注意这个时候的sign是前次运算的sign，根据上次的sign来决定对前个num是采用何种操作。
		                     之后将sign赋值为当前字符，num设为0
		                2)   如果i遍历到字符串尾部。
		            3. 将stack的所有项相加，就是ressult
            */
			public class Solution {
			    public int calculate(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        Stack<Integer> stack = new Stack<Integer>();
			        int num = 0;
			        char sign = '+';
			        for (int i = 0; i < s.length(); i++) {
			            if (Character.isDigit(s.charAt(i))) {
			                num = num * 10 + s.charAt(i) - '0';
			            }
			            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length() - 1) {
			                if (sign == '-') {
			                    stack.push(-num);
			                }
			                if (sign == '+') {
			                    stack.push(num);
			                }
			                if (sign == '*') {
			                    stack.push(stack.pop() * num);
			                }
			                if (sign == '/') {
			                    stack.push(stack.pop() / num);
			                }
			                sign = s.charAt(i);
			                num = 0;
			            }
			        }
			        int res = 0;
			        for (int i : stack) {
			            res += i;
			        }
			        return res;
			    }
			}




