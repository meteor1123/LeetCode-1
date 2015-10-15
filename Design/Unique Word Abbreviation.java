/*
	Unique Word Abbreviation
	An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

	a) it                      --> it    (no abbreviation)

	     1
	b) d|o|g                   --> d1g

	              1    1  1
	     1---5----0----5--8
	c) i|nternationalizatio|n  --> i18n

	              1
	     1---5----0
	d) l|ocalizatio|n          --> l10n
	Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

	Example: 
	Given dictionary = [ "deer", "door", "cake", "card" ]

	isUnique("dear") -> false
	isUnique("cart") -> true
	isUnique("cane") -> false
	isUnique("make") -> true
*/
public class ValidWordAbbr {
    private HashMap<String, ArrayList<String>> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            String abb = generateAbb(s);
            if (!map.containsKey(abb)) {
                map.put(abb, new ArrayList<>());
            }
            map.get(abb).add(s);
        }
    }
    public boolean isUnique(String word) {
        String abb = generateAbb(word);
        if (!map.containsKey(abb)) {
            return true;
        } else if (map.get(abb).contains(word) && map.get(abb).size() <= 1) {
            return true;
        } else {
            return false;
        }
    }
    public String generateAbb(String word) {
        int len = word.length();
        if (len <= 2) {
            return word;
        } else {
            StringBuilder abbreviation = new StringBuilder().append(word.charAt(0)).append(len - 2).append(word.charAt(len - 1));
            return abbreviation.toString();
        }
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
