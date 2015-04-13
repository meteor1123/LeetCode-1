/*
	Permutations II
	Given a collection of numbers that might contain duplicates, return all possible unique permutations.

	For example,
	[1,1,2] have the following unique permutations:
	[1,1,2], [1,2,1], and [2,1,1].
*/


public class Solution {
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }
        Arrays.sort(num);
        boolean[] visited = new boolean[num.length];
        ArrayList<Integer> item = new ArrayList<Integer>();
        dfs(num, res, item, visited);
        return res;
    }
    public void dfs(int[] num, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item, boolean[] visited) {
        if (item.size() == num.length) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        
        for (int i = 0; i < num.length; i++) {
            //if the num[i] equals num[i - 1] ,and the visited[i - 1] is true,
    		// mean that we meet the duplicate situation, so we jump to this for loop, and to next number
            if (i > 0 && num[i - 1] == num[i] && !visited[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                item.add(num[i]);
                visited[i] = true;
                dfs(num, res, item, visited);
                item.remove(item.size() - 1);
                visited[i] = false;
            }
            
        }
    }
}