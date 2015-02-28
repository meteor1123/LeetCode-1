/*
	Rotate Array 
	Rotate an array of n elements to the right by k steps.

	For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

	Note:
	Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

	[show hint]

	Credits:
	Special thanks to @Freezen for adding this problem and creating all test cases.
*/


/*
	Solution: 1. reverse 前面 length - k个数
			  2. reverse 后面 k 个数
			  3. reverse 所有的数，这样就可以得到在倒数位置K逆转的数组
*/
public class Solution {
    public void rotate(int[] nums, int k) {
        // if (k == 0 || nums.length < 2)
        //     return;
        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }
    
    public static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
    
    public static void reverse(int[] nums, int start, int end) {
        for (int i = start; i <= (start + end) / 2; i++)
            swap(nums, i, start + end - i);
    }
}