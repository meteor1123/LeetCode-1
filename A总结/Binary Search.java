/*
	Binary Search
*/




3. Binary Search Tree
	3.1 Contains Duplicate III
		/*
			TreeSet数据结构（Java）使用红黑树实现，是平衡二叉树的一种。
    		该数据结构支持如下操作：
		    1. floor()方法返set中不大于给定元素的最大元素；如果不存在这样的元素，则返回 null。
		    2. ceiling()方法返回set中不小于给定元素的最小元素；如果不存在这样的元素，则返回 null。
		*/
		public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
	        if (k < 1 || t < 0) {
	            return false;
	        }
	        TreeSet<Integer> set = new TreeSet<Integer>();
	        for (int i = 0; i < nums.length; i++) {
	            if ((set.floor(nums[i]) != null && nums[i] <= t + set.floor(nums[i])) ||
	             (set.ceiling(nums[i]) != null && set.ceiling(nums[i]) <= t + nums[i])){
	                 return true;
	             }
	            set.add(nums[i]);
	            if (i >= k) {
	                set.remove(nums[i - k]);
	            }
	        }
	        return false;
	    }