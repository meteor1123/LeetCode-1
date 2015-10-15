/*
	Median of Two Sorted Arrays
	There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	Tags: Divide and Conquer ,Array, Binary Search
*/

public class Solution {

	//Solution1:
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int total = m + n;
		if (total % 2 != 0) {
			return findKth(A, 0, m - 1, B, 0, n - 1, total / 2 + 1);
		} else {
			double x = findKth(A, 0, m - 1, B, 0, n - 1, total / 2);
			double y = findKth(A, 0, m - 1, B, 0, n - 1, total / 2 + 1);
			return (double) (x + y) / 2;
		}
	}

	public static int findKth(int[] A, int astart, int aend, int[] B, int bstart, int bend, int k) {
		int m = aend - astart + 1;
		int n = bend - bstart + 1;

		if (m > n) {
			return findKth(B, bstart, bend, A, astart, aend, k);
		}
		if (m == 0) {
			return B[k - 1];
		}
		if (k == 1) {
			return Math.min(A[astart], B[bstart]);
		}

		//this statement is assgin the median index to A and B,
		int partA = Math.min(k / 2, m);
		int partB = k - partA;

		if (A[astart + partA - 1] < B[bstart + partB - 1]) {
			return findKth(A, astart + partA, aend, B, bstart, bend, k - partA);
		} else if (A[astart + partA - 1] > B[bstart + partB - 1]) {
			return findKth(A, astart, aend, B, bstart + partB, bend, k - partB);
		} else {
			return A[start + partA - 1];
		}
	}


	//Solution2
	public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;

        if (len % 2 == 0) {
        	//总数是偶数的情况，中位数 等于 (len/2 + 1 + len/2) / 2
            return (findKth(A, 0, B, 0, len / 2 + 1) + findKth(A, 0, B, 0, len / 2)) / 2.0;
        } else {
        	//总数是奇数的情况
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }

    public static int findKth(int[] A, int A_start, int[] B, int B_start, int k){
    	//A_start 已经到头，只能从B中取
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }

        //B_start 已经到头，只能从A中取。
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }

        //边界条件 k == 1，返回小的那个。
        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }
        
        // start + k / 2 - 1要是大于A的长度，表明，要从B中舍去 K / 2
        int A_key = A_start + k / 2 - 1 < A.length
                ? A[A_start + k / 2 - 1]
                : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length
                ? B[B_start + k / 2 - 1]
                : Integer.MAX_VALUE;

        //A_key < B_key , A的K/2 肯定在全部的前面，要从B中找
        //B_key < A_key,  B的K/2 肯定在全部的前面，要从A中找
        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }

}