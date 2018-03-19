/*
	686. Repeated String Match

	Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

	For example, with A = "abcd" and B = "cdabcdab".

	Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

	Note:
	The length of A and B will be between 1 and 10000.
*/

// 这里让求的是需要repeat 多少次A才能 contains B，而不是A中B的index，所以只需要判断repeat几次可以contains B 就好了， 用 indexOf方法
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int index = 1;
        StringBuilder sb = new StringBuilder(A);
        while (sb.length() < B.length()) {
            index++;
            sb.append(A);
        }
        if (sb.indexOf(B) >= 0)
            return index;
        if (sb.append(A).indexOf(B) >= 0)
            return index + 1;
        return -1;
    }
}