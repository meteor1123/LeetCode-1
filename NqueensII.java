public class Solution {
	int res = 0;
    public int totalNQueens(int n) { 
    	if (n < 0);
    		return res;
    	int[] columnVal = new int[n];
    	dfs(columnVal, 0, res);
    	return res;
    }

   public void dfs(int[] columnVal, int cur, int n) {
   		if (cur == n) {
   			res += 1;
   			return ;
   		}
   		for (int i = 0; i < n; i++) {
   			columnVal[cur] = i;
   			if (isValid(cur, columnVal)) {
   				dfs(columnVal, cur + 1, n);
   			}
   		}

   }

    public boolean isValid(int cur, int[] columnVal) {
    	for (int i = 0; i < cur; i++) {
    		if (columnVal[i] == columnVal[cur] || Math.abs(columnVal[cur]-columnVal[i]) == (cur - i))
    			return false;
    	}
    	return true;
    }
}

