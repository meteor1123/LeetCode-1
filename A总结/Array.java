/*
	Array
*/




1. Matrix Problem
		1.1 Sparse Matrix Multiplication
			/*
				Given two sparse matrices A and B, return the result of AB.
				You may assume that A's column number is equal to B's row number.
				Example:
				A = [
				  [ 1, 0, 0],
				  [-1, 0, 3]
				]
				B = [
				  [ 7, 0, 0 ],
				  [ 0, 0, 0 ],
				  [ 0, 0, 1 ]
				]
				     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
				AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
				                  | 0 0 1 |
			*/
			public class Solution {
			    public int[][] multiply(int[][] A, int[][] B) {
			        int m = A.length;
			        int n = A[0].length;
			        int nB = B[0].length;
			        int[][] C = new int[m][nB];
			        for (int i = 0; i < m; i++) {
			            for (int k = 0; k < n; k++) {
			                if (A[i][k] != 0) {
			                    for (int j = 0; j < nB; j++) {
			                        C[i][j] += A[i][k] * B[k][j];
			                    }
			                }
			            }
			        }
			        return C;
			    }
			}



2. Array Problem
		2.1 Majority Element
			/*
				More than [n / 2]
			*/
			public class Solution {
			    public int majorityElement(int[] nums) {
			        int count = 1;
			        int majorityNum = nums[0];
			        for (int i = 1; i < nums.length; i++) {
			            if (nums[i] == majorityNum) {
			                count++;
			            } else if (count == 0) {
			                count++;
			                majorityNum = nums[i];
			            } else {
			                count--;
			            }
			        }
			        return majorityNum;
			    }
			}
		2.2 Majority Element II
			/*
				More than  [n / 3];
			*/
			public class Solution {
			    public List<Integer> majorityElement(int[] nums) {
			        List<Integer> res = new ArrayList<>();
			        if (nums == null || nums.length == 0) {
			            return res;
			        }
			        int count1 = 0, count2 = 0;
			        int candidate1 = 0, candidate2 = 1;
			        for (int num : nums) {
			            if (num == candidate1) {
			                count1++;
			            } else if (num == candidate2) {
			                count2++;
			            } else if (count1 == 0) {
			                candidate1 = num;
			                count1 = 1;
			            } else if (count2 == 0) {
			                candidate2 = num;
			                count2 = 1;
			            } else {
			                count1--;
			                count2--;
			            }
			        }
			        count1 = count2 = 0;
			        for (int num : nums) {
			            if (num == candidate1) {
			                count1++;
			            }
			            if (num == candidate2) {
			                count2++;
			            } 
			        }
			        if (count1 > nums.length / 3) {
			            res.add(candidate1);
			        }
			        if (count2 > nums.length / 3) {
			            res.add(candidate2);
			        }
			        return res;
			    }
			}
		2.3 Maximum Subarray
		/*
			Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
			For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
			the contiguous subarray [4,−1,2,1] has the largest sum = 6.
		*/
			public class Solution {
			    public int maxSubArray(int[] nums) {
			        int localMax = nums[0];
			        int globalMax = nums[0];
			        for (int i = 1; i < nums.length; i++) {
			            localMax = Math.max(nums[i], localMax + nums[i]);
			            globalMax = Math.max(localMax, globalMax);
			        }
			        return globalMax;
			    }
			}

2. Two Pointer Problem

3. K Sum Problem

4. A


