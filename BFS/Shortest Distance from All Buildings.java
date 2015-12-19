/*
	Shortest Distance from All Buildings
	You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You are given a 2D grid of values 0, 1 or 2, where:

	Each 0 marks an empty land which you can pass by freely.
	Each 1 marks a building which you cannot pass through.
	Each 2 marks an obstacle which you cannot pass through.
	The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

	For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

	1 - 0 - 2 - 0 - 1
	|   |   |   |   |
	0 - 0 - 0 - 0 - 0
	|   |   |   |   |
	0 - 0 - 1 - 0 - 0
	The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

	Note:
	There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/*
	Solution: 1. 设两个数组 record1 用来记录这个坐标已经被多少个坐标值为1进行了多少次bfs
						  record2 用来记录这个坐标对已经遍历过的坐标值为1的点的距离和，因为bfs 所以一定是这个点到所有1点的最近距离

			  2. 对所有坐标值为1的点进行bfs，记录所有0点对这个点的距离.
			  3. 遍历所有坐标值为0的点的record1 和record2值，找record1等于所有1的数量，并且record2的值

*/
public class Solution {
    public int shortestDistance(int[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return -1;
        }
        int col = grid[0].length;
        int[][] record1 = new int[row][col];// visited num用来记录 所有坐标值为1的点对这个坐标进行了多少次bfs
        int[][] record2 = new int[row][col];// distance
        
        int numOfOne = 0;
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
            	//遍历每一个1值点
                if (grid[i][j] == 1) {
                    numOfOne++;
                    boolean[][] visited = new boolean[grid.length][grid[0].length];
                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.offer(new int[] {i, j});
                    int dist = 0;
                    //对所有的点进行bfs，计算和上面这个1点的距离
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] node = queue.poll();
                            int x = node[0];
                            int y = node[1];
                            record1[x][y] ++;
                            record2[x][y] += dist;
                            
                            if (x > 0 && grid[x - 1][y] == 0 && !visited[x - 1][y]) {
                                queue.offer(new int[]{x - 1, y});
                                visited[x - 1][y] = true;
                            }
                            if (x + 1 < grid.length && grid[x + 1][y] == 0 && !visited[x + 1][y]) {
                                queue.offer(new int[]{x + 1, y});
                                visited[x + 1][y] = true;
                            }
                            if (y > 0 && grid[x][y - 1] == 0 && !visited[x][y - 1]) {
                                queue.offer(new int[]{x, y - 1});
                                visited[x][y - 1] = true;
                            }
                            if (y + 1 < grid[0].length && grid[x][y + 1] == 0 && !visited[x][y + 1]) {
                                queue.offer(new int[]{x, y + 1});
                                visited[x][y + 1] = true;
                            }
                        }
                        dist++;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && record1[i][j] == numOfOne && record2[i][j] < res) {
                    res = record2[i][j];
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

