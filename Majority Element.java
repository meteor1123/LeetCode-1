/*
	Majority Element 
	Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

	You may assume that the array is non-empty and the majority element always exist in the array.

	Credits:
	Special thanks to @ts for adding this problem and creating all test cases.
*/


public class Solution {
    public int majorityElement(int[] num) {
        return majorityHelper(num, 0, num.length - 1);
    }
    public int majorityHelper(int[] num, int start, int end) {
        // int n = end - start + 1;
        if (start == end) {
            return num[start];
        }
        int mid = (start + end) / 2;
        int leftMajority = majorityHelper(num, start, mid);
        int rightMajority = majorityHelper(num, mid + 1, end);
        
        int leftCount = 0;
        int rightCount = 0;
        for (int i = start; i <= end; i++) {
            if (num[i] == leftMajority) {
                leftCount++;
            }
            if (num[i] == rightMajority) {
                rightCount++;
            }
        }
        
        if (leftCount > (end - start + 1) / 2) {
            return leftMajority;
        }
        if (rightCount > (end - start + 1) / 2) {
            return rightMajority;
        }
        return 0;
    }
}