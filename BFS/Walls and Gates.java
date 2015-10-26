/*
	Walls and Gates
	You are given a m x n 2D grid initialized with these three possible values.

	-1 - A wall or an obstacle.
	0 - A gate.
	INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
	Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

	For example, given the 2D grid:
	INF  -1  0  INF
	INF INF INF  -1
	INF  -1 INF  -1
	  0  -1 INF INF
	After running your function, the 2D grid should be:
	  3  -1   0   1
	  2   2   1  -1
	  1  -1   2  -1
	  0  -1   3   4
*/

/*
	Solution: 只需要进行一次bfs即可，因为每次会更新的结点的层数必定是最短的距离！
*/
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        int INF = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] point = queue.remove();
            int row = point[0];
            int col = point[1];
            if (row > 0 && rooms[row - 1][col] == INF) {
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row - 1, col});
            }
            
            if (row < m - 1 && rooms[row + 1][col] == INF) {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            
            if (col > 0 && rooms[row][col - 1] == INF) {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            
            if (col < n - 1 && rooms[row][col + 1] == INF) {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
        }
       
    }
}

//Solution2
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        int INF = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * n + j);
                }
            }
        }
        while (!queue.isEmpty()) {
            int code = queue.poll();
            int x = code / n;
            int y = code % n;
            if (isValid(rooms, x + 1, y) && rooms[x + 1][y] == INF) {
                rooms[x + 1][y] = rooms[x][y] + 1;
                queue.offer((x + 1) * n + y);
            }
            if (isValid(rooms, x - 1, y) && rooms[x - 1][y] == INF) {
                rooms[x - 1][y] = rooms[x][y] + 1;
                queue.offer((x - 1) * n + y);
            }
            if (isValid(rooms, x, y + 1) && rooms[x][y + 1] == INF) {
                rooms[x][y + 1] = rooms[x][y] + 1;
                queue.offer(x * n + y + 1);
            }
            if (isValid(rooms, x, y - 1) && rooms[x][y - 1] == INF) {
                rooms[x][y - 1] = rooms[x][y] + 1;
                queue.offer(x * n + y - 1);
            }
        }
    }
    
    public boolean isValid(int[][] rooms, int i, int j) {
        if (i < 0 || j < 0 || i > rooms.length - 1 || j > rooms[0].length - 1) {
            return false;
        }
        return true;
    }
}