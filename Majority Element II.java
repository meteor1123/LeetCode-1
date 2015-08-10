/*
	Majority Element II
	Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space. 
*/

/*
	n1, n2 are 2 distinct numbers. c1, c2 are the count of the 2 numbers. 
	Whenever encounter 3 different numbers, cancel them by decreasing the count. 
	And the remaining 2 numbers (or 1 or 0) are candidates. 
	Scan the array one more time to determine the result.
*/
public class Solution {
    //Solution1
     public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int major1 = 0, major2 = 0;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            int major3 = nums[i];
            if (count1 > 0 && count2 > 0) {
                if (major3 != major1 && major3 != major2) {
                    count1--;
                    count2--;
                } else if (major3 == major1) {
                    count1++;
                } else {
                    count2++;
                }
            } else if (count1 > 0) {
                if (major3 == major1) {
                    count1++;
                } else {
                    major2 = major3;
                    count2++;
                }
            } else if (count2 > 0) {
                if (major3 == major2) {
                    count2++;
                } else {
                    major1 = major3;
                    count1++;
                }
            } else {
                major1 = major3;
                count1++;
            }
        }
        count1 = count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == major1) {
                count1++;
            } else if (nums[i] == major2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(major1);
        }
        if (count2 > nums.length / 3) {
            res.add(major2);
        }
        return res;
    }

    //Solution2
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        
        for (int i = 0; i < nums.size(); i++) {
            if (candidate1 == nums.get(i)) {
                count1++;
            } else if (candidate2 == nums.get(i)) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = nums.get(i);
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = nums.get(i);
                count2 = 1; 
            } else {
                count1--;
                count2--;
            }
        }
        
        count1 = count2 = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == candidate1) {
                count1++;
            } else if (nums.get(i) == candidate2) {
                count2++;
            }
        }
        return count1 > count2 ? candidate1 : candidate2;
    }
}