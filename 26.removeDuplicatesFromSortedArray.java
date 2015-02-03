/*
	Remove Duplicates from Sorted Array 
	Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
	Do not allocate extra space for another array, you must do this in place with constant memory.

	For example,	
		Given input array A = [1,1,2],
	Your function should return length = 2, and A is now [1,2].
*/

public class Solution {
	public int removeDuplicates(int[] A) {
        int index = 1;
        if (A.length <= 1)
            return A.length;
        //只要A[i]和A[i - 1]不同，就可以将A[i]赋值给A[index],index记录着数组里不同长度的个数
        for (int i = 1; i < A.length; i++){
            if (A[i] != A[i-1]){
                A[index] = A[i];
                index ++;
            }
        }
        return index;
	}
}