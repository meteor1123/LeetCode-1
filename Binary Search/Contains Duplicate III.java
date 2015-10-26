/*
	Contains Duplicate III 
	Given an array of integers, find out whether there are two distinct indices i and j 
	in the array such that the difference between nums[i] and nums[j] is at most t and 
	the difference between i and j is at most k.
	Tags: Binary Search, TreeSet, SortedSet

*/

/*
    参考LeetCode Discuss：https://leetcode.com/discuss/38177/java-o-n-lg-k-solution

    TreeSet数据结构（Java）使用红黑树实现，是平衡二叉树的一种。

    该数据结构支持如下操作：

    1. floor()方法返set中≤给定元素的最大元素；如果不存在这样的元素，则返回 null。

    2. ceiling()方法返回set中≥给定元素的最小元素；如果不存在这样的元素，则返回 null。
*/
import java.util.SortedSet;

public class Solution {

    //Solution1
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //input check
        if(k<1 || t<0 || nums==null || nums.length<2) return false;
        
        SortedSet<Long> set = new TreeSet<Long>();
        
        for(int j=0; j<nums.length; j++) {
            SortedSet<Long> subSet =  set.subSet((long)nums[j] - t, (long)nums[j] + t + 1);
            if(!subSet.isEmpty()) return true;
            
            if(j >= k) {
                set.remove((long)nums[j - k]);
            }
            set.add((long)nums[j]);
        }
        return false;
    }


    //Solution2 prefer
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {

            if ((set.floor(nums[i]) != null && nums[i] <= t + set.floor(nums[i])) ||//nums[i] - set.floor(nums[i]) <= t 会溢出？
              (set.ceiling(nums[i]) != null && set.ceiling(nums[i]) <= t + nums[i])) {//set.ceiling(nums[i]) - nums[i] <= t 会溢出？
                  return true;
            }
            set.add(nums[i]);
            if (i >= k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }


}


/*
    1. 给每一个元素设置一个桶， bucket = num[i] / (t + 1), 桶序号为Key， 元素值为value 将元素存入HashMap<bucket, num>
         比如当 t = 3时， 0 , 1 ,2 ,3 就在 序号为0的桶中，表示在这个桶中的所有数都是满足互相的差值 <= t(3)
         因此如果我们找到相同桶序号的元素返回true，或者在bucket-1 以及bucket+1的桶中去找，并且这个元素与bucket元素的差值也要<= t
    2. 我们注意到，上面的方法虽然可以很方便的解决两元素差值的判断，但是我们如何确保两个元素的间距最多不超过k呢？
        如果map.keySet().size() == k，表示HashMap里面的条目已经等于K，
        由于我们的put操作是在比较以后才放入，也就意味着如果不remove最左边的放入的元素，put操作以后，map.keySet().size() 会大于k，会导致出现在下一次循环的时候出现t符合，但是k不符合仍然返回true的错误情况
        因此每当能到map.keySet().size() == k这个判断 就意味我们就要从hashmap中移除num[i - k], 这样在后面put元素进来，并进行下一次循环的时候保证在范围k内。

*/
//Solution time O(n)! Best!
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long)nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket) || 
                (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t) || 
                (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)) {
                    return true;
                }
            /*
                if k == 3, t == 3,

                0, 1, 2 , 3 是一个bucket，
                当0, 4 , 8， 12时  map里有0， 4， 8，size() == k, 我们需要remove 1， 如果不move不然下一次如果进来2 会出现 （0， 4， 8， 11）错误匹配 0和2
            */
            if (map.keySet().size() == k) {
                long lastBucket = ((long)nums[i - k] - Integer.MIN_VALUE) / ((long)t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
}
