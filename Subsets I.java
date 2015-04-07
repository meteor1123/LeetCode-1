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

    //concise solution
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (S == null || S.length == 0) {
            return res;
        }
        ArrayList<Integer> item = new ArrayList<Integer>();
        Arrays.sort(S);
        res.add(new ArrayList<Integer>());
        subsetsHelper(0, item, res, S);
        return res;
    }
    
    public void subsetsHelper(int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, int[] S) {
        for (int i = start; i < S.length; i++) {
            //
            item.add(S[i]);
            res.add(new ArrayList<Integer>(item));
            subsetsHelper(i + 1, item, res, S);
            item.remove(item.size() - 1);
        }
    }



  //Solution1: combination 加个循环
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

    //Solution2: from 小莹子
        public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (S == null || S.length == 0)
            return res;
        ArrayList<Integer> item = new ArrayList<Integer>();
        Arrays.sort(S);
        dfs(res, item, S, 0);
        res.add(new ArrayList<Integer>());//空集
        return res;
    }
    
    public void dfs ( ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item, int[] S, int pos) {
        for (int i = pos; i < S.length; i++) {
            item.add(S[i]);
            res.add(new ArrayList<Integer>(item));
            dfs(res, item, S, i + 1);
            item.remove(item.size() -1);
        }
    }

    //Solution: Chapter 9
    public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);  
        subsetsHelper(result, list, num, 0);

        return result;
    }


    private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos) {

        result.add(new ArrayList<Integer>(list));

        for (int i = pos; i < num.length; i++) {
            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
}