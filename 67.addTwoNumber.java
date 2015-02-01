/*
	Add Binary 
	Given two binary strings, return their sum (also a binary string).
	For example,
		a = "11"
		b = "1"
		Return "100".
	Tags: Math, String
*/

public class Solution {
	public String addBinary(String a, String b) {
		int m = s.length();
		int n = b.length();
		int carry = 0;
		String res = "";
		int maxLen = Math.max(m, n);
		for (int i = 0; i < maxLen; i++) {
			int p = 0;
			int q = 0;
			if (i < m) {
				p = a.charAt(m - i - 1) - '0';
			}
			else 
				p = 0;

			if (i < n) {
				q = a.charAt(n - i - 1) - '0';
			}
			else q = 0;

			int temp = p + q + carry;
			carry = temp / 2;
			res = temp % 2 + res;
		}
		return (carry == 0) ? res : "1" + res;	
	}
}