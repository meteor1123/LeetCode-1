/*
	Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
	A region is captured by flipping all 'O's into 'X's in that surrounded region.

	For example,	
	X X X X
	X O O X
	X X O X
	X O X X

	After running your function, the board should be:
	X X X X
	X X X X
	X X X X
	X O X X
*/


/*
	Solution:
	这道题是说
	1. 一个O周围都是X那么这个O就得变成X。那么就可以发现四周这一圈如果有O肯定不能四周都被X包围，
	2. 同时这个O也将会是潜在的内部的O的缺口，内部的O不能都被X覆盖。
	3. 因此，思路就是先对四周的O进行特殊处理，用BFS走，把所有这个O连接的O（包括这个O）都涂成#。这样子，对于原来的棋盘来说，
	   没有变成#的O就是四周没有被O污染的，四周都是X，那么对其变成X。而所有#就是那些原来是O但是不是四周都被X包围的，把它们再还原成O。

	关键思路，从四周开始bfs所有的O点 以及和O点连接的O，并标记为#，因此所有没有被标为#的O点就一定是四周被X围绕
*/
public class Solution {
    public void solve(char[][] board) {
    	if (board == null || board.length <= 1 || board[0].length <= 1)
    		return ;

        //check the first and last row whether has 0,by using fill algorithm
    	for (int i = 0 ; i < board[0].length; i++) {
    		fill(board, 0, i);
    		fill(board, board.length - 1, i);
    	}

        //check the first and last column whether has 0,by using fill algorithm
    	for (int i = 0; i < board.length; i++) {
    		fill(board, i, 0);
    		fill(board, i, board[0].length - 1);
    	}


        //check the matrix value, if  equals 'O' that can be set to 1
        // if equals '#' that can not be setted.        
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			if (board[i][j] == 'O')
    				board[i][j] = 'X';
    			else if (board[i][j] == '#')
    				board[i][j] = 'O';
    		}
    	}
    }

    public void fill(char[][] board, int i, int j) {
    	if (board[i][j] != 'O')
    		return ;
    	board[i][j] = '#';
    	LinkedList<Integer> queue = new LinkedList<Integer>();
    	int code = i * board[0].length + j;
    	queue.offer(code);
    	while (!queue.isEmpty()) {
    		code = queue.poll();
    		int row = code / board[0].length;
    		int col = code % board[0].length;

            //从最后一行开始往前行查找
    		if (row > 0 && board[row - 1][col] == 'O') {
    			queue.offer((row - 1) * board[0].length + col);
    			board[row - 1][col] = '#';
    		}

            //从第一行开始往后行查找
    		if (row < board.length - 1 && board[row + 1][col] == 'O') {
    			queue.offer((row + 1) * board[0].length + col);
    			board[row + 1][col] = '#';
    		}

            //从最后一列开始往前列查找
    		if (col > 0 && board[row][col - 1] == 'O') {
    			queue.offer(row * board[0].length + col - 1);
    			board[row][col - 1] = '#';
    		}

            //从第一列开始往后列查找
    		if (col < board[0].length - 1 && board[row][col + 1] == 'O') {
    			queue.offer(row * board[0].length + col + 1);
    			board[row][col + 1] = '#';
    		}
    	}
    }
}