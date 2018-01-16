/*
	Group Anagrams
	Given an array of strings, group anagrams together.

	For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
	Return:

	[
	  ["ate", "eat","tea"],
	  ["nat","tan"],
	  ["bat"]
	]
	Note:
	For the return value, each inner list's elements must follow the lexicographic order.
	All inputs will be in lower-case.
	Update (2015-08-09):
	The signature of the function had been updated to return list<list<string>> instead of list<string>, as suggested here. If you still see your function signature return a list<string>, please click the reload button  to reset your code definition.
*/

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String sortS = new String(arr);
            if (!map.containsKey(sortS)) {
                map.put(sortS, new ArrayList<>());
            } 
            map.get(sortS).add(s);
        }
        
        for (String s : map.keySet()) {
            List<String> item = new ArrayList<>();
            item = map.get(s);
            Collections.sort(item);
            res.add(item);
        }
        return res;
    }
}