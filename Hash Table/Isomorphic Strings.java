/*
	Isomorphic Strings 
	Given two strings s and t, determine if they are isomorphic.

	Two strings are isomorphic if the characters in s can be replaced to get t.

	All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

	For example,
	Given "egg", "add", return true.

	Given "foo", "bar", return false.

	Given "paper", "title", return true.

	Note:
	You may assume both s and t have the same length.
	Tags: HashTable
*/


//Solution1: Two Hash Table
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        int len = s.length();
        HashMap<Character, Character> hm = new HashMap<Character, Character>();
        HashSet<Character> used = new HashSet<Character>();
        for (int i = 0; i < len; i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (!hm.containsKey(sChar)) {
                if (used.contains(tChar)) {
                    return false;
                }
                hm.put(sChar, tChar);
                used.add(tChar);
            } else {
                if (hm.get(sChar) != tChar) {
                    return false;
                }
            }
        }
        return true;
    }
}
//Solution2: use map.containsValue method
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (!map.containsKey(c1)) {
                if (map.containsValue(c2)){
                    return false;
                }
                map.put(c1, c2);
            } else if (map.get(c1) != c2) {
                    return false;
            }
        }
        return true;
    }
}