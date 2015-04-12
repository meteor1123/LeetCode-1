/*
	Single Number II 
	Given an array of integers, every element appears three times except for one. Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class Solution {
	//Solution1
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

	//Solution2
	/*
		Solution: 
			How to find the single number>
			1. create a 32 length array, this array's index is store the index bit's sum of array numbers
			2. 
	*/
	public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result=0;
        int[] bits=new int[32];
        for (int i = 0; i < 32; i++) {
            for(int j = 0; j < A.length; j++) {
            	//check the i th bit position is 1 or 0,
                bits[i] += A[j] >> i & 1;
                //and plus the previous sum of i th bit position, 
                //if divide three and the remainder equals 0, means, has 3 or 3's  multiple bits,
                bits[i] %= 3;
            }

            //after every bit position's traversal, we use or operation to save the i th bits position's value
            //and use left shift to stroe the value.
            result |= (bits[i] << i);
        }
        return result;
    }
}


