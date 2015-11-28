/*
	String 
*/



1. Math, Alphabet, Game, Other
		1.1 Flip Game I & II
			/*
				For example, given s = "++++", after one move, it may become one of the following states:
				[
				  "--++",
				  "+--+",
				  "++--"
				]
				T(N) = T(N-2) + T(N-3) + [T(2) + T(N-4)] + [T(3) + T(N-5)] + ... 
				        [T(N-5) + T(3)] + [T(N-4) + T(2)] + T(N-3) + T(N-2)
				     = 2 * sum(T[i])  (i = 3..N-2)
			*/
			//I
			public class Solution {
			    public List<String> generatePossibleNextMoves(String s) {
			        List<String> res = new ArrayList<>();
			        if (s == null || s.length() < 2) {
			            return res;
			        }
			        for (int i = 0; i < s.length(); i++) {
			            if (s.startsWith("++", i)) {
			                res.add(s.substring(0, i) + "--" + s.substring(i + 2));
			            }
			        }
			        return res;
			    }
			}
			//II
			public class Solution {
			    public boolean canWin(String s) {
			        if (s == null || s.length() < 2) {
			            return false;
			        }
			        for (int i = 0; i < s.length() - 1; i++) {
			            if (s.startsWith("++", i)) {
			                String item = s.substring(0, i) + "--" +s.substring(i + 2);
			                //If the opponent can't win, then we win,recursively check the result
			                if (!canWin(item)) {
			                    return true;
			                }
			            }
			        }
			        return false;
			    }
			}
		1.2 Integer To Roman
			public class Solution {
				public static String intToRoman(int num) {
			        String M[] = {"", "M", "MM", "MMM"};//1000, 2000, 3000
			        //                100,  200,  300,   400, 500,  600,  700,   800,   900
			        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
			        //                10,   20,   30,    40,  50,   60,   70,    80,    90  
			        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
			        //                1,    2,    3,     4,   5,    6,    7,     8,     9
			        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
			        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
			    }
			}
		1.3 Roman To Integer
			public class Solution {
			    public int romanToInt(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        int res = 0;
			        for (int i = s.length() - 1; i >= 0; i--) {
			            char c = s.charAt(i);
			            if (c == 'I') {
			                if (res >= 5) {
			                    res -= 1;
			                } else {
			                    res += 1;
			                }
			            } else if (c == 'V') {
			                res += 5;
			            } else if (c == 'X') {
			                if (res >= 50) {
			                    res -= 10;
			                } else {
			                    res += 10;
			                }
			            } else if (c == 'L') {
			                res += 50;
			            } else if (c == 'C') {
			                if (res >= 500) {
			                    res -= 100;
			                } else {
			                    res += 100;
			                }
			            } else if (c == 'D') {
			                res += 500;
			            } else if (c == 'M') {
			                res += 1000;
			            }
			        }
			        return res;
			    }
			}
		1.4 Add Binary
			public class Solution {
			    public String addBinary(String a, String b) {
			        if (a == null || a.length() == 0) {
			            return b;
			        }
			        if (b == null || b.length() == 0) {
			            return a;
			        }
			        StringBuilder sb = new StringBuilder();
			        int flag = 0;
			        int i = a.length() - 1;
			        int j = b.length() - 1;
			        while (i >= 0 || j >= 0) {
			            if (i >= 0) {
			                flag += a.charAt(i) - '0';
			                i--;
			            }
			            if (j >= 0) {
			                flag += b.charAt(j) - '0';
			                j--;
			            }
			            sb.insert(0, flag % 2);
			            flag = flag / 2;
			        }
			        if (flag == 1) {
			            sb.insert(0, "1");
			        }
			        return sb.toString();
			    }
			}
		1.5 Count And Say
			/*
				The count-and-say sequence is the sequence of integers beginning as follows:
				1, 11, 21, 1211, 111221, ...

				1 is read off as "one 1" or 11.
				11 is read off as "two 1s" or 21.
				21 is read off as "one 2, then one 1" or 1211.
			*/
			public class Solution {
			    public String countAndSay(int n) {
			        if (n == 0) {
			            return "";
			        } 
			        String res = "1";
			        int count = 1;
			        for (int i = 1; i < n; i++) {
			            StringBuilder curRes = new StringBuilder();
			            for (int i = 1; j < res.length(); j++) {
			                if (res.charAt(i - 1) == res.charAt(i)) {
			                    count++;
			                } else {
			                    curRes.append(count);
			                    curRes.append(res.charAt(i - 1));
			                    count = 1;
			                }
			            }
			            curRes.append(count);
			            curRes.append(res.charAt(res.length() - 1));
			            count = 1;
			        }
			        return res;
			    }
			}
		1.6 Compare Version Numbers
			public class Solution {
			    public int compareVersion(String version1, String version2) {
			        String[] v1 = version1.split("\\.");
			        String[] v2 = version2.split("\\.");
			        int len = Math.max(v1.length, v2.length);
			        for (int i = 0; i < len; i++) {
			            Integer i1 = i < v1.length ? Integer.parseInt(v1[i]) : 0; 
			            Integer i2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
			            if (i1 > i2) {
			                return 1;
			            } else if (i1 < i2) {
			                return -1;
			            } 
			        }
			        return 0;
			    }
			}

2. Longest Problem
		2.1 Longest Substring With At Most Two Distinct Characters
			public class Solution {
			    public int lengthOfLongestSubstringTwoDistinct(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        HashMap<Character, Integer> map = new HashMap<>();
			        int maxLen = 0;
			        int start = 0;
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (!map.containsKey(c) && map.size() == 2) {//key point
			                int leftCharPos = s.length();
			                char leftChar = ' ';
			                for (char ch : map.keySet()) {
			                    if (map.get(ch) < leftCharPos) {
			                        leftCharPos = map.get(ch);
			                        leftChar = ch;
			                    }
			                }
			                start = leftCharPos + 1;
			                map.remove(leftChar);
			            }
			            map.put(c, i);
			            maxLen = Math.max(maxLen, i - start + 1);
			        }
			        return maxLen;
			    }
			}
		2.2 Longest Valid Parentheses
			/*
				1. 新建一个stack，用以保存括号char的序号，而不是保存内容，遍历String上的所有char， 遇到'('一律将index进栈.
			    2. 遇到')'，一律不进栈，此时判断stack是否为空如果为空
			       1） 为空说明之前已经将'('匹配完出栈，已经没有'('可以进行匹配，所以需要将start的位置 赋值为i+ 1，跳过这个')'
			       2） 如果栈非空， pop 一个'('和这个')'进行匹配， 匹配完成后判断栈是否为空，
			            2.1）如果空  maxLen = Math.max(maxLen, i - start + 1) 
			            2.2）如果栈非空  maxLen = Math.max(maxLen, i - stack.pop())
			*/
			public class Solution {
			    public int longestValidParentheses(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;  
			        }
			        Stack<Integer> stack = new Stack<Integer>();
			        int start = -1;
			        int maxLen = 0;
			        //use a stack to store the unvalid parentheses
			        for (int i = 0; i < s.length(); i++) {
			            if (s.charAt(i) == '(') {
			                stack.push(i);
			            } else {
			                ////if stack is empty, it means that we already found a complete valid combo and pop to the stack
			                // or since we meet a right parenthesesm so we need to update the last index. to store the unmatched  position
			                if (stack.isEmpty()) {
			                    start = i;
			                } else {
			                    stack.pop();
			                    maxLen = stack.isEmpty() ? Math.max(maxLen, i - start) : Math.max(maxLen, i - stack.peek());
			                }
			            }
			        }
			        return maxLen;
			    }
			}
		2.3	Longest Substring Without Repeating Characters
			/*
				step1: 设定一个滑动窗口，窗口的左边界为left，右边界为right，维护一个全局最大距离变量 max = right - left + 1
				step2: 先不停将right往右边移动，并放入hashset，只要hashset中没有重复的字符，right一直++
				step3: 一旦遇到重复的字符，判断先前的 max 是否小于现在的right - left + 1，如果小于则更新max,
					   并且需要将right之前存在set里的字符remove，因为下一次要进行比较的窗口序列是从right开始，也就是 left = right
				step4: 每一次left前进只在找到重复字符时才++，
					   相反right只在没找到重复字符时才++.
			*/
			public class Solution {
			    public int lengthOfLongestSubstring(String s) {
			        if (s == null || s.length() == 0) {
			            return 0;
			        }
			        int left = 0;
			        int right = 0;
			        int maxLen = 0;
			        HashSet<Character> set = new HashSet<>();
			        while (right < s.length()) {
			            char c = s.charAt(right);
			            if (set.contains(c)) {
			                while (s.charAt(left) != c) {
			                    set.remove(s.charAt(left));
			                    left++;
			                }
			                left++;
			            } else {
			                set.add(c);
			                maxLen = Math.max(maxLen, right - left + 1);
			            }
			            right++;
			        }
			        maxLen = Math.max(maxLen, right - left);
			        return maxLen;
			    }
			}
		2.4 Longest Palindromic Substring
			/*
				中心回探法O（n*n)
			*/
			public class Solution {
			    private int low, maxLen;
			    public String longestPalindrome(String s) {
			        int len = s.length();
			        if (len < 2) {
			            return s;
			        }
			        
			        for (int i = 0; i < s.length(); i++) {
			            extendPalindrome(s, i, i);
			            extendPalindrome(s, i, i + 1);
			        }
			        return s.substring(low, low + maxLen);
			    }
			    public void extendPalindrome(String s, int i, int j) {
			        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			            i--;
			            j++;
			        }
			        if (maxLen < j - i - 1) { 
			            low = i + 1;
			            maxLen = j - i - 1;
			        }
			    }
			}
			/*
				DP
			*/
			public String longestPalindrome(String s) {
		        if (s == null || s.length() == 0) {
		            return "";
		        }
		        //isPalin[i][j], i and j are two indices of the string, 
		        //denote whether substring from i to j is palindrome;
		        //isPalin[i][i] is always palindrome, since s.charAt(i) == s.charAt(i)
		        boolean[][] isPalind = new boolean[s.length()][s.length()];
		        String res = "";
		        int maxLen = 0;
		        for (int i = s.length() - 1; i >= 0; i--) {
		            for (int j = i; j < s.length(); j++) {
		                // j - i <= 2 --> aba or aa 肯定palindrome
		                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalind[i + 1][j - 1])) {
		                    isPalind[i][j] = true;
		                    if (maxLen < j - i + 1) {
		                        maxLen = j - i + 1;
		                        res = s.substring(i, j + 1);
		                    }
		                }
		            }
		        }
		        return res;
		    }
		    /*
		    	Best manacher's Algorithm   O(n) O(n)
				http://www.felix021.com/blog/read.php?2040
				http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
			*/
			public class Solution {
			    public String longestPalindrome(String s) {
			        if (s == null || s.length() <= 1) {
			            return s;
			        }
			        s = preProcess(s);
			        int[] p = new int[s.length()];
			        int mid = 0;
			        int max = 0;
			        for (int i = 1; i < s.length() - 1; i++) {
			            p[i] = i < max ? Math.min(p[mid - (i - mid)], max - i) : 0; 
			            while (s.charAt(i - p[i] - 1) == s.charAt(i + p[i] + 1)) {
			                p[i]++;
			            }
			            if (i + p[i] > max) {
			                mid = i;
			                max = p[i];
			            }
			        }
			        int maxLen = 0;
			        mid = 0;
			        for (int i = 0; i < s.length(); i++) {
			            if (p[i] > maxLen) {
			                mid = i;
			                maxLen = p[i];
			            }
			        }
			        StringBuilder sb = new StringBuilder();
			        for (int i = mid - maxLen; i <= mid + maxLen; i++) {
			            if (s.charAt(i) != '#') {
			                sb.append(s.charAt(i));
			            }
			        }
			        return sb.toString();
			    }
			    public String preProcess(String s) {
			        StringBuilder sb = new StringBuilder();
			        sb.append('~');
			        for (int i = 0; i < s.length(); i++) {
			            sb.append('#');
			            sb.append(s.charAt(i));
			        }
			        sb.append("#$");
			        return sb.toString();
			    }
			}
		2.5 Longest Common Prefix
			// indexOf method
			public class Solution {
			    public String longestCommonPrefix(String[] strs) {
			        if (strs == null || strs.length == 0) {
			            return "";
			        }
			        String pre = strs[0];
			        for (int i = 1; i < strs.length; i++) {
			            while (strs[i].indexOf(pre) != 0) {
			                pre = pre.substring(0, pre.length() - 1);
			            }
			        }
			        return pre;
			    }
			}
			//no indexOf method
			public class Solution {
			    public String longestCommonPrefix(String[] strs) {
			        if (strs == null || strs.length == 0) {
			            return "";
			        }
			        for (int i = 0; i < strs[0].length(); i++) {
			            char c = strs[0].charAt(i);
			            for (int j = 1; j < strs.length; j++) {
			                if (i == strs[j].length() || strs[j].charAt(i) != c) {
			                    return strs[0].substring(0, i);
			                }
			            }
			        }
			        return strs[0];
			    }
			}
		2.6 Longest Consecutive Sequence
			/*
				We will use HashMap. The key thing is to keep track of the sequence length and store that in the boundary points of the sequence. 
				For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.

				Whenever a new element n is inserted into the map, do two things:

				1) See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. 
					Variables left and right will be the length of those two sequences, while 0 means there is no sequence and n will be the boundary point later. 
					Store (left + right + 1) as the associated value to key n into the map.
				2) Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length.
					Everything inside the for loop is O(1) so the total time is O(n). Please comment if you see something wrong. Thanks.
			*/
			public class Solution {
			    public int longestConsecutive(int[] nums) {
			        int res = 0;
			        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			        for (int i : nums) {
			            if (!map.containsKey(i)) {
			                int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
			                int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;
			                
			                int sum = left + right + 1;
			                map.put(i, sum);
			                
			                res = Math.max(res, sum);
			                
			                map.put(i - left, sum);
			                map.put(i + right, sum);
			            } else {
			                continue;
			            }
			        }
			        return res;
			    }
			}
		2.7 Longest Increasing Subsequence
			//O(n*n) dp
			public class Solution {
			    public int lengthOfLIS(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return 0;
			        }
			        int[] dp = new int[nums.length];
			        int max = 0;
			        Arrays.fill(dp, 1);
			        for (int i = 0; i < nums.length; i++) {
			            for (int j = 0; j < i; j++) {
			                if (nums[i] > nums[j]) {
			                    dp[i] = Math.max(dp[j] + 1, dp[i]);
			                }
			            }
			            max = Math.max(max, dp[i]);
			        }
			        return max;
			    }
			}
			//O(n),binarySearch and Dp
			/*
				The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, f
				or all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. Having this info,
				 for each new number we iterate to, we can determine the longest subsequence where it can be appended using binary search. 
			*/
			public class Solution {
			    public int lengthOfLIS(int[] nums) {
			        int[] dp = new int[nums.length];
			        int len = 0;
			        for (int num : nums) {
			            int i = binarySearch(dp, 0, len, num);
			            dp[i] = num;
			            if (i == len) {
			                len++;
			            }
			        }
			        return len;
			    }
			    private int binarySearch(int[] nums, int start, int end, int target) {
			        while (start < end) {
			            int mid = start + (end - start)/2;
			            if (nums[mid] >= target) {
			                end = mid;
			            } else {
			                start = mid + 1;
			            }
			        }
			        return end;
			    }
			}

3. Anagram Problem
		3.1 Valid Anagram
			public class Solution {
			    public boolean isAnagram(String s, String t) {
			        int[] arr = new int[26];
			        for (char c : s.toCharArray()) {
			            arr[c - 'a'] += 1;
			        }
			        for (char c : t.toCharArray()) {
			            arr[c - 'a'] -= 1;
			        }
			        for (int value : arr) {
			            if (value >= 1 || value < 0) {
			                return false;
			            }
			        }
			        return true;
			    }
			}
		3.2 Group Anagrams
			/*
				For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
				Return:
				[
				  ["ate", "eat","tea"],
				  ["nat","tan"],
				  ["bat"]
				]
			*/
			public class Solution {
			    public List<List<String>> groupAnagrams(String[] strs) {
			        List<List<String>> res = new ArrayList<>();
			        HashMap<String, List<String>> map = new HashMap<>();
			        for (String str : strs) {
			            char[] wordArr = str.toCharArray();
			            Arrays.sort(wordArr);
			            String anagram = String.valueOf(wordArr);
			            if (!map.containsKey(anagram)) {
			                map.put(anagram, new ArrayList<String>());
			            } 
			            map.get(anagram).add(str);
			        }
			        for (List<String> item : map.values()) {
			            Collections.sort(item);
			            res.add(item);
			        }
			        return res;
			    }

			}
4. Palindrome Problem
		4.1 Valid Palindrome
			/*
				Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
				For example,
				"A man, a plan, a canal: Panama" is a palindrome.
				"race a car" is not a palindrome.
			*/
			public class Solution {
			    public boolean isPalindrome(String s) {
			        int start = 0;
			        int end = s.length() - 1;
			        while (start < end) {
			            if(isValid(s.charAt(start)) && isValid(s.charAt(end))) {
			                if (isSame(s.charAt(start), s.charAt(end))) {
			                    start++;
			                    end--;
			                    continue;
			                } else {
			                    return false;
			                }
			            } 
			            if (!isValid(s.charAt(start))) {
			                start++;
			            }
			            if (!isValid(s.charAt(end))) {
			                end--;
			            }
			        }
			        return true;
			    }
			    public boPHolean isValid(char c) {
			        if (c != ' ' && ((c <= 'z' && c >= 'a') || (c <= '9' && c >= '0') || (c <= 'Z' && c >= 'A'))) {
			            return true;
			        }
			        return false;
			    }
			    public boolean isSame(char c1, char c2) {
			        if (c1 >= 'A' && c1 <= 'Z') {
			            c1 = (char) (c1 - 'A' + 'a');
			        }
			        if (c2 >= 'A' && c2 <= 'Z') {
			            c2 = (char) (c2 - 'A' + 'a');
			        }
			        return c1 == c2;
			    }
			}

		4.2 Palindrome Permutation
			/*
				Given a string, determine if a permutation of the string could form a palindrome.
				For example,
				"code" -> False, "aab" -> True, "carerac" -> True.
			*/
			public class Solution {
			    public boolean canPermutePalindrome(String s) {
			        HashSet<Character> set = new HashSet<>();
			        for (char c : s.toCharArray()) {
			            if (set.contains(c)) {
			                set.remove(c);
			            } else {
			                set.add(c);
			            }
			        }
			        return set.size() <= 1;
			    }
			}
		4.3 Palindrome Partitioning I
			/*
				Given a string s, partition s such that every substring of the partition is a palindrome.
				Return all possible palindrome partitioning of s.
				For example, given s = "aab",
				Return
				  [
				    ["aa","b"],
				    ["a","a","b"]
				  ]
			*/
			public class Solution {
			    public List<List<String>> partition(String s) {
			        List<List<String>> res =new ArrayList<>();
			        if (s == null || s.length() == 0) {
			            return res;
			        }
			        List<String> item = new ArrayList<>();
			        helper(res, item, s, 0);
			        return res;
			    }
			    public void helper(List<List<String>> res, List<String> item, String s, int start) {
			        if (start == s.length()) {
			            res.add(new ArrayList<>(item));
			            return;
			        }
			        for (int i = start; i < s.length(); i++) {
			            String str = s.substring(start, i + 1);
			            if (isValid(str)) {
			                item.add(str);
			                helper(res, item, s, i + 1);
			                item.remove(item.size() - 1);
			            }
			        }
			    }
			    public boolean isValid(String s) {
			        int i = 0;
			        int j = s.length() - 1;
			        while (i < j) {
			            if (s.charAt(i++) != s.charAt(j--)) {
			                return false;
			            }
			        }
			        return true;
			    }
			}
		4.4 Palindrome Permutation II
			public class Solution {
			    public List<String> generatePalindromes(String s) {
			        List<String> res =new ArrayList<>();
			        if (s == null || s.length() == 0) {
			            return res;
			        }
			        int odd = 0;
			        String mid = "";
			        List<Character> item = new ArrayList<>();
			        HashMap<Character, Integer> map = new HashMap<>();
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (map.containsKey(c)) {
			                map.put(c, map.get(c) + 1);
			            } else {
			                map.put(c, 1);
			            }
			            odd += map.get(c) % 2 != 0 ? 1 : -1;
			        }
			        if (odd > 1) {
			            return res;
			        }
			        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			            char key = entry.getKey();
			            int val = entry.getValue();
			            
			            if (val %2 != 0) {
			                mid += key;
			            }
			            for (int i = 0; i < val / 2; i++) {
			                item.add(key);
			            }
			        }
			        dfs(res, item, mid, new boolean[item.size()], new StringBuilder());
			        return res;
			    }
			    public void dfs(List<String> res, List<Character> item, String mid, boolean[] visited, StringBuilder sb) {
			        if (sb.length() == item.size()) {
			            res.add(sb.toString() + mid + sb.reverse().toString());
			            sb.reverse();
			            return;
			        }
			        for (int i = 0; i < item.size(); i++) {
			            if (i != 0 && item.get(i) == item.get(i - 1) && !visited[i - 1]) {
			                continue;
			            }
			            if (!visited[i]) {
			                visited[i] = true;
			                sb.append(item.get(i));
			                dfs(res, item, mid, visited, sb);
			                visited[i] = false;
			                sb.deleteCharAt(sb.length() - 1);
			            }
			        }
			    }
			}
		4.5 Shortest Palindrome
			/*
				Given "aacecaaa", return "aaacecaaa".
				Given "abcd", return "dcbabcd".
			*/
			public class Solution {
			    public String shortestPalindrome(String s) {
			        if (s.length() <= 1) {
			            return s;
			        }
			        String cur = s + " " + new StringBuilder(s).reverse().toString();
			        int len = cur.length();
			        int[] next = new int[len];
			        int k = -1;
			        int j = 0;
			        next[0] = -1;
			        while (j < len - 1) {
			            if (k == -1 || cur.charAt(k) == cur.charAt(j)) {
			                next[++j] = ++k;
			            } else {
			                k = next[k];
			            }
			        }
			        return new StringBuilder(s.substring(next[cur.length() - 1] + 1)).reverse().toString() + s;
			    }
			}

5. String And Word Modification Problem
		5.1 Reverse Word In A String I & II
			//Using split()
			public class Solution {
				//Using split(), Reverse Word In A String I
				public String reverseWords(String s) {
			        if (s == null || s.length() == 0) {
			            return "";
			        }
			        String[] strArr = s.split(" ");
			        if (strArr.length == 0) {
			            return "";
			        }
			        StringBuilder sb = new StringBuilder();
			        for (int i = strArr.length - 1; i >= 0; i--) {
			            if (!strArr[i].equals("")) {
			                sb.append(strArr[i]).append(" ");
			            }
			        }
			        return sb.toString().trim();
			    }
			    //No using split()
			    public String reverseWords(String s) {
			        StringBuilder res = new StringBuilder();
			        for (int start = s.length() - 1; start >= 0; start--) {
			            if (s.charAt(start) == ' ') {
			                continue;
			            }
			            int end = start;
			            while (start >= 0 && s.charAt(start) != ' ') {
			                start--;
			            }
			            res.append(s.substring(start + 1, end + 1)).append(' ');
			        }
			        return res.toString().trim();
			    }
			}
			//Reverse Word In A String II
			public class Solution {
				public class Solution {
				    public void reverseWords(char[] s) {
				        reverse(s, 0, s.length - 1);
				        for (int i = 0, j = 0; j < s.length; j++) {
				            if (s[j] == ' ') {
				                reverse(s, i, j - 1);
				                i = j + 1;
				            }
				            if (j == s.length - 1) {
				                reverse(s, i, j);
				            }
				        }
				    }
				    public void reverse(char[] s, int start, int end) {
				        while (start < end) {
				            swap(s, start++, end--);
				        }
				    }
				    public void swap(char[] s, int i, int j) {
				        char temp = s[i];
				        s[i] = s[j];
				        s[j] = temp;
				    }
				}
			}

		5.2 Length Of Last Word
			/*
				Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
				If the last word does not exist, return 0.
				Note: A word is defined as a character sequence consists of non-space characters only.
			*/
			public class Solution {
			    public int lengthOfLastWord(String s) {
			        int end  = s.length() - 1;
			        while (end >= 0 && s.charAt(end) == ' ') {
			            end--;
			        }
			        int start = end;
			        while (start >= 0 && s.charAt(start) != ' ') {
			            start--;
			        }
			        return end - start;
			    }
			}
		5.3 Encode And Decode Strings
			/*
				encode: len + '/' + s
				decode: 1. Using indexOf('/', i) to find the slash position
						2. slash 
			*/
			public class Codec {
			    // Encodes a list of strings to a single string.
			    public String encode(List<String> strs) {
			        StringBuilder sb = new StringBuilder();
			        for (String s : strs) {
			            sb.append(s.length()).append('/').append(s);
			        }
			        return sb.toString();
			    }
			    // Decodes a single string to a list of strings.
			    public List<String> decode(String s) {
			        List<String> res = new ArrayList<>();
			        int i = 0;
			        while (i < s.length()) {
			            int slash = s.indexOf('/', i);//这里的indexOf是return从下标i开始，第一个遇见的 slash的下标，并不是指固定位置上有没有
			            int len = Integer.valueOf(s.substring(i, slash));
			            res.add(s.substring(slash + 1, slash + len + 1));
			            i = slash + len + 1;
			        }
			        return res;
			    }
			}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));

6. File Read And Write
		6.1 Read N Characters Given Read4 I
			public class Solution extends Reader4 {
			    /**
			     * @param buf Destination buffer
			     * @param n   Maximum number of characters to read
			     * @return    The number of characters read
			     */
			    public int read(char[] buf, int n) {
			        char[] buffer = new char[4];
			        int readBytes = 0;
			        int bytes = 0;
			        boolean lessthan4 = false;
			        while (!lessthan4 && readBytes < n) {
			            int size = read4(buffer);
			            if (size < 4) {
			                lessthan4 = true;
			            }
			            bytes = Math.min(n - readBytes, size);
			            for (int i = 0; i < bytes; i++) {
			                buf[readBytes + i] = buffer[i];
			            }
			            readBytes += bytes;
			        }
			        return readBytes;
			    }
    		}
		6.2 Read N Characters Given Read4 II
			public class Solution extends Reader4 {
			    /**
			     * @param buf Destination buffer
			     * @param n   Maximum number of characters to read
			     * @return    The number of characters read
			     */
			    private char[] buffer = new char[4];
			    int offset = 0;
			    int bufsize = 0;
			    public int read(char[] buf, int n) {
			        int readBytes = 0;
			        boolean lessthan4 = false;
			        int bytes = 0;
			        while (!lessthan4 && readBytes < n) {
			            if (bufsize == 0) {
			                bufsize = read4(buffer);
			                if (bufsize < 4) {
			                	lessthan4 = true;
			                }
			            }
			            
			            bytes = Math.min(n - readBytes, bufsize);
			            for (int i = 0; i < bytes; i++) {
			                buf[readBytes + i] = buffer[offset + i];
			            }
			            offset = (offset + bytes) % 4;
			            bufsize -= bytes;
			            readBytes += bytes;
			        }
			        return readBytes;
			    }
			}



7. Word Distance Problem
		7.1 Edit Distance
			/*
				Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
			*/
			//2D DP
			public class Solution {
			    public int minDistance(String word1, String word2) {
			        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
			        for (int i = 0; i <= word1.length(); i++) {
			            dp[i][0] = i;
			        }
			        for (int i = 0; i <= word2.length(); i++) {
			            dp[0][i] = i;
			        }
			        for (int i = 1; i <= word1.length(); i++) {
			            for (int j = 1; j <= word2.length(); j++) {
			                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
			                    int delete = dp[i][j - 1] + 1;
			                    int insert = dp[i - 1][j] + 1;
			                    int replace = dp[i - 1][j - 1] + 1;
			                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
			                } else {
			                    dp[i][j] = dp[i - 1][j - 1];
			                }
			            }
			        }
			        return dp[word1.length()][word2.length()];
			    }
			}
			//1D DP
			public class Solution {
			    public int minDistance(String word1, String word2) {
			        int[] dp = new int[word2.length() + 1];
			        for (int i = 0; i <= word2.length(); ++i) {
			        	dp[i] = i;
			        }
			        for (int i = 1; i <= word1.length(); ++i) {
			            int pre = dp[0];
			            dp[0] = i;
			            for (int j = 1; j <= word2.length(); ++j) {
			                int temp = dp[j];
			                dp[j] = Math.min(dp[j - 1], dp[j]) + 1;
			                dp[j] = Math.min(dp[j], pre + (word1.charAt(i -1) == word2.charAt(j - 1) ? 0: 1));
			                pre = temp;
			            }
			        }
			        return dp[word2.length()];
			    }
			}
		7.2 One Distance
			/*
				Given two strings S and T, determine if they are both one edit distance apart.
			*/
			public class Solution {
			    public boolean isOneEditDistance(String s, String t) {
			        int m = s.length();
			        int n = t.length();
			        if (m > n) {
			            return isOneEditDistance(t, s);
			        }
			        if (m == n) {
			            int count = 0;
			            for (int i = 0; i < m; i++) {
			                if (s.charAt(i) != t.charAt(i)) {
			                    count++;
			                }
			            }
			            return count == 1;
			        }
			        if (m == n - 1) {
			            for (int i = 0; i < m; i++) {
			                if (s.charAt(i) != t.charAt(i)) {
			                    return s.substring(i).equals(t.substring(i + 1));
			                }
			            }
			            return true;
			        }
			        return false;
			    }
			}


8. String Matching
		8.1 Basic Matching
			8.1.1 Implement StrStr()
				//Solution1: Brute Force
				public class Solution {
				    public int strStr(String haystack, String needle) {
				        if (needle == null || needle.length() == 0) {
				            return 0;
				        }
				        if (haystack == null || haystack.length() == 0) {
				            return -1;
				        }
				        int i, j;
				        for (i = 0; i <= haystack.length() - needle.length(); i++) {
				            for (j = 0; j < needle.length(); j++) {
				                if (haystack.charAt(i + j) != needle.charAt(j)) {
				                    break;
				                }
				                if (j == needle.length() - 1) {
				                    return i;
				                }
				            }
				        }
				        return -1;
				    }
				}
			8.1.2 Implement StrStr() By Kmp
				public class Solution {
					public int strStr(String haystack, String needle) {
				        if (needle == null || needle.length() == 0) {
				            return 0;
				        }
				        if (haystack == null || haystack.length() == 0) {
				            return -1;
				        }
				        int[] next = new int[needle.length()];
				        setNext(next, needle);
				        int i = 0;
				        int j = 0;
				        while (i < haystack.length()) {
				            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
				                i++;
				                j++;
				            } else {
				                j = next[j];
				            }
				            if (j == needle.length()) {
				                return i - needle.length();
				            }
				        }
				        return -1;
				    }
				    public void setNext(int[] next, String pattern) {
				        char[] s = pattern.toCharArray();
				        int len = pattern.length();
				        int k = -1;
				        int j = 0;
				        next[0] = -1;
				        while (j < s.length - 1) {
				            if (k == -1 || s[k] == s[j]) {
				                next[++j] = ++k;
				            } else {
				                k = next[k];
				            }
				        }
				    } 
				}


