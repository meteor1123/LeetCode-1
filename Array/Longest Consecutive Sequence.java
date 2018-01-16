/*
	Longest Consecutive Sequence
	Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

	For example,
	Given [100, 4, 200, 1, 3, 2],
	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

	Your algorithm should run in O(n) complexity.
*/



/*
    We will use HashMap. The key thing is to keep track of the sequence length and store that in the boundary points of the sequence. 
    For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.

    Whenever a new element n is inserted into the map, do two things:

    1) See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. 
        Variables left and right will be the length of those two sequences, while 0 means there is no sequence and n will be the boundary point later. 
        Store (left + right + 1) as the associated value to key n into the map.
    2) Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length.
        Everything inside the for loop is O(1) so the total time is O(n). Please comment if you see something wrong. Thanks.
*/
//Solution1
public class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            if (!map.containsKey(i)) {
                int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
                int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;
                
                int sum = left + right + 1;
                map.put(i, sum);
                
                res = Math.max(res, sum);
                
                map.put(i - left, sum);
                map.put(i + right, sum);
            } else {
                continue;
            }
        }
        return res;
    }
}

           
//Solution2            
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