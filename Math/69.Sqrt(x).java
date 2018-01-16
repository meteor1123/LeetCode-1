/*
	Implement int sqrt(int x).
	Compute and return the square root of x.
	Tags: Binary Search, Math
*/


//Solution1
public class Solution {
	/*    
        因为是求数x（x>=0) 的平方根， 因此，结果一定是小于等于x且大于等于0，所以用二分查找法肯定能搜到结果。
        以每一次的mid的平方来与target既数x相比：
        如果mid*mid == x，返回mid；
        如果mid*mid < x，那么说明mid过小，应让low = mid+1，在右边继续查找
        如果mid*mid > x，那么说明mid过大，应让high = mid-1，在左边继续查找
    */
    public int sqrt(int x) {
        int low = 0;
        int high = x;
        while (low <= high) {
            //要使用long型防止溢出
            long mid = (long)(low + high) / 2;
            if (mid * mid > x)
                high = (int)mid - 1;
            else if (mid * mid < x)
                low = (int)mid + 1;
            else 
                return (int)mid;
        }
        /*
            若x无法开整数根号（在上述查找中没有找到），那么我们仍然可以利用之前对二分查找法总结的技巧，当target值不在数组中，
            low指向大于target的那个值，high指向小于target的那个值，由于我们需要向下取整的结果，
            所以我们返回high指向的值（这里high指向的值和high的值是同一个值），这个值就是所求得最接近起开根号结果的整数值。
        */
        return high;
    }
}


//Solution2 prefer
public class Solution {
    public int mySqrt(int x) {
         if (x == 0) {
            return 0;
         }
        int left = 1;
        int right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > (x / mid)) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            }
        }
    }
   
}