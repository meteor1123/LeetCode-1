/*
	Smallest Rectangle Enclosing Black Pixels
	An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

	For example, given the following image:

	[
	  "0010",
	  "0110",
	  "0100"
	]
	and x = 0, y = 2,
	Return 6.
*/

/*
	Suppose we have a 2D array

	"000000111000000"
	"000000101000000"
	"000000101100000"
	"000001100100000"
	Imagine we project the 2D array to the bottom axis with the rule "if a column has any black pixel it's projection is black otherwise white". 
	The projected 1D array is

	"000001111100000"
	Theorem
		If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.
	Proof by contradiction
		Assume to the contrary that there are disconnected black pixels at i and j where i < j in the 1D projection array. 
		Thus there exists one column k, k in (i, j) and and the column k in the 2D array has no black pixel. 
		Therefore in the 2D array there exists at least 2 black pixel regions separated by column k which contradicting the condition of "only one black pixel region".
		Therefore we conclude that all the black pixels in the 1D projection array is connected.
		This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.

	To find the left boundary, do the binary search in the [0,x) range and find the first column vector who has any black pixel.

	To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)

	We can do the same for the other boundaries. The area is then calculated by the boundaries. Thus the algorithm runs in O(m * log n + n * log m)
*/
//Solution1 Binary Search
	public class Solution {
	private char[][] image;
    public int minArea(char[][] iImage, int x, int y) {
        image = iImage;
        int m = image.length, n = image[0].length;
        int top = search(0, x, 0, n, true, true);
        int bottom = search(x + 1, m, 0, n, false, true);
        int left = search(0, y, top, bottom, true, false);
        int right = search(y + 1, n, top, bottom, false, false);
        return (right - left) * (bottom - top);
    }
    private boolean isWhite(int mid, int k, boolean isRow) {
        return ((isRow) ? image[mid][k] : image[k][mid]) == '0';
    }
    private int search(int i, int j, int low, int high, boolean opt, boolean isRow) {
        while (i != j) {
            int k = low, mid = (i + j) / 2;
            while (k < high && isWhite(mid, k, isRow)) ++k;
            if (k < high == opt)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }
}
//Solution2 DFS
public class Solution {
    private int minX = Integer.MAX_VALUE;
    private int maxX = 0;
    private int minY = Integer.MAX_VALUE;
    private int maxY = 0;
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        dfs(image, x, y);
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
    
    public void dfs(char[][] image, int x, int y) {
        if (x < 0 || x > image.length - 1 || y < 0 || y > image[0].length - 1 || image[x][y] == '0') {
            return;
        }
        image[x][y] = '0';
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
        dfs(image, x, y + 1);
        dfs(image, x, y - 1);
    }
}