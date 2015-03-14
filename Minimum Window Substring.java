/*
	Minimum Window Substring
	Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

	For example,
	S = "ADOBECODEBANC"
	T = "ABC"
	Minimum window is "BANC".

	Note:
	If there is no such window in S that covers all characters in T, return the emtpy string "".

	If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

public class Solution {
	public String minWindow(String S, String T) {
        String res = "";
        if (S == null || S.length() == 0 || T == null || T.length() == 0)
            return res;
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (!dict.containsKey(T.charAt(i)))
                dict.put(T.charAt(i), 1);
            else 
                dict.put(T.charAt(i), dict.get(T.charAt(i)) + 1);
        }
        
        int count = 0;
        int left = 0;
        int minLen = S.length() + 1;
        int minStart = 0;
        
        for (int right = 0; right < S.length(); right ++) {
            if (dict.containsKey(S.charAt(right))) {
                dict.put(S.charAt(right), dict.get(S.charAt(right)) - 1);
                if (dict.get(S.charAt(right)) >= 0)
                    count++;
                while (count == T.length()) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        minStart = left;
                    }
                    
                    if (dict.containsKey(S.charAt(left))) {
                        dict.put(S.charAt(left), dict.get(S.charAt(left)) + 1);
                        if (dict.get(S.charAt(left)) > 0)
                            count--;
                    }
                    left++;
                }
            }
        }
        if (minLen > S.length())
            return "";
        return S.substring(minStart, minStart + minLen);
    }
}
