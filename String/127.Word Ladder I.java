/*
	Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

	Only one letter can be changed at a time
	Each intermediate word must exist in the dictionary
	
	For example,
		Given:
		start = "hit"
		end = "cog"
		dict = ["hot","dot","dog","lot","log"]
	As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	return its length 5.
*/

/*
	把每个单词作为一个node进行BFS。当取得当前字符串时，对他的每一位字符进行从a~z的替换，如果在字典里面，就入队，并将下层count++,并且为了避免环路，需把在字典里检测到的单词从字典里删除。
	这样对于当前字符串的每一位字符安装a~z替换后，在queue中的单词就作为下一层需要遍历的单词了。
	正因为BFS能够把一层所有可能性都遍历了，所以就保证了一旦找到一个单词equals（end），那么return的路径肯定是最短的。

	
*/


 public int ladderLength(String start, String end, HashSet<String> dict) {
        //corner case,notice if the length between start and end is different mean impossible to match
        if (start == null || end == null || start.length() == 0 || end.length() == 0 || start.length() != end.length())
            return 0;
        LinkedList<String> wordQueue = new LinkedList<String>();
        int level = 1;//record the length of the ladder
        int curWordNum = 1; //队列中同一层的单词的数量
        int nextWordNum = 0; //队列中下一层的单词的数量
        wordQueue.push(start);
        
        while (!wordQueue.isEmpty()) {
            curWordNum--; //每一次while循环要pop一次，则同一层需要判断的单词数量减1
            String word = wordQueue.pop(); 
            
            //遍历一个word的每一位
            for (int i = 0; i < word.length(); i++) {
                //每遍历一位就new 一个字符数组，以便进行比较
                char[] charArr = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    charArr[i] = c;
                    String temp = new String(charArr);
                    
                    if (temp.equals(end)) 
                        return level + 1;
                    if (dict.contains(temp)) {
                        nextWordNum++;
                        wordQueue.add(temp);
                        dict.remove(temp);
                    }
                }
            }
            
            //要在两个for循环之外进行判断，代表每遍历玩一个单词的所有单位不同的可能性的单词后，才对现有单词数量进行判断
            if (curWordNum == 0) {
                curWordNum = nextWordNum;
                nextWordNum = 0;
                level++;
            }
        }
        return 0;
    }