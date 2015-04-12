/*
	Candy
	There are N children standing in a line. Each child is assigned a rating value.

	You are giving candies to these children subjected to the following requirements:

	Each child must have at least one candy.
	Children with a higher rating get more candies than their neighbors.
	What is the minimum candies you must give?

	Tags:Greedy , DP
*/
/*
	这道题和Trapping water那个是一样的想法，因为无论是水坑还是得到糖的小朋友，影响因素都不只一边，都是左右两边的最小值/最大值来决定的。

 	所以这道题跟上一道一样，也是左右两边遍历数组。

	leftnums数组存从左边遍历，当前小朋友对比其左边小朋友，他能拿到糖的数量；

	rightnums数组存从右边遍历，当前小朋友对比其右边小朋友，他能拿到的糖的数量。

 

	最后针对这两个数组，每个小朋友能拿到的糖的数量就是这两个数最大的那个数，求总加和就好了。
*/

public class Solution {
    public int candy(int[] ratings) {
        if(ratings==null || ratings.length==0)
              return 0;
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        right[len - 1] = left[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }
}