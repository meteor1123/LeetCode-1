/*
	Maximal Rectangle
	Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
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