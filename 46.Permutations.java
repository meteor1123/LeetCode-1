/*
	Permutations 
	Given a collection of numbers, return all possible permutations.

	For example,
	[1,2,3] have the following permutations:
	[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	Tags:BackTracking.
*/


/*
	solution: key idea using boolean[]  to check the element in num arry,if is visited,dont put into the res
			  and remove 
*/

public class Solution {
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		if (num.length == 0 || num == null)
			return res;
		boolean[] visited = new boolean[num.length];
		backtracking(num, item, res, visited);
		return res;
	}

	public void backtracking(int[] num, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, boolean[] visited) {
		if (item.size() == num.length) {
			res.add(new ArrayList<Integer>(item));
			return ;
		}

		for (int i = 0; i < num.length; i++) {
			if (!visited[i]) {
				item.add(num[i]);
				visited[i] = true;
				backtracking(num, item, res, visited);
				//去掉最后一个元素，回溯
				item.remove(item.size() - 1);
				//元素出列以后，标记为false，可以继续遍历
				visited[i] = false;
			}
		}
	}
}