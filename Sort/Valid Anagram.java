/*
	Valid Anagram
	Given two strings s and t, write a function to determine if t is an anagram of s.

	For example,
	s = "anagram", t = "nagaram", return true.
	s = "rat", t = "car", return false.


*/

public class Solution {

	//O(n) time complexity, O(1) space
	public boolean isAnagram(String s, String t) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++; 
        }
        
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'a']--;
        }
        
        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    //sort O(nlogn) time, O(n) space
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return false;
            }
        }
        return true;
    }

    //HashMap
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hm.containsKey(s.charAt(i))) {
                hm.put(s.charAt(i), hm.get(s.charAt(i)) + 1);
            } else {
                hm.put(s.charAt(i), 1);
            }
        }
        
        for (int i = 0; i < t.length(); i++) {
            if (hm.containsKey(t.charAt(i))) {
                hm.put(t.charAt(i), hm.get(t.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        
        for (Integer val : hm.values()) {
            if (val > 0) {
                return false;
            }
        }
        return true;
    }

}