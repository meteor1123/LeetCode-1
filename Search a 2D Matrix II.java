/*
	Search a 2D Matrix II
	Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

	This matrix has the following properties:

    * Integers in each row are sorted from left to right.

    * Integers in each column are sorted from up to bottom.

    * No duplicate integers in each row or column.

    Example
	Consider the following matrix:

	[
    	[1, 3, 5, 7],

    	[2, 4, 7, 8],

    	[3, 5, 9, 10]
	]

	Given target = 3, return 2.

	Challenge
	O(m+n) time and O(1) extra space
*/

/*
	Solution:从数组的
*/
public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        	int rowLen = matrix.length;
	if (rowLen == 0)
		return 0;
	int colLen = matrix[0].length;
	if (colLen == 0)
		return 0;

	int count = 0;
	int i = 0;
	int j = colLen - 1;

	while (i < rowLen && j >= 0) {
		if (matrix[i][j] == target) {
			count++;
			i++;
			j--;
		} else if (matrix[i][j] > target) {
			j--;
		} else {
			i++;
		}
	}
	return count;
}