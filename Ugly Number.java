/*
	Ugly Number 

	Write a program to check whether a given number is an ugly number.

	Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

	Note that 1 is typically treated as an ugly number.
*/


/*
	Solution:  1.use 2 3 5  to divide the num, if can be divide  which mean 2,3,5 is the factor of this  number,
			   2. 
*/
public class Solution {
	public boolean isUgly(int num) {
		for (int i = 2; i < 6 && num > 0; i++) {
			while (num % i == 0) {
				num /= i;
			}
		}
		return num == 1;
	}
}

