/*
	Happy Number
	Write an algorithm to determine if a number is "happy".

	A happy number is a number defined by the following process: Starting with any positive integer, 
	replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 
	(where it will stay), or it loops endlessly in a cycle which does not include 1. 
	Those numbers for which this process ends in 1 are happy numbers.
	Example: 19 is a happy number

	12 + 92 = 82
	82 + 22 = 68
	62 + 82 = 100
	12 + 02 + 02 = 1
	Tags: HashSet, Math
*/


public class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(n);
        int newValue = 0;
        while (n > 0) {
            newValue += Math.pow(n % 10, 2) ;
            n = n / 10;
            if (n == 0) {
                if (set.contains(newValue)) {
                    return false;
                } else if (newValue == 1) {
                    return true;
                } else {
                    set.add(newValue);
                    n = newValue;
                    newValue = 0;
                }
            }
        } 
        return false;
    }
}