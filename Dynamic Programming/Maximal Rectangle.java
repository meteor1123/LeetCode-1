/*
	Maximal Rectangle
	Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*/



public class Solution {
    //Solution2:
    public int maximalRectangle(char[][] matrix) {
        //对每一行的每一列高度进行计算，如果列=0，则高度为0
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int max = 0;
            int[] height = new int[n];//每一行与上一行的数字相加，上一行的1+这一行的0等于0，上一行的0加上这一行的1等于1.
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == '0') //
                        height[j] = 0;
                    else 
                        height[j] += 1;
                }
                max = Math.max(largestRectangleArea(height), max);
            }
            return max;
    }
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0; 
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while(i < h.length) {
            if(stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                maxArea = Math.max(maxArea, h[top] * (stack.isEmpty() ? i: i - stack.peek() - 1));
            }
        }
        return maxArea;
    }
}


public class Solution {
    /*
        This solution is so clever that I think so hard to understand it.
        height counts the number of successive '1's above (plus the current one).

        The value of left & right means the boundaries of the rectangle
        which contains the current point with a height of value height.
        

        one pointer, left, to note the starting position of 1s.
        one pointer, right, to note the ending position of 1s.
        one pointer, height, to note the height.
                 0 1 2 3 4 5 6

            0    0 0 0 1 0 0 0
            1    0 0 1 1 1 0 0
            2    0 1 1 1 1 1 0

        0th row: 0 0 0 1 0 0 0

        height:  0 0 0 1 0 0 0
        left:    0 0 0 3 0 0 0
        right    7 7 7 4 7 7 7

        1st row: 0 0 1 1 1 0 0

        height:  0 0 1 2 1 0 0
        left:    0 0 2 3 2 0 0
        right:   7 7 5 4 5 7 7

        2nd row: 0 1 1 1 1 1 0
        height:  0 1 2 3 2 1 0
        left:    0 1 2 3 2 1 0
        right:   7 6 5 4 5 6 7
    */



	//Solution1:
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        
        for (int i = 0; i < right.length; i++) {
            right[i] = n;
        }
        
        int maxA = 0;
        
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
            
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            
            for (int j = 0; j < n; j++) {
                maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);
            }
        }
        return maxA;
    }
}