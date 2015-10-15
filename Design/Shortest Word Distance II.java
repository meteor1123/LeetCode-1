/*
	Shortest Word Distance II
	This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

	Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

	For example,
	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

	Given word1 = “coding”, word2 = “practice”, return 3.
	Given word1 = "makes", word2 = "coding", return 1.

	Note:
	You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/


public class WordDistance {
    private HashMap<String, ArrayList<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            } else {
                map.get(words[i]).add(i);
            }
        }
    }

    public int shortest(String word1, String word2) {
        int minDist = Integer.MAX_VALUE;
        for (int i :  map.get(word1)) {
            for (int j : map.get(word2)) {
                minDist = Math.min(minDist, Math.abs(i - j));
            }
        }
        return minDist;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");