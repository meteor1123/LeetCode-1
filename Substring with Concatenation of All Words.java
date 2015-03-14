/*
	Substring with Concatenation of All Words 
	You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

	For example, given:
	S: "barfoothefoobarman"
	L: ["foo", "bar"]

	You should return the indices: [0,9].
	(order does not matter).

	必须要连续的相等，但是单词的顺序可以不同
*/

/*
	Solution: 
		1. 设置两个HashMap， 一个map用来存放字典L 以及L 中单词的个数，一个curMap 来存放每次循环时的单词的情况
		2. 两层for loop， 外层按照一个单词的长度进行遍历，每次+1，内层遍历S字符串，每次按照wordLen的长度进行遍历。
		   这样可以遍历所有清空
		3. 设置一个count，计算匹配到的单词个数，如果等于字典的size，则匹配成功。
		   设置窗口坐起点left 
		4. 内层循环中，
				取一个 wordLen长度的substring str，先判断是否在字典map中，
					   如果不在则窗口右移，curMap清空，count清零;

					   如果在则判断是否在curMap中存在，然后添加至curMap
					   	  继续判断curMap中str的个数以及Map中的个数，如果cur <= map ,则意味找到一个匹配单词，count++
															   如果cur > map, 则意味找到的单词虽然在字典中，但是整体来看，该窗口已经有了冗余的单词
															   				  需要将窗口的左起点右移。
															   				  此时curMap将不停的将substring(left, left + wordLen) 的个数-1，直到，里面不在有冗余的单词为止


*/
	public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (S == null || S.length() == 0 || L == null || L.length == 0)
            return res;
        //每个单词的长度相等
        int wordLen = L[0].length();

        //将需要匹配的字典里的单词以及对应的个数存入hashmap
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < L.length; i++) {
        	//一个单词出现多次的情况
            if (map.containsKey(L[i]))
                map.put(L[i], map.get(L[i]) + 1);
            else 
                map.put(L[i], 1);
        }
        
        for (int i = 0; i < wordLen; i++) {

        	//curMap用来保存一次内层循环中，匹配到的单词，以及单词出现次数，和map进行比对
            HashMap<String, Integer> curMap = new HashMap<String, Integer>();
            //count 代表匹配的单词的个数，如果等于字典的长度则匹配成功
            int count = 0;

            //窗口的左起点
            int left = i;

            //每次+一个单词的长度
            for (int j = i; j <= S.length() - wordLen; j += wordLen) {
                String str = S.substring(j, j + wordLen);
                //map中已拥有此单词
                if (map.containsKey(str)) {
                	//假如curMap中也有，则+1
                    if (curMap.containsKey(str))
                        curMap.put(str, curMap.get(str) + 1);
                    else 
                        curMap.put(str, 1);

                    //str这个单词出现的次数如果小于字典的次数，则count++
                    if (curMap.get(str) <= map.get(str))
                        count++;
                    // 否则的话，需要将左窗口的起点往右移动，并将最左的单词的出现次数在curMap中减1，
                    // 在每次往右移的过程中需要一次check右移后的字符在字典里的次数和curMap里的次数，如果小于字典里的 就--
                    // 比如 aaabbbcccbbb     {bbb,ccc,}
                    else {
                        while (curMap.get(str) > map.get(str)) {
                            String temp = S.substring(left, left + wordLen);
                            if (curMap.containsKey(temp)) {
                                curMap.put(temp, curMap.get(temp) - 1);
                                if (curMap.get(temp) < map.get(temp))
                                    count--;
                            }
                            left += wordLen;
                        }
                    }

                    //count 等于L.length 找到一个匹配字符串
                    if (count == L.length) {
                    	//将起点位置添加至结果
                        res.add(left);

                        //将窗口右移一个wordLen长度，为下一次匹配准备
                        String temp = S.substring(left, left + wordLen);
                        if (curMap.containsKey(temp))
                            curMap.put(temp, curMap.get(temp) - 1);
                        count--;
                        left += wordLen;
                    }
                //map里直接没有该字符，此时需要将curMap清空， count置0，再将左窗口 右移至右窗口的位置
                } else {
                    curMap.clear();
                    count = 0;
                    left = j + wordLen;//j是这个字典里没有的单词的位置，因此需要将left右移 wordLen
                }
            }
        }
        return res;
    }