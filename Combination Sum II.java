/*
	Combination Sum II 


	Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

	Each number in C may only be used once in the combination.

	Note:
	All numbers (including target) will be positive integers.
	Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
	The solution set must not contain duplicate combinations.
	For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
	A solution set is: 
	[1, 7] 
	[1, 2, 5] 
	[2, 6] 
	[1, 1, 6]

	和I的区别在于 combinations1数组里的数可以无限的试探使用
*/


  //   public List<List<Integer>> combinationSum1(int[] candidates, int target) {
  //       ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integr>>();
  //       ArrayList<Integer> item = new ArrayList<Integer>();

  //       if （candidates.length == 0 || candidates == null)
		// 	return res;
		// Arrays.sort(candidates);
		// dfs(candidates, target, 0, item, res);
  //   }

  //   public void dfs(int[] candidates, int target, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
  //   	if (target < 0)
  //   		return ;
  //   	if (target == 0) {
  //   		res.add(new ArrayList<Integer>(item));
  //   		return;
  //   	}

  //   	for (int i = start; i < candidates.length; i++) {
  //   		if (i > 0 && candidates[i] == candidates[i - 1])
  //   			continue;
  //   		item.add(candidates[i]);
  //   		int newTarget = target - candidates[i];
  //   		dfs(candidates, newTarget, i, item, res);
  //   		//之所以不传i+1，是因为The same repeated number may be chosen from C
		// 	//unlimited number of times. num[i]可以任意使用n多次,因此在这里用newTarget 来控制递归
  //   		item.remove(item.size() - 1);
  //   	}
  //   }


public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];
        if (num.length == 0 || num == null)
        	return res;
        Arrays.sort(num);
        dfs(num, target, 0, visited, item, res);
        return res;
    }

    public void dfs(int[] num, int target, int start,boolean[] visited, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
        if (target < 0)
            return ;
        if (target == 0) {
            res.add(new ArrayList<Integer>(item));
            return ;
        }
        
        for (int i = start; i < num.length; i++) {
            if (!visited[i]) {
                if (i > 0 && num[i] == num[i - 1] && visited[i - 1] == false)
                    continue;
            }//if num[i] == num[i -1] and  visited[i - 1] == false, means already use num[i] to recursive the result,
             //

            visited[i] = true;
            item.add(num[i]);
            dfs(num, target - num[i], i + 1, visited, item, res);
            item.remove(item.size() - 1);
            visited[i] = false;
        }
    }
}