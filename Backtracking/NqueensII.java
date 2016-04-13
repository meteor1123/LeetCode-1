/*
  Follow up for N-Queens problem.

  Now, instead outputting board configurations, return the total number of distinct solutions.
*/
public class Solution {
        int res = 0;
    public int totalNQueens(int n) {
        if (n <= 0) {
            return res;
        }
        //use one dimension array to store the  position information
        // columnVal[n] mean  in the position the row n, and the columnVal[n] column put a queens
        int[] columnVal = new int[n];
        totalNQueensHelper(0, columnVal, n);
        return res;
    }
    
    public void totalNQueensHelper(int curRow, int[] columnVal, int n) {
        if (curRow == n) {
            res++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
          //只要curRow
            columnVal[curRow] = i; 
            if (isValid(curRow, columnVal)) {
                totalNQueensHelper(curRow + 1, columnVal, n);
            } 
        }
    }
    
    
    public boolean isValid(int curRow, int[] columnVal) {
        for (int i = 0; i < curRow; i++) {
            if (columnVal[curRow] == columnVal[i] || Math.abs(columnVal[curRow] - columnVal[i]) == (curRow - i)) {
                return false;
            }
        }
        return true;
    }
}


//Solution2 
public class Solution {
    int count;
    public int totalNQueens(int n) {
        if (n <= 1) {
            return n;
        }
        count = 0;
        int[] arr = new int[n];
        dfs(arr, 0, n);
        return count;
    }
    
    public void dfs(int[] arr, int row, int n) {
        if (row == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[row] = i;
            if (isValid(arr, row)) {
                dfs(arr, row + 1, n);
            }
        }
    }
    
    public boolean isValid(int[] arr, int row) {
        for (int i = 0; i < row; i++) {
            if (arr[row] == arr[i] || Math.abs(arr[row] - arr[i]) == (row - i)) {
                return false;
            }
        }
        return true;
    }
}
