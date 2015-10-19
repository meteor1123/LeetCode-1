/*
	Longest Consecutive Sequence
	Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

	For example,
	Given [100, 4, 200, 1, 3, 2],
	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

	Your algorithm should run in O(n) complexity.
*/

public class Solution {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], 1);
            int end = nums[i];
            int start = nums[i];
            if (map.containsKey(nums[i] + 1)) {
                end = nums[i] + map.get(nums[i] + 1);
            }
            if (map.containsKey(nums[i] - 1)) {
                start = nums[i] - map.get(nums[i] - 1);
            }
            
            longest = Math.max(longest, end - start + 1);
            map.put(end, end - start + 1);
            map.put(start, end - start + 1);
        }
        return longest;
    }
}