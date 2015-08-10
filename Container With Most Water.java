/*
	Container With Most Water
	Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
	which together with x-axis forms a container, such that the container contains the most water.
	Note: You may not slant the container.
*/


/*
    Here is the proof. Proved by contradiction:

    Suppose the returned result is not the optimal solution. Then there must exist an optimal solution, 
    say a container with aol and aor (left and right respectively), such that it has a greater volume than the one we got. 
    Since our algorithm stops only if the two pointers meet. So, we must have visited one of them but not the other. 
    WLOG, let's say we visited aol but not aor. When a pointer stops at a_ol, it won't move until

    The other pointer also points to aol. In this case, iteration ends. 
    But the other pointer must have visited aor on its way from right end to aol. 
    Contradiction to our assumption that we didn't visit aor.

    The other pointer arrives at a value, say arr, that is greater than aol before it reaches aor. 
    In this case, we does move aol. But notice that the volume of aol and arr is already greater than aol and aor (as it is wider and heigher), 
    which means that aol and aor is not the optimal solution -- Contradiction!
*/
public class Solution {

    //the input is Array
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

    //the input is list
    public int maxArea(List<Integer> height) {
        if (height.size() == 0)
            return 0;
        if (height.size() == 1)
            return 0;
        int left = 0;
        int right = height.size() - 1;
        //initialize the maximum value,which equals the minimum
        int max = Math.min(height.get(left), height.get(right)) * Math.abs(left - right);
        while (left < right) {
            max = Math.max(Math.min(height.get(left), height.get(right)) * Math.abs(left - right), max);
            if (height.get(right) > height.get(left)){
                left = left + 1;
            }
            else {
                right = right - 1;
            }
        }
        return max;
    }
}
}