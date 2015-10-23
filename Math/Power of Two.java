/*
	Power of Two
	Given an integer, write a function to determine if it is a power of two.
*/
public class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}

public class Solution {
    public boolean isPowerOfTwo(int n) {
        for (long i = 1; i <= n; i = i << 1) {
            if (i == n) {
                return true;
            }
        }
        return false;
    }
}