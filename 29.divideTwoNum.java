/*
	Divide Two Integers
	Divide two integers without using multiplication, division and mod operator.
	If it is overflow, return MAX_INT.
	Tags: Math, Binary Search
*/

public class Solution {
	public int divide (int dividend, int divisor) {
		long a = Math.abs((long)dividend);
		long b = Math.abs((long)divisor);

		long res = 0;

		/*			 tmp	
                 {    |   } 
			21 - 3 * 2^0 = 18  <-- (a - tmp) //res = 2^0
			|    |    |
			a    b   base

			18 - 3 * 2^1 = 12   // res =  2^0 + 2^1
			12 - 3 * 2^2 = 0;   // res =  2^0 + 2^1 + 2^2
			res = 2^0 + 2^1 + 2^2 = 7;

			35 - 3 * 2^0 = 32;  // res = 2^0  
			32 - 3 * 2^1 = 26;  // res = 2^0 + 2^1 
			26 - 3 * 2^2 = 14;  // res = 2^0 + 2^1 + 2^2 =7
			
			//a = 14, tmp = 3 * 2^ 3 = 24， a < tmp, break, but a still larger than b
			14 - 3 * 2^3 < 0;  < 0 ;break for loop，进行下一个while loop，此时a = 14, b = 3

			14 - 3 * 2^0 = 11; //  res = 2^0 + 2^1 + 2^2 + 2^0
			11 - 3 * 2^1 = 5;  //  res = 2^0 + 2^1 + 2^2 + 2^0 +2^1 = 11 
			此时tmp = 3 * 2^2 = 12   a < tmp so break


			
		*/
		while (a >= b) {
			//tmp is used to store the divisor mutiple the base,
			//at initial, base equals 1, and cotinue left move the base, 
			//and use a minus tmp, compare to the tmp, if smaller, break to for loop, 
			//if a still no less than b, and we make next for loop
			for (long tmp = b, base = 1; a >= tmp; tmp <<= 1, base <<= 1) {
				res = res + base;
				a = a - tmp;
			}
		}
		//dividend和divisor按位异或之后 右移31位则只剩下符号位，再将最高位的符号位与1进行与操作确定符号（1正,0负）。
		//bitwise XOR dividend and divisor, and right move 31 bit, that will just ramain 1 sign bit
		//if sign bit equals 1 means the res is negative, since (1 XOR 0, O XOR 1  == 1) ,else is positive 
		res = (((dividend ^ divisor) >> 31) & 1) == 1 ? -res: res;
		if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) 
			return Integer.MAX_VALUE;
		return (int) res;
	}
}