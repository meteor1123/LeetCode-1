/*
	Game of Life
	According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

	Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

	Any live cell with fewer than two live neighbors dies, as if caused by under-population.
	Any live cell with two or three live neighbors lives on to the next generation.
	Any live cell with more than three live neighbors dies, as if by over-population..
	Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

	Write a function to compute the next state (after one update) of the board given its current state.

	Follow up: 
	Could you solve it in-place? Remember that the board needs to be updated at the same time: 
	You cannot update some cells first and then use their updated values to update other cells.
	In this question, we represent the board using a 2D array. 
	In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
	How would you address these problems?

*/

/*
	
*/

//Solution1
public class Solution {
	int[][] dir ={{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int live = 0;
                for (int[] d : dir) {
                    if (d[0] + i < 0 || d[0] + i >= board.length || d[1] + j < 0 || d[1] + j >= board[0].length) {
                        continue;
                    }
                    if (board[d[0] + i][d[1] + j] == 1 || board[d[0] + i][d[1] + j] == 2) {
                        live++;
                    }
                }
                if (board[i][j] == 0 && live == 3) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 1 && (live < 2 || live > 3)) {
                    board[i][j] = 2;
                }
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] %= 2;
            }
        }
    }
}

//Solution2
public class Solution {
	public void gameOfLife(int[][] board) {
        if (board.length == 0 || board == null) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int live = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (!check(board, x, y) || (x == i && y == j)) {
                            continue;
                        }
                        if ((board[x][y] == 1 || board[x][y] == 2)) {
                            live++;
                        }
                    }
                }
                if (board[i][j] == 0 && live == 3) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 1 && (live < 2 || live > 3)) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] %= 2;
            }
        }
    }
    public boolean check(int[][] board, int x, int y) {
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1) {
            return false;
        } 
        return true;
    }

//Solution3
public class Solution {
        public void gameOfLife(int[][] board) {
        if (board.length == 0 || board == null) {
            return;
        }
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[i][j] = board[i][j];
                setLife(board, i, j, newBoard);
               
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }
    public void setLife(int[][] board, int i, int j, int[][] newBoard) {
         int live = 0;
         for (int x = i - 1; x <= i + 1; x++) {
             for (int y = j - 1; y <= j + 1; y++) {
                 if (!check(board, x, y) || (x == i && y == j)) {
                     continue;
                 }
                 if (board[x][y] == 1) {
                     live++;
                 }
             }
         }
         if (board[i][j] == 0 && live == 3) {
             newBoard[i][j] = 1;
         }
         if (board[i][j] == 1 && (live < 2 || live > 3)) {
             newBoard[i][j] = 0;
         }
    }
    
    public boolean check(int[][] board, int x, int y) {
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1) {
            return false;
        } 
        return true;
    }
}