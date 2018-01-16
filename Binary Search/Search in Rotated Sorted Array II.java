/*
	Search in Rotated Sorted Array II 
	Follow up for "Search in Rotated Sorted Array":
	What if duplicates are allowed?
	Would this affect the run-time complexity? How and why?
	Write a function to determine if a given target is in the array.
*/

/*
	 the worst case is O(n).

To explain why, consider this sorted array 1111115, which is rotated to 1151111.
Assume left = 0 and mid = 3, and the target we want to search for is 5. Therefore, 
the condition A[left] == A[mid] holds true, which leaves us with only two possibilities:
All numbers between A[left] and A[right] are all 1's.
Different numbers (including our target) may exist between A[left] and A[right].
As we cannot determine which of the above is true, the best we can do is to move left one step to the right and repeat the process again. 
Therefore, we are able to construct a worst case input which runs in O(n), for example: the input 11111111...115.
*/
public class Solution {
    public boolean search(int[] A, int target) {
        for (int i = 0; i < A.length; i++) {
        	if(A[i] == target)
        		return true;
        }
        return false;
    }
}