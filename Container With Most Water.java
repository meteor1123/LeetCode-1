/*
	Container With Most Water
	Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
	which together with x-axis forms a container, such that the container contains the most water.
	Note: You may not slant the container.
*/

public class Solution {
    public int maxArea(int[] height) {
        if (height.length == 0)
            return 0;
        if (height.length == 1)
            return 0;
        int left = 0;
        int right = height.length - 1;
        int max = Math.min(height[left],height[right]) *Math.abs(left-right);
        while (left < right){
            max = Math.max(Math.min(height[left], height[right]) * Math.abs(left - right), max);
            if (height[right] > height[left]){
                left = left + 1;

            }
            else if (height[right] <= height[left]){
                right = right - 1;

            }
        }
        return max;
    }
}