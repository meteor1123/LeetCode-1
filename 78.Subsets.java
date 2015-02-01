/*
	Subsets
	Given a set of distinct integers, S, return all possible subsets.

	Note:
	Elements in a subset must be in non-descending order.
	The solution set must not contain duplicate subsets.
	For example,
	If S = [1,2,3], a solution is:

	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
*/

/*
	套用combination的方法，其实combination那道题就是在求不同n下的subset，这里其实是要求一个集合罢了。
 例如k=3，n=1，用combination那道题的方法求得集合是[[1], [2], [3]]；
    k=3, n=2, 用combination那道题的方法求得集合是[[1, 2], [1, 3], [2, 3]]
    k=3, n=3, 用combination那道题的方法求得集合是[[1,2,3]]
所以上述3个集合外加一个空集不就是
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
么？
只需要在combination的外面加个循环即可。
*/
public class Solution {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item = new ArrayList<Integer>();
        if (S.length == 0 || S == null)
            return res;
        Arrays.sort(S);
        for (int len = 1 ; len <= S.length; len++ ) //将dfs终止条件len遍历放入dfs中用于判断生成subset
            dfs(S, 0, len, res, item);
        res.add(new ArrayList<Integer>());//最后加入空集
        return res;
    }
    public void dfs (int[] S, int start, int len, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item) {
        //递归终止判断条件是len，
        if (item.size() == len) {
            res.add(new ArrayList<Integer>(item));
            return ;
        }
        
        //遍历S[]数组中的每一位数字,S.length 等于 combination中的n
        for (int i = start; i < S.length; i++) {
            item.add(S[i]);
            dfs(S, i + 1, len, res, item);
            item.remove(item.size() - 1);
        }
    }
}