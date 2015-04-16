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
	//Recursive
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		if (num.length == 0 || num == null)
			return res;
		//为了不重复，visited数组是关键
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
			/* 
				if the num[i] equals num[i - 1] ,and the visited[i - 1] is true,
    			mean that we meet the duplicate situation,
    			so we jump to this for loop, and to next number

    			下面这句if判断是 假如数组里有重复元素的 permutationII 的情况
				if (i > 0 && num[i - 1] == num[i] && !visited[i - 1]) {
                	continue;
            	}
            */
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

	//iteration
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        List<List<Integer>> preList = null;
        List<Integer> subList = new LinkedList<Integer>();
        if(num.length == 0) return wrapList;
        subList.add(num[0]);
        wrapList.add(subList);
        preList = wrapList;
        for(int i=1; i<num.length; i++) {
            wrapList = new LinkedList<List<Integer>>();
            for(int j=0; j<preList.size(); j++) {
                for(int k=0; k<=preList.get(j).size(); k++) {
                    subList = new LinkedList<Integer>(preList.get(j));
                    subList.add(k, num[i]);
                    wrapList.add(subList);
                }
            }
            preList = wrapList;
        }
        return wrapList;
    }
}