/*
	Two Strings Are Anagrams
	Write a method anagram(s,t) to decide if two strings are anagrams or not.

	Example
	Given s="abcd", t="dcab", return true
*/

public class Solution {
	public boolean anagram(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		int[] sArr = new int[256];
		int[] tArr = new int[256];
		for (int i = 0; i < s.length(); i++) {
			sArr[s.charAt(i)]++;
			tArr[t.charAt(i)]++;
		}
		for (int i = 0; i < 256; i++) {
			if (sArr[i] != tArr[i]) {
				return false;
			}
		}
		return true;
	}
}