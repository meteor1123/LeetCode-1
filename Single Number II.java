/*
	Single Number II 
	Given an array of integers, every element appears three times except for one. Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class Solution {
	public int singleNumber(int A[], int n) {
	    int count[32] = {0};
	    int result = 0;
	    for (int i = 0; i < 32; i++) {
	        for (int j = 0; j < n; j++) {
	            if ((A[j] >> i) & 1) {
	                count[i]++;
	            }
	        }
	        result |= ((count[i] % 3) << i);
	    }
	    return result;
	}
}


