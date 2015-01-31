/*
	Anagrams
	Given an array of strings, return all groups of strings that are anagrams.
	Note: All inputs will be in lower-case.
	Tags:HashTable String
*/


public class Solution {
	public ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> res = new ArrayList<String>();
		if (strs == null || strs.length() == 0)
			return res;

		HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
		for (String s : strs) {
			char[] temp = s.toCharArray();
			Arrays.sort(temp);
			String tempStr = new String(temp);

			if (hm.containsKey(tempStr)) {
				if (hm.get(tempStr).size() <= 1) 
					res.add(hm.get(tempStr).get(0));
				hm.get(tempStr).add(s);
				result.add(s);
			} else {
				ArrayList<String> tempList = new ArrayList<String>()l
				tempList.add(s);
				hm.put(tempStr, tempList);
			}
		}
		return result;
	}
}