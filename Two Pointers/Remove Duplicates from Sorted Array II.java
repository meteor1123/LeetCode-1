/*
	Remove Duplicates from Sorted Array II 
	Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?

	For example,
	Given sorted array A = [1,1,1,2,2,3],

	Your function should return length = 5, and A is now [1,1,2,2,3].
	Tags: Array, Two Pointers
*/

public class Solution {
    //1 StefanPochmann
    public int removeDuplicates(int[] A) {
        int index = 0;
        for (int num : nums) {
            if (index < 2 || num > nums[index - 2]) {
                nums[index++] = num;
            }
        }
        return index;
    }

    //2
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
        	return 0;
        }
        //index 记录满足条件的数组长度
        int index = 0;
        //count 记录相等的数的个数
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (i > 0 && A[i] == A[i - 1]) {
                count++;
                if (count >= 3) {
                    continue;
                }
            } else {
                count = 1;
            }

            
            A[index++] = A[i];
        }
        return index;
    }
}