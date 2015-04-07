/*
	Subsets II
	Given a collection of integers that might contain duplicates, S, return all possible subsets.

	Note:
	Elements in a subset must be in non-descending order.
	The solution set must not contain duplicate subsets.
	For example,
	If S = [1,2,2], a solution is:

	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	]
	Tags: Array, Backtracking
*/

public class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }
        ArrayList<Integer> item = new ArrayList<Integer>();
        res.add(new ArrayList<Integer>());
        Arrays.sort(num);
        boolean[] visited = new boolean[num.length];
        subsetsWithDupHelper(num, 0, item, res, visited);
        return res;
        
    }
    
    public void subsetsWithDupHelper(int[] num, int index, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res, boolean[] visited) {
       
       for (int i = index; i < num.length; i++) {
       		// this if statament is to skip the duplicate value
           if (i > 0 && num[i] == num[i - 1] && visited[i - 1] == false) {
               continue;
           }
           visited[i] = true;
           item.add(num[i]);
           res.add(new ArrayList<Integer>(item));
           subsetsWithDupHelper(num, i + 1, item, res, visited);
           visited[i] = false;
           item.remove(item.size() - 1);
       } 
    }
}