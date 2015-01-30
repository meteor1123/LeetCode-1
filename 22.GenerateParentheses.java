/*
	Generate Parentheses
	Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	For example, given n = 3, a solution set is:
		"((()))", "(()())", "(())()", "()(())", "()()()"
	Tags: Backtracking, String
*/



public class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<String>();
        if (n <= 0)
            return res;
        dfs(n, n, "", res);
        return res;
    }
    public void dfs(int left, int right, String item, ArrayList<String> res){
        //终止的返回条件，left 和right都等于0，说明已经放置n个(括号 和 n个)括号
        if (left == 0 && right == 0) {
            res.add(item);
            return;
        }
        //递归状态，如果left > 0 则不断递归放入(符号
        if (left > 0)
            dfs(left - 1, right, item + '(', res);
        //因为只有当左括号数量大于右括号的数量时才能放入右括号
        if (left < right)
            dfs(left, right - 1, item + ')', res);
    }
}