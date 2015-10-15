/*
	The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
	Given an integer n, return all distinct solutions to the n-queens puzzle.
	Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
	For example,	There exist two distinct solutions to the 4-queens puzzle:

		[
		 [".Q..",  // Solution 1
		  "...Q",
		  "Q...",
		  "..Q."],

		 ["..Q.",  // Solution 2
		  "Q...",
		  "...Q",
		  ".Q.."]
		]
*/


public class Solution {
    public ArrayList<String[]> solveNQueens(int n) {
        	ArrayList<String[]> res = new ArrayList<String[]>();
        	if (n < 0)
        		return res;
        	int[] columnVal = new int[n];
        	dfs(n, res, 0, columnVal);
       		return res;
    }

    public void dfs(int nQueens, ArrayList<String[]> res, int row, int[] columnVal) {
    	if (row == nQueens) {
    		String[] unit = new String[nQueens];
    		for (int i = 0; i < nQueens; i++) {
    			StringBuilder sb = new StringBuilder();
    			for (int j = 0; j < nQueens; j++) {
    				if (j == columnVal[i])
    					sb.append("Q");
    				else 
    					sb.append(".");
    			}
    			unit[i] =sb.toString();
    		}
    		res.add(unit);
    		return ;
    	}

    	for (int i = 0; i < nQueens; i++) {
    		columnVal[row] = i;
    		if (isValid(row, columnVal)) {
    			dfs(nQueens, res, row + 1, columnVal);
    		}
    	} 
    }


    public boolean isValid(int row, int[] columnVal) {
    	for (int i = 0; i < row; i++) {
    		if (columnVal[row] == columnVal[i] || Math.abs(columnVal[row] -columnVal[i]) == row - i)
    			return false;
    	}
    	return true;
    }
}


//Solution2
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }
        dfs(n, res, 0, 0, board);
        return res;
    }
    
    public void dfs(int nQueens, List<List<String>> res, int row, int col, char[][] board) {
        if (col == nQueens) {
            List<String> item = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                item.add(new String(board[i]));
            }
            res.add(item);
        }
        
        for (int i = row; i < nQueens; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                dfs(nQueens, res, row, col + 1, board);
                board[i][col] = '.';
            }
        }
    }
    
    public boolean isValid(char[][] board, int row, int col) {
        if(col>= board.length)
            return false;
        for(int i = 0;i < board.length; i++){
            for(int j = 0;j < col; j++){
                if(board[i][j] == 'Q'&& (i == row || row + col == i + j || row - col == i - j)) //Return false if a quuen is already present in another row for same col, or in any diagnal passing through board[row][col]
                    return false;
            }
        }
        return true;
    }

    //Solution3 no row 
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(n, 0, board, res);
        return res;
        
    }
    
    public void dfs(int n, int col, char[][] board, List<List<String>> res) {
        if (col == n) {
            List<String> item = new ArrayList<>();
            for (char[] rowRes : board) {
                item.add(new String(rowRes));
            }
            res.add(item);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (isValid(board, i, col)) {
                 board[i][col] = 'Q';
                 dfs(n, col + 1, board, res);
                 board[i][col] = '.';
            }
        }
    }
    
    public boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'Q' && (i == row || j == col || Math.abs(row - i) == Math.abs(col - j))) {
                    return false;
                }
            }
        }
        return true;
    }
}