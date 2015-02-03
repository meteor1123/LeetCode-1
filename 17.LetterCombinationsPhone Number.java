/*
	Letter Combinations of a Phone Number
	Given a digit string, return all possible letter combinations that the number could represent.
	A mapping of digit to letters (just like on the telephone buttons) is given below.

	Input:Digit string "23"
	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

	Note:
	Although the above answer is in lexicographical order, your answer could be in any order you want.

	Tags: Backtracking， String
*/


public class Solution {
	public List<String> letterCombinations(String digits) {
    	ArrayList<String> res = new ArrayList<String>();
    	if (digits == null)
    		return res;
    	String[] keyboard = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    	StringBuilder item = new StringBuilder();
    	int index = 0;
    	backtracking_helper(digits, index, item, keyboard, res);
    	return res;
    }

    private void backtracking_helper(String digits, int index, StringBuilder item, String[] keyboard, ArrayList<String> res) {
    	if (index == digits.length()) {
    		res.add(item.toString());
    		return ;
    	}

    	int num = digits.charAt(index) - '0';//取出字符串里的第index位数字
    	for (int i = 0; i < keyboard[num].length(); i++) { //在keyboard字符串数组里，23 的2 就代表在keyboard[2]里的字符
    		item.append(keyboard[num].charAt(i));
    		backtracking_helper(digits, index + 1, item, keyboard, res);
    		item.deleteCharAt(item.length() - 1);
    	}

    }
}