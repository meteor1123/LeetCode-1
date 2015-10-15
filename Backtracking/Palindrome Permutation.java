/*
	Palindrome Permutation
	Given a string, determine if a permutation of the string could form a palindrome.

	For example,
	"code" -> False, "aab" -> True, "carerac" -> True.

	Hint:

	Consider the palindromes of odd vs even length. What difference do you notice?
	Count the frequency of each character.
	If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

public class Solution {
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (hm.containsKey(c)) {
                hm.remove(c);
            } else {
                hm.put(c, 1);
            }
        }
        int sum = 0;
        for (int i : hm.values()) {
            sum += i;
        }
        return sum > 1 ? false : true;
    }
}

public class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] letter = new int[256];
        int count = 0;
        for (char c : s.toCharArray()) {
            if (letter[c] == 0) {
                letter[c]++;
            } else {
                letter[c]--;
            }
        }
        for (int i : letter) {
            count += i;
        }
        return count > 1 ? false : true;
    }
}