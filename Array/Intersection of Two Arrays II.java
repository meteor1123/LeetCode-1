/*
	 Intersection of Two Arrays II
	 Given two arrays, write a function to compute their intersection.

	Example:
	Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

	Note:
	Each element in the result should appear as many times as it shows in both arrays.
	The result can be in any order.
	Follow up:
	What if the given array is already sorted? How would you optimize your algorithm?
	What if nums1's size is small compared to nums2's size? Which algorithm is better?
	What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
/*
Follow up:
	(1)What if the given array is already sorted? How would you optimize your algorithm?
	 Using two pointer, start from two array respectively, if find equals just add result, else move the smaller pointer.
	(2)What if nums1's size is small compared to nums2's size? Which algorithm is better?
	
	(3)What if elements of nums2 are stored on disk, and the memory is limited 
	   such that you cannot load all elements into the memory at once?

		If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, 
		read chunks of array that fit into the memory, and record the intersections.

		If both nums1 and nums2 are so huge that neither fit into the memory, 
		sort them individually (external sort), then read 2 elements from each array at a time in memory, 
		record intersections.

 */
//Solution1: prefer, one HashSet
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> resList = new ArrayList<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                resList.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] res = new int[resList.size()];
        int i = 0;
        for (int num: resList) {
            res[i++] = num;
        }
        return res;
    }
}

//Solution2: sort + two pointer
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
       Arrays.sort(nums1);
       Arrays.sort(nums2);
       List<Integer> resList = new ArrayList<>();
       int i = 0;
       int j = 0;
       while (i < nums1.length && j < nums2.length) {
           if (nums1[i] > nums2[j]) {
               j++;
           } else if (nums1[i] < nums2[j]) {
               i++;
           } else {
               resList.add(nums2[j]);
               i++;
               j++;
           }
       }
       int k = 0;
       int[] res = new int[resList.size()];
       for (int num : resList) {
           res[k++] = num;
       }
       return res;
    }
}

//Solution3: time: O(m + n), space:O(m + n)
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> sourceMap = new HashMap<>();
        HashMap<Integer, Integer> inputMap = new HashMap<>();
        for (int num : nums1) {
            if (!sourceMap.containsKey(num)) {
                sourceMap.put(num, 1);
            } else {
                sourceMap.put(num, sourceMap.get(num) + 1);
            }
        }
        for (int num : nums2) {
            if (!inputMap.containsKey(num)) {
                inputMap.put(num, 1);
            } else {
                inputMap.put(num, inputMap.get(num) + 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int num: nums1) {
            if (inputMap.containsKey(num) && inputMap.get(num) > 0 && sourceMap.get(num) > 0) {
                res.add(num);
                inputMap.put(num, inputMap.get(num) - 1);
                sourceMap.put(num, sourceMap.get(num) - 1);
            }
        }
        int[] resArr = new int[res.size()];
        int i = 0;
        for (int num: res) {
            resArr[i++] = num;
        }
        return resArr;
    }
}