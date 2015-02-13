/*
	Search a 2D Matrix 
	Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

	Integers in each row are sorted from left to right.
	The first integer of each row is greater than the last integer of the previous row.
	For example,

	Consider the following matrix:

	[
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]
	Given target = 3, return true.
	Tag: Array, Binary Search
*/

/*
虽然本题看似是矩阵问题，但是本着搜索题目关键字为第一步的原则，可以找到：each row are sorted，每一行按照顺序也是sorted。同时也是数组保存。
但是本题的难点就是如何将2D矩阵转换成1D，然后利用二分查找法来解决问题。
转换的重点就在于每个点的位置，在矩阵表示中，我们习惯用（i，j）来表示一个点，所以这就有碍于我们使用low high mid来指向需要的位置。
为了解决问题，第一步就是需要将这个矩阵按照顺序拉成一条线。

position: 0   1   2   3   4   5   6   7   8   9    10   11   

values:   1   3   5   7   10  11  16  20  23  30   34   50

row:      0   0   0   0   1   1   1   1   2   2    2    2

column:   0   1   2   3   0   1   2   3   0   1    2    3 
*/

public class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int low = 0; 
		int high = row * cols - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (matrix[mid/cols][mid%cols] == target)
				return true;
			else if (matrix[mid/cols][mid%cols] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return false;
	}
}