/*
	Given n non-negative integers representing the histogram's bar height 
	where the width of each bar is 1, 
	find the area of largest rectangle in the histogram.

	Above is a histogram where width of each bar is 1, 
	given height = [2,1,5,6,2,3].

	The largest rectangle is shown in the shaded area, 
	which has area = 10 unit.

	For example,
	Given height = [2,1,5,6,2,3],
	return 10.
*/

public class Solution {
    public int largestRectangleArea(int[] height) {
    	Stack<Integer> stack = new Stack<Integer>();
    	int i = 0 ;
    	int maxArea = 0;
    	int[] h = new int[height.length + 1];//h[height.length]存放的是人为假尾结点0，使其可以终止
    	h = Arrays.copyOf(height, height.length + 1);
    	// 假如是递增序列，则持续进栈，一遇到小于栈顶元素的数就一直出栈到栈顶元素小于等于 h[i],然后持续递增进栈，或者递减出栈重新算
    	while (i < h.length) {
    		if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
    			stack.push(i++);
    		} else {

    			int top = stack.pop();//一遇到小的数就栈顶元素出栈
    			maxArea = Math.max(maxArea, h[top] * (stack.isEmpty() ? i: i - stack.peek() - 1));
                //假如栈顶元素非空，则宽度等于 i - stack.peek() - 1
    		}
    	}
    	return maxArea;
    }
}

