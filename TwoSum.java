/*
	Given an array of integers, find two numbers such that they add up to a specific target number.
	The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
	Please note that your returned answers (both index1 and index2) are not zero-based.
	You may assume that each input would have exactly one solution.

	Input: numbers={2, 7, 11, 15}, target=9
	Output: index1=1, index2=2
*/

/*
	Using HashMap to store the numbers[i] and position,if find target - numbers[i] in map,mean find successfull. 
*/

public class Solution {
	public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			//并不是判断number[i]是否存在hashmap，而是巧妙判断target-number[i]是否存在
			if (!hm.containsKey(target - numbers[i])) {
				hm.put(numbers[i]);
			} else {
				res[0] = hm.get(target - numbers[i]) + 1;
				res[1] = i + 1;
				break;
			}
		}
		return res;
	}
}