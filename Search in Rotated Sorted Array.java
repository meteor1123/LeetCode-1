/*
	Search in Rotated Sorted Array 
	Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	You are given a target value to search. If found in the array return its index, otherwise return -1.
	You may assume no duplicate exists in the array.
*/


/*
	Solution:

	分三种情况：
	1.   顺序排列，没有rotated，1 2 3 4 5 6 7 判断start target 和mid的大小

	2.	 rotated了，旋转的数比旋转之后的数多，也就是mid在rotated的数之中 
		 7 6 5 4 3 1 2   

		 rotated了，但是旋转的数比正常的少，mid在正常数之中
		 7 6 1 2 3 4 5   判断 mid  target 和 end 的大小

		 注意2中的2中状况可以直接一次判断和else 筛选出来

*/
public class Solution {
	//solution 1
	public int search(int[] A, int target) {
		int start = 0;
		int end = A.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == target)
				return mid;
			if (A[start] < A[mid]) {
				if (A[start] <= target && target <= A[mid])
					end = mid - 1;
				else 
					start = mid + 1;
			}
			else {
				if (A[mid] <= target && target <= A[end])
					start = mid + 1;
				else 
					end = mid - 1;
			}
		}
	}

	//solution 2
	public int search(int[] A, int target) {
		int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //denote we find the target
            if (A[mid] == target) {
                return mid;
            }
            //A[start] < A[mid] means the array doesn't rotated, just search like the normal array
            if (A[start] < A[mid]) {
            	//target in the front of mid
                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                //target in the back of mid 
                } else {
                    start = mid;
                }
            //this situation means the array had already rotated
            } else {
            	//so we need to check the target, if target large than A[mid] and less than A[end]
                if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target) 
            return start;
        if (A[end] == target) 
            return end;
        return -1;
	}

}