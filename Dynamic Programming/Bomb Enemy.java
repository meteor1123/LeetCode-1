/*
	361. Bomb Enemy

	Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
	The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
	Note that you can only put the bomb at an empty cell.

	Example:
	For the given grid

	0 E 0 0
	E 0 W E
	0 E 0 0

	return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/


// Solution1: brute force my solution
class Solution {
    int res = 0;
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
     
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0')
                    helper(grid, i, j, m, n);
            }
        }
        return res;
    }
    
    public void helper(char[][] grid, int x, int y, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 'W') {
            return;
        }
        int count = 0;
        for (int i = x + 1; i < m; i++) {
            if (grid[i][y] == 'E')
                count++;
            else if (grid[i][y] == 'W')
                break;
        }
        
        for (int i = x - 1; i >= 0; i--) {
            if (grid[i][y] == 'E')
                count++;
            else if (grid[i][y] == 'W')
                break;
        }
        
        for (int j = y + 1; j < n; j++) {
            if (grid[x][j] == 'E')
                count++;
            else if (grid[x][j] == 'W')
                break;
        }
        
        for (int j = y - 1; j >= 0; j--) {
            if (grid[x][j] == 'E')
                count++;
            else if (grid[x][j] == 'W')
                break;
        }
        res = Math.max(res, count);
    }
}

// Solution2: DP
