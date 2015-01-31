/*
	Remove Element
	Given an array and a value, remove all instances of that value in place and return the new length.
	The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/



public class Solution {
    public int removeElement(int[] A, int elem) {
    	if (A == null || A.length == 0)
    		return 0;
    	int len = A.length - 1;
    	for (int i = 0; i < len; i++) {
    		while (A[i] == elem && i < len) {
    			A[i] = A[--len];
    		}
    	}
    	return len;
    }
}