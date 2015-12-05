/*
	HashTable
*/




1. String And Word Problem
		1.1 Palindrome Permutation
			/*
				Given a string, determine if a permutation of the string could form a palindrome.
				For example,
				"code" -> False, "aab" -> True, "carerac" -> True.
			*/
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
		1.2 Valid Anagram
			/*
				For example,
					s = "anagram", t = "nagaram", return true.
					s = "rat", t = "car", return false.

				Note:
					You may assume the string contains only lowercase alphabets.
			*/
				public class Solution {
				    public boolean isAnagram(String s, String t) {
				        if (s.length() != t.length()) {
				            return false;
				        }
				        int[] arr = new int[26];
				        for (int i = 0; i < s.length(); i++) {
				            arr[s.charAt(i) - 'a']++;
				            arr[t.charAt(i) - 'a']--;
				        }
				        for (int value : arr) {
				            if (value != 0) {
				                return false;
				            }
				        }
				        return true;
				    }
				}

		1.2 Group Shifted Strings
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
		1.3 Shortest Word Distance II
			/*
				For example,
					Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

					Given word1 = “coding”, word2 = “practice”, return 3.
					Given word1 = "makes", word2 = "coding", return 1.
				Note:
					You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
					Call mutiple time
			*/
			public class WordDistance {
			    private HashMap<String, ArrayList<Integer>> map;
			    public WordDistance(String[] words) {
			        map = new HashMap<String, ArrayList<Integer>>();
			        for (int i = 0; i < words.length; i++) {
			            if (!map.containsKey(words[i])) {
			                map.put(words[i], new ArrayList<>());
			            }
			            map.get(words[i]).add(i);
			        }
			    }
			    public int shortest(String word1, String word2) {
			        List<Integer> list1 = map.get(word1);
			        List<Integer> list2 = map.get(word2);
			        int minLen = Integer.MAX_VALUE;
			        int i = 0;
			        int j = 0;
			        while (i < list1.size() && j < list2.size()) {
			            minLen = Math.min(minLen, Math.abs(list1.get(i) - list2.get(j)));
			            if (list1.get(i) > list2.get(j)) {
			                j++;
			            } else {
			                i++;
			            }
			        }
			        return minLen;
			    }
			}

2. Array Problem
		2.1 Duplicate Problem
			2.1.1 Contains Duplicate I
				public class Solution {
				    public boolean containsDuplicate(int[] nums) {
				        if (nums.length == 0 || nums == null) {
				            return false;
				        }
				        HashSet<Integer> set = new HashSet<Integer>();
				        for (int i : nums) {
				            if (set.contains(i)) {
				                return true;
				            } else {
				                set.add(i);
				            }
				        }
				        return false;
				    }
				}
			2.1.2 Contains Duplicate II
				/*
					Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] 
					and the difference between i and j is at most k.
				*/
				public class Solution {
				    public boolean containsNearbyDuplicate(int[] nums, int k) {
				        if (nums == null || nums.length <= 1) {
				            return false;
				        }
				        HashMap<Integer, Integer> map = new HashMap<>();
				        for (int i = 0; i < nums.length; i++) {
				            if (map.containsKey(nums[i])) {
				                if (i - map.get(nums[i]) <= k) {
				                    return true;
				                }
				            }
				            map.put(nums[i], i);
				        }
				        return false;
				    }
				}
			2.1.3 Contains Duplicate III
				/*
					Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t 
					and the difference between i and j is at most k.
				*/
				public class Solution {
				    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
				        if (t < 0) {
				            return false;
				        }
				        HashMap<Integer, Integer> map = new HashMap<>();//key存储的是nums[i]的值，value是下标
				        t++;
				        for (int i = 0; i < nums.length; i++) {
				            //距离只要超过k就移除最左边界限的数
				            if (i > k) {
				                map.remove(getID(nums[i - k - 1], t));
				            }
				            int m = getID(nums[i], t);
				            //只要存在duplicate则一定会在下标范围k以内，因为上一句已确保
				            if (map.containsKey(m)) {
				                return true;
				            }
				            if (map.containsKey(m - 1) && Math.abs(nums[i] - map.get(m - 1)) < t) {
				                return true;
				            }
				            if (map.containsKey(m + 1) && Math.abs(nums[i] - map.get(m + 1)) < t) {
				                return true;
				            }
				            map.put(m, nums[i]);
				        }
				        return false;
				    }
				    //ID是根据数组的值计算bucket，而不是下标！
				    private int getID(int i, int t) {
				        return i < 0 ? (i + 1)/t - 1 : i / t;
				        //为什么要这么做？因为在java中 -3/5 == 0 而不是-1
				        /*
				            比如t = 4， 则 （-5，-4， -3， -2， -1） 在bucket为 -1的桶里， 
				                         （0， 1， 2， 3， 4）在bucket为0的桶里， 
				                          （-10， -9， -8， -7， -6） 在bucket为-2 的桶里，   i < 0 == (i + 1) / t - 1, 解决负数时的索引问题
				        */
				    }
				}



3. Math Problem
	3.1	Happy Number
		/*
			A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
			Example: 19 is a happy number
				12 + 92 = 82
				82 + 22 = 68
				62 + 82 = 100
				12 + 02 + 02 = 1
		*/	
		public class Solution {
		    public boolean isHappy(int n) {
		        HashSet<Integer> set = new HashSet<>();
		        int sum = 0;
		        int remain = 0;
		        while (set.add(n)) {
		            sum = 0;
		            while (n > 0) {
		                remain = n % 10;
		                sum += remain * remain;
		                n = n / 10;
		            }
		            if (sum == 1) {
		                return true;
		            } else {
		                n = sum;
		            }
		        }
		        return false;
		    }
		}
	3.2 Strobogrammatic Number I
		/*
			A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
			Write a function to determine if a number is strobogrammatic. The number is represented as a string.
			For example, the numbers "69", "88", and "818" are all strobogrammatic.
		*/
			public class Solution {
			    public boolean isStrobogrammatic(String num) {
			        if (num == null || num.length() == 0) {
			            return false;
			        }
			        HashMap<Character, Character> map = new HashMap<>();
			        map.put('6', '9');
			        map.put('9', '6');
			        map.put('8', '8');
			        map.put('0', '0');
			        map.put('1', '1');
			        int start = 0;
			        int end = num.length() - 1;
			        while (start <= end) {
			            char c1 = num.charAt(start);
			            char c2 = num.charAt(end);
			            if (!map.containsKey(c1) || !map.containsKey(c2)) {
			                return false;
			            } else if (map.get(c1) == c2) {
			                start++;
			                end--;
			            } else {
			                return false;
			            }
			        }
			        return true;
			    }
			}





















5. Appendix Implement HashTable
public class HashTable {
    public Bucket[] buckets;
    public int entryNum;
    public static final int DEFAULT_SIZE = 2029;
    /**
     * default constructor with size 2029
     */
    public HashTable(){
    	buckets = new Bucket[DEFAULT_SIZE];
    	entryNum = 0;
    }
	
    /**
     * constructor for hash table with a given size
     * @param size
     */
    public HashTable(int size){
    	buckets = new Bucket[size];
    	entryNum = 0;
    }
    
    /**
     * hash function using double hashing
     * for the collision resolution function, we choose
     * hash_2(k) = R - (k mod R) 
     * where R is a prime number that is smaller than the size of the table
     * since we know the size of hash table will grow larger than the default size
     * as we keep inserting, we can choose R to be 2027 
     * 
     * We choose this collision resolution function because it never evaluates to zero and 
     * all cells in the table can be probed.
     * This function is also easy and quick to compute and achieves even distribution
     * Also, the lecture slide says it is a popular hash function for double addressing
     * @param b
     * @param j
     * @return value of hash function
     */
    public int hashFunction(Bucket b, int j){
    	int i = b.getKey();
    	int m = buckets.length;
    	int hash_1 = i % m;
    	int hash_2 = 2027 - (i % 2027);
    	return (hash_1 + j*hash_2) % m;
    }
    
    /**
     * insert a process as a bucket
     * @param b
     * @return index of the inserted item
     *         -1 if it is a duplicate
     */
    public int insert(Bucket b){
    	//if table not large enough, do rehasing
    	if(1.0*entryNum/buckets.length >= 0.5){
    		rehash();
    	}
    
    	int i = 0;
    	do{
    		int j = hashFunction(b,i);
    		//find duplicate
    		if(buckets[j] != null){
    			if(b.getKey() == buckets[j].getKey()){
    				return -1;
    			}
    		}
    		
    		//find right position to insert
    		if(buckets[j]==null || buckets[j].getLive()==false){
    			buckets[j] = b;
    			entryNum ++;
    			return j;
    		}
    		//keep probing
    		else{
    			i ++;
    		}
    	}while(i!=buckets.length-1);
    	return -1;
    }
    
    /**
     * remove process from hash table
     * @param b
     */
    public void remove(Bucket b){
    	//find the index of the process to be removed
    	int index = findIndex(b);
    	//set the bucket to dead
    	if(isLive(index)){
    		buckets[index].setLive(false);
    		entryNum --;
    	}
    }
    
    
    /**
     * search for a process in the hash table
     * @param b
     * @return index of process to be searched and -1 otherwise
     */
    public int search(Bucket b){
    	int i = 0;
    	int j = hashFunction(b,i);
    	while(buckets[j]!=null || i!=buckets.length-1){
    		if(b.equals(buckets[j])){
    			return j;
    		}else{
    			i ++;
    			j = hashFunction(b,i);
    		}	
    	}
    	return -1;
    }
    
   
    /**
     * find the index of a given bucket
     * @param b
     * @return index of the given bucket
     */
    public int findIndex(Bucket b){
    	int i = 0;
  
    	int curr = hashFunction(b,i);
    	while(buckets[curr]!=null & !b.equals(buckets[curr])){
    		i ++;
    		curr = hashFunction(b,i);
    	}
    	return curr;
    }
    

    
    /**
     * check if the bucket is live
     * @param i
     * @return true if the bucket is live and false otherwise
     */
    public boolean isLive(int i){
    	if(buckets[i]!=null && buckets[i].getLive()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * make hash table empty
     */
    public void makeEmpty(){
    	entryNum = 0;
    	for(int i=0; i<buckets.length; i++){
    		buckets[i] = null;
    	}
    }
    
    /**
     * rehashing the table
     */
    public void rehash(){
    	Bucket[] oldBuckets = buckets;
    	//enlarge the table
    	//the new size is the next prime larger than twice the old size
    	buckets = new Bucket[2*oldBuckets.length];
    	entryNum = 0;
    	//insert old buckets into the new hash table
    	for(int i=0; i<oldBuckets.length; i++){
    		if(oldBuckets[i]!=null && oldBuckets[i].getLive()){
    			insert(oldBuckets[i]);
    		}
    	}
    }
    
    
    /**
     * get the size of hash table
     * @return size of hash table
     */
    public int getSize(){
    	return entryNum;
    }
}

