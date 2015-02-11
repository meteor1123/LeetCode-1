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
	 public  String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int maxLen = Math.max(aLen, bLen);
        int carry = 0;
        String res = "";
        for (int i = 0; i < maxLen; i++) {
            int valA = 0;
            int valB = 0;
            if (i < aLen) 
                valA = a.charAt(aLen - i - 1) - '0';
            else 
                valA = 0;
            if (i < bLen)
                valB = b.charAt(bLen - i - 1) - '0';
            else 
                valB = 0;
            int sum = valA + valB + carry;
            carry = sum / 2;
            res = sum % 2 + res;
        }
        return (carry == 0) ? res : "1" + res;
    }
}