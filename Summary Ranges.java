/*
	Summary Ranges 
	Given a sorted integer array without duplicates, return the summary of its ranges.

	For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

public class Solution {
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<String>();
		if (nums = null || nums.length == 0) {
			return res;
		}
		//case 1 only has one element
		if (num.length == 1) {
			res.add("" + nums[0]);
			return res;
		}
		//use left to record the left border
		int left = nums[0];
		//use right to record the right border
		int right = 0;
		//use count to record the continuous arrange
		int count = 0;
		for (int i = 1; i < nums.length; i++) {
			right = nums[i];
			if (nums[i] > nums[i - 1] + 1) {
				if (count == 0) {
					res.add("" + left);
				} else {
					res.add("" + left + "->" + right);
					count = 0;
				}
				left = right;
				if (i + 1 == nums.length) {
					res.add("" + right);
				}
			} else {
				count++;
			}
		}
		//if count != 0 means still have range do not add to the result list then add it.
		if (count != 0) {
			res.add("" + left + "->" + (left + count));
		}
		return res;
	}
}