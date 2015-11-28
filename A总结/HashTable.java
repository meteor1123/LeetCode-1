/*
	HashTable
*/




1. String And Word Problem
		1.1 Group Shifted Strings
		/*
			For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
			Return:

			[
			  ["abc","bcd","xyz"],
			  ["az","ba"],
			  ["acef"],
			  ["a","z"]
			]
		*/
		// (s.charAt(i) - s.charAt(0) + 26) % 26
		public List<List<String>> groupStrings(String[] strings) {
	        List<List<String>> res = new ArrayList<>();
	        Map<String, List<String>> map = new HashMap<>();        
	        for (String s : strings) {
	            String key = "";
	            for (int i = 0; i < s.length(); i++) {
	                char c = (char)((s.charAt(i) - s.charAt(0) + 26) % 26);
	                key += c;
	            }
	            if (!map.containsKey(key)) {
	                map.put(key, new ArrayList<String>());
	            }
	            map.get(key).add(s);
	        }
	        for (List<String> item : map.values()) {
	            Collections.sort(item);
	            res.add(item);
	        }
	        return res;
	    }