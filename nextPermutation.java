/*
	nextPermutation
	Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

	If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

	The replacement must be in-place, do not allocate extra memory.

	Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	Tags: Array
*/

/*
	1. find the first one which make A[i] < A[i + 1] from the end to start of the array,
	2. find the smallest one which make A[j] > A[i] , from the index i + 1 to A.length - 1;
	3. swap them, and reverse the array from i + 1 to A.length - 1;
*/
public class Solution {
	public void nextPermutation(int[] num) {
        if (num == null || num.length <= 1) {
            return;
        }
        //find the first element which A[i] < A[i + 1],so the index start from num.length - 2;
        int i = num.length - 2;
        while (i >= 0 && num[i + 1] <= num[i]) {
            i--;
        }

        //if find that number, the idnex must be no less than 0;
        if (i >= 0) {
        	//the adjacent number of j
            int j = i + 1;
            //Why we don't need to compare the value?cause from j to length - 1, that is the descending array so the last large one is smallest one
            while (j < num.length && num[j] > num[i]) {// because the last sequence of array is deascending array, so the last
                                                      //one which bigger than num[i],is the smallest number which is larger than                                                        //num[i];
                j++;
            }
            
            j--;//j minus 1 is the position,smallest large num[i] one;
            swap(num, i, j);
        }
        reverse(num, i + 1, num.length - 1);
    }
    
    public void swap (int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    
    public void reverse(int[] num, int i, int j) {
        while (i < j) {
            swap(num, i++, j--);
        }   
    }
}




