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
    //1
    public int removeDuplicates(int[] A) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }
    //2
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
        	return 0;
        }
        //index 指示当前的满足条件的 长度
        int index = 0;
        //记录相等的数的个数
        int count = 0;
        for (int i = 0; i < A.length; i++) {
        	if (i > 0 && A[i] == A[i - 1]) {
        		//只要i>0 并且A[i] 和前一个 A[i - 1]相等，就加加
        		count++;
        		//重复数大于3，跳出，进行下一次计数
        		if (count >= 3) {
        			continue;
        		}
        	} else {
        		//长度加加，假如之前有超过等于3的 就将新的不重复超过3的i赋值回去
        		A[index++] = A[i];
        	}
        }
        return index;
    }
}