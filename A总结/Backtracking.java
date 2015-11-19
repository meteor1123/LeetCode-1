1.First Part: Introduction Of Backtracking Algorithm
	1.0 Overview
		Backtracking is a systematic way to iterate through all the possible configurations of a search space. 
		These configurations may represent as:
			Permutations（排列）: all possible arrangements of objects.
			Subsets（子集）: all possible ways of building a collection of them
			Combinations(组合）: a combination is a way of selecting items from a collection, such that the order of selection does not matter.
		Other situations may demand enumerating all spanning trees of a graph, all paths between two vertices, or all possible ways to partition vertices into color classes.
		What these problems have in common is that we must generate each one possible configuration exactly once.


		Backtrack-DFS(A, k)//Pseudocode
			if A = (a1, a2, ..., ak) is a solution, report it. 
			else
				k = k + 1 
				compute Sk 
				while Sk != ∅ do
					ak = an element in Sk 
					Sk = Sk − ak 
					Backtrack-DFS(A, k)
    1.1 Q & A
		1.1.1 Why use DFS for Backtracking problem ?
				Although a breadth-first search could also be used to enumerate solutions, a depth-first search is greatly preferred because it uses much less space. 
				The current state of a search is completely represented by the path from the root to the current search depth-first node. 
				This requires space proportional to the height of the tree.

		1.1.2 Why we dont use BFS for Backtracking problem ?
				In breadth-first search, the queue stores all the nodes at the current level, which is proportional to the width of the search tree. 
				For most interesting problems, the width of the tree grows exponentially in its height.

		1.1.3 Why Backtracking works and how?
				Backtracking ensures correctness by enumerating all possibilities. 
			   It ensures efficiency by never visiting a state more than once.

	1.2 Implementation(From Skiena The Algorithm Desigen Manual):
		//The honest working backtrack code is given below:

		public class Solution {
			private boolean finished = false;/* found all solutions yet? */

			public void backtrack(int a[], int k, data input) {
				int c[MAXCANDIDATES]; /* candidates for next position */
				int ncandidates; /* next position candidate count */
				int i; /* counter */
				
				if (is_a_solution(a, k, input)) {
					process_solution(a, k, input);
				} else {
					k = k + 1;
					construct_candidates(a, k, input, c, &ncandidates);
					for (int i = 0; i < ncandidates; i++) {
						a[k] = c[i];
						make_move(a, k, input);
						backtrack(a, k, input);
						unmake_move(a, k, input);
						if (finished) {
							return ;
						}/* terminate early */
					}
				}
			}
		}
		1.2.1 Backtracking Method Explain
			1.2.1 is_a_solution(a,k,input)
				This Boolean function tests whether the first k elements of vector a from a complete solution for the given problem. 
				The last argument, input, allows us to pass general information into the routine. We can use it to specify n---the size of a target solution. 
				This makes sense when constructing permutations or subsets of n elements, but other data may be relevant when constructing variable-sized objects such as sequences of moves in a game.
			1.2.2 construct_candidates(a, k, input, c, ncandidates) 
				This routine fills an array c with the complete set of possible candidates for the kth position of a, given the contents of the first k − 1 positions. 
				The number of candidates returned in this array is denoted by ncandidates. Again, input may be used to pass auxiliary information.
			1.2.3 process solution(a,k,input)
				This routine prints, counts, or however processes a complete solution once it is constructed.
			1.2.4 make_move(a, k, input) and unmake_move(a, k, input)
				able us to modify a data structure in response to the latest move, as well as clean up this data structure if we decide to take back the move. 
				Such a data structure could be rebuilt from scratch from the solution vector a as needed, but this is inefficient when each move involves incremental changes that can easily be undone.






2. Second Part: Problems Set
		1. Permutations 排列 //[1,2,3] have the following permutations:[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
			1.1 Permutations I, II //Permutations II 只需要加入下面2行代码
				//Recursive, the key is boolean[] array!
				public class Solution {
				    public List<List<Integer>> permute(int[] nums) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (nums == null || nums.length == 0) {
				            return res;
				        }
				        //Arrays.sort(nums); Permutations II
				        List<Integer> item = new ArrayList<>();
				        boolean[] visited = new boolean[nums.length];
				        helper(nums, res, item, visited);
				        return res;
				    }
				    public void helper(int[] nums, List<List<Integer>> res, List<Integer> item, boolean[] visited) {
				        if (item.size() == nums.length) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = 0; i < nums.length; i++) {
				        	//if (i != 0 && nums[i] == nums[i - 1] && nums[i - 1] == false) Permutations II
				            if (visited[i] == false) {
				                visited[i] = true;
				                item.add(nums[i]);
				                helper(nums, res, item, visited);
				                item.remove(item.size() - 1);
				                visited[i] = false;
				            }
				           
				        }
				    }
				}
				//Iterative
				public class Solution {
					public List<List<Integer>> permute(int[] nums) {
				        List<List<Integer>> res = new ArrayList<List<Integer>>();
				        if (nums.length == 0 || nums == null) {
				            return res;
				        }
				        // Collections.sort(nums) //Permutations II
				        List<Integer> initList = new ArrayList<>();
				        initList.add(nums[0]);
				        res.add(initList);				   
				        /*	
				    		if nums[] = {1, 2, 3}
				    		At first 			  : ans = { (1) }
				    		After second for loop : ans = { (2, 1), (1, 2)}
				    		Finaly:    			  :	ans = { (3,2,1) (3,1,2) (2,3,1) (1,3,2) (2,1,3) (1,2,3)}
				    	*/
				        for (int i = 1; i < nums.length; i++) { //遍历待插入元素
				            List<List<Integer>> tempRes = new ArrayList<>();
				            for (int j = 0; j <= i; j++) { //遍历插入位置
				                for (List<Integer> list : res) {//遍历res里的 list，取出来插入
				                    List<Integer> newList = new ArrayList<>(list);//这样是copy的用法，如果直接把newList = list,将会导致错误
				                    newList.add(j, nums[i]);
				                    // if (!tempRes.contains) {  //Permutations II 
				                    // 	tempRes.add(newList);
				                    // }
				                    tempRes.add(newList);
				                }
				            }
				            res = tempRes;
				        }
				        return res;
				    }
				}
			1.2 Permutation Sequence 
				/*
					The logic is as follows: 
						1. For n numbers, permutations can be divided into n groups with (n - 1)! elements in each group. 
						2. Thus, k / (n - 1)! is the group index among the current n (to be) sorted groups
						3. and k % (n - 1)! is the sequence number k for next iteration.    
				*/
				/*
					Example:
						1. n = 4, k = 13;
						2. 将1~4加入list中， fact = 1 * 2 * 3 * 4 = 24;
						   fact /= n 是因为，在由1，2，3，4 组成的permutation如下所示：
								   				1 + (permutations of 2, 3, 4) 
												2 + (permutations of 1, 3, 4) 
												3 + (permutations of 1, 2, 4) 
												4 + (permutations of 1, 2, 3)，
												后面三位组成的permutation数量= fact / n = 24 / 4;
												我们只需要将 k / (fact / n) ,就可以得到第一位数字的序号。
												13 / (24 / 4) = 13 / 6 = 2, 也就是取3.
						3. k--,是因为数组下标从0开始，方便计算。
						4. k %= fact 是因为， k = 13 % 6 = 1, 
						    fact = 6 / 3 = 2;
						    k / (fact / n) : 1 / (6 / 3) = 0, 我们要在剩余的3位permutation数字中确定首位的序号，首位取0，
						    3，1
						5. 如此循环直到n == 0;


				*/
				public class Solution {
					public String getPermutation(int n, int k) {
					    LinkedList<Integer> list = new LinkedList<>();
					    for (int i = 1; i <= n; i++) {
					    	list.add(i);
					    }
					    int fact = 1;
					    for (int i = 2; i <= n; i++) {
					    	fact *= i; // factorial
					    }
					    StringBuilder sb = new StringBuilder();
					    for (k--; n > 0; n--) {
					        fact /= n;
					        sb.append(list.remove(k / fact));
					        k %= fact;
					    }
				    	return sb.toString();
					}
				}
			1.3 Next Permutation
				/*
					1. find the first element which A[i] <= A[i + 1], the index i start from num.length - 2;
					2. find the smallest one which make A[j] > A[i] , j start from the index i + 1 to A.length - 1;
						(notice, i + 1 ~ A.length - 1,must be a descending order, since the step 1)
					3. swap(i, j)
					4. reverse from i + 1 to nums.length - 1;
				*/
				public class Solution {
					public void nextPermutation(int[] num) {
				        if (num == null || num.length <= 1) {
				            return;
				        }
				        int i = num.length - 2;
				        while (i >= 0 && num[i + 1] <= num[i]) {
				            i--;
				        }
				        if (i >= 0) {
				        	//the adjacent number of j
				            int j = i + 1;
				            //Why we don't need to compare the value?cause from j to length - 1, that is the descending array so the last large one is smallest one
				            while (j < num.length && num[j] > num[i]) {
				                j++;
				            }
				            j--;
				            swap(num, i, j);
				        }
				        reverse(num, i + 1, num.length - 1);
				    } 
				    public void reverse(int[] num, int i, int j) {
				        while (i < j) {
				            swap(num, i++, j--);
				        }   
				    }
				}
			1.4 Previous Permutation
				/*
					思路：和next permutation完全一致的思路。
					1. 从右往左扫，找到的第一个增长pivot，这个大的数字就是即将被换掉的位置i
					2. 从这个数字往右必然是一个递增序列，往右扫，直到找到一个数比这个pivot小而且是所有右边数字中最大的（递增序列，因此最后一个比pivot大的就是）
					3. 然后交换pivot和该数字。
					4. reverse from i + 1 ~ nums.len - 1;
				*/
				public class Solution {
					public void previousPermutation(int[] num) {
				        if (num == null || num.length <= 1) {
				            return;
				        }
				        int i = num.length - 2;
				        while (i >= 0 && num[i + 1] >= num[i]) {//只有两处区别，第一处
				            i--;
				        }
				        if (i >= 0) {
				        	//the adjacent number of j
				            int j = i + 1;
				            //Why we don't need to compare the value?cause from j to length - 1, that is the descending array so the last large one is smallest one
				            while (j < num.length && num[j] < num[i]) {//第二处
				                j++;
				            }
				            j--;
				            swap(num, i, j);
				        }
				        reverse(num, i + 1, num.length - 1);
				    } 
			1.5 Palindrome Permutation II
				/*
					Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
					For example:
						Given s = "aabb", return ["abba", "baab"].
						Given s = "abc", return [].
				*/
				/*
					关键部分： 1. 判断这个String 是否能够palindrome，这里用一个hashmap，value存每一个字符出现的次数
								字符数量为奇数的字符最多只允许一个，用odd去记录奇数字符的个数，大于1就返回空list。（
							 2. 用一个list去保存hashmap里的字符，记住只需要保存一半即可，
							 	比如 aaaa bb ccc dddd,只需要保存 aabcdd, 再对这个一半的字符串进行backtracking
							 	aabcdd + c + ddcbaa.....
				*/
				public class Solution {
				    public List<String> generatePalindromes(String s) {
				        List<String> res = new ArrayList<>();
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
				            odd += map.get(c) % 2 != 0 ? 1 : -1;//char的数量只要是偶数就会抵消
				        }
				        if (odd > 1) {
				            return res;
				        }
				        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
				            char key = entry.getKey();
				            int val = entry.getValue();
				            
				            if (val % 2 != 0) {
				                mid += key;
				            }
				            //only need to find the first half
				            for (int i = 0; i < val / 2; i++) {
				                item.add(key);
				            }
				        }
				        helper(item, mid, new boolean[item.size()], new StringBuilder(), res);
				        return res;
				    }
				    public void helper(List<Character> item, String mid, boolean[] visited, StringBuilder sb, List<String> res) {
				        //only need to find the first half
				        if (sb.length() == item.size()) {
				            res.add(sb.toString() + mid + sb.reverse().toString());
				            sb.reverse();
				            return;
				        }
				        for (int i = 0; i < item.size(); i++) {
				            if (i != 0 && item.get(i) == item.get(i - 1) && !visited[i - 1]) {//去重
				                continue;
				            }
				            //Backtracking
				            if (!visited[i]) {
				                visited[i] = true;
				                sb.append(item.get(i));
				                helper(item, mid, visited, sb, res);
				                visited[i] = false;
				                sb.deleteCharAt(sb.length() - 1);
				            }
				        }
				    }
				}
		2. Combination
			2.1 Combinations
				/*
					Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
					For example,
					If n = 4 and k = 2, a solution is:
					[
					  [2,4],
					  [3,4],
					  [2,3],
					  [1,2],
					  [1,3],
					  [1,4],
					]
				*/
				//Recursive
				public class Solution {
				    public List<List<Integer>> combine(int n, int k) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (n == 0 || k == 0 || n < k) {
				            return res;
				        }
				        List<Integer> item = new ArrayList<>();
				        helper(res, item, n, k, 1);
				        return res;
				    }
				    public void helper(List<List<Integer>> res, List<Integer> item, int n, int k, int start) {
				        if (item.size() == k) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i <= n; i++) {
				            item.add(i);
				            helper(res, item, n, k, i + 1);
				            item.remove(item.size() - 1);
				        }
				    }
				}
				//Iterative
				public class Solution {
					public List<List<Integer>> combine(int n, int k) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (k == 0 || n == 0 || k > n) {
				            return res;
				        }
				        for (int i = 1; i <= n; i++) {
				            res.add(Arrays.asList(i));
				        }
				        for (int i = 2; i <= k; i++) {
				            List<List<Integer>> tempRes = new ArrayList<>();
				            for (int j = i; j <= n; j++) {
				                for (List<Integer> item : res) {
				                    if (item.get(item.size() - 1) < j) {
				                        List<Integer> newItem = new ArrayList<>(item);
				                        newItem.add(j);
				                        tempRes.add(newItem);
				                    } 
				                }
				            }
				            res = tempRes;
				        }
				        return res;
				    }
				}
			2.2 Combination Sum I & II
				/*
					I ：candidates没有重复的数字，但是每个数字可以重复使用，因此递归下一层的时候，还是传递本身的位置
						O(k * 2^n) time.
					II：candidates有重复的数字，每个数字仅能使用一次，因此递归下一层的时候，数字从下一个开始
						O(k * 2^n) time.
				*/
				//Recursive
				public class Solution {
				    public List<List<Integer>> combinationSum(int[] candidates, int target) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (candidates == null || candidates.length == 0) {
				            return res;
				        }
				        Arrays.sort(candidates);
				        List<Integer> item = new ArrayList<>();
				        helper(candidates, res, item, target, 0);
				        return res;
				    }
				    public void helper(int[] candidates, List<List<Integer>> res, List<Integer> item, int target, int start) {
				        if (target < 0) {
				            return;
				        }
				        if (target == 0) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i < candidates.length; i++) {
				        	// if (i > start && candidates[i - 1] == candidates[i]) { ---Combination sum II
				         //        continue;
				         //    }
				            int newTarget = target - candidates[i];
				            item.add(candidates[i]);
				            helper(candidates, res, item, newTarget, i);//helper(candidates, res, item, newTarget, i + 1); ---Combination sum II
				            item.remove(item.size() - 1);
				        }
				    }
				}
				//Iterative
				/*
                    Example :{2, 3, 6, 7}
                    dp.get(i) = {candidates[j], dp.get(i - candidates[j] - 1)}
                    dp.get(0) = {[]};
                    dp.get(1) = {[2]};
                    dp.get(2) = {[3]};
                    dp.get(3) = {[2, 2]};
                    dp.get(4) = {[2, 3]};
                    dp.get(5) = {[2, 2, 2], [3, 3], [6]};
                    
                    dp.get(5) = {2 + dp.get(3)}, {3 + dp.get(2)}. {6 + dp.get(0));

                    ->  for 循环所有candidates[j], 并且使得candidates[j]要满足 candidates[j] <= item里面的第一个元素

                        dp.get(i) = {candidates[j] + dp.get(i - candidates[j] - 1) }
                */
			    public List<List<Integer>> combinationSum(int[] candidates, int target) {
			         Arrays.sort(candidates);
			         List<List<List<Integer>>> dp = new ArrayList<>();
			         for (int i = 1; i <= target; i++) {// run through all targets from 1 to target
			             List<List<Integer>> tempRes = new ArrayList<>();
			             // run through all candidates <= i
			             for (int j = 0; j < candidates.length && candidates[j] <= i; j++) {
			                 // special case when cur target is equal to cur candidate
			                 if (i == candidates[j]) {
			                     tempRes.add(Arrays.asList(candidates[j]));
			                 } else {
			                     for (List<Integer> item : dp.get(i - candidates[j] - 1)) {
			                          // if current candidate is less than the target use prev results, since we already get the result
			                         if (candidates[j] <= item.get(0)) {
			                             List<Integer> newList = new ArrayList<>();
			                             newList.add(candidates[j]);
			                             newList.addAll(item);
			                             tempRes.add(newList);
			                         }
			                     }
			                 }
			             }
			             dp.add(tempRes);
			         }
			         return dp.get(target - 1);
			    }
			2.3 Combination Sum III
				/*
					Find all possible combinations of k numbers that add up to a number n, 
					given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
						Input: 	 k = 3, n = 9
						Output:
								[[1,2,6], [1,3,5], [2,3,4]]
				*/
				public class Solution {
				    public List<List<Integer>> combinationSum3(int k, int n) {
				        List<List<Integer>> res = new ArrayList<>();
				        List<Integer> item = new ArrayList<>();
				        helper(res, item, k, n, 1);
				        return res;
				    }
				    public void helper(List<List<Integer>> res, List<Integer> item, int k, int target, int start) {
				        if (item.size() == k && target == 0) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i <= 9; i++) {
				            int newTarget = target - i;
				            item.add(i);
				            helper(res, item, k, newTarget, i + 1);
				            item.remove(item.size() - 1);
				        }
				    }
				}

			2.4 Letter Combinations Of A Phone Number
				/*
					O(n * 4^n) OR O(n * 3^p*4^q) time
				    Explanation:
				    O(n * num of sols) time
				    n is the toString() API time complexity which is the same length as input
				  
				    num of sols:
				    O(4^n)
				    n is the length of input digits
				    
				    OR O(3^p*4^q) solutions
				    p is the number of digits which has 3 corresponding letters
				    q is the number of digits which has 4 corresponding letters
			    */
				//Recursive
			    public class Solution {
				    public List<String> letterCombinations(String digits) {
				        List<String> res = new ArrayList<>();
				        if (digits == null || digits.length() == 0) {
				            return res;
				        }
				        String[] keyboard = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
				        StringBuilder sb = new StringBuilder();
				        helper(res, digits, keyboard, sb, 0);
				        return res;
				    }
				    public void helper(List<String> res, String digits, String[] keyboard, StringBuilder sb, int index) {
				        if (sb.length() == digits.length()) {
				            res.add(sb.toString());
				            return;
				        }
				        int number = digits.charAt(index) - '0';
				        for (int i = 0; i < keyboard[number].length(); i++) {
				            sb.append(keyboard[number].charAt(i));
				            helper(res, digits, keyboard, sb, index + 1);
				            sb.deleteCharAt(sb.length() - 1);
				        }
				    }
				}
				//Iterative
				public List<String> letterCombinations(String digits) {
			        List<String> res = new LinkedList<>();
			        if (digits == null || digits.length() == 0) {
			            return res;
			        }
			        String[] keyboard = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
			        res.add("");
			        for (int i = 0; i < digits.length(); i++) {
			            String letters = keyboard[digits.charAt(i) - '2'];
			            int size = res.size();
			            for (int j = 0; j < size; j++) {
			                String item = res.remove(0);
			                for (int k = 0; k < letters.length(); k++) {
			                    res.add(item + letters.charAt(k));
			                }
			            }
			        }
			        return res;
			    }
			2.5 Factor Combinations
				/*
					Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
					You may assume that n is always positive.
					Factors should be greater than 1 and less than n.
					input: 32
					output:
					[
					  [2, 16],
					  [2, 2, 8],
					  [2, 2, 2, 4],
					  [2, 2, 2, 2, 2],
					  [2, 4, 4],
					  [4, 8]
					]
				*/
				//The Key Point is using n / i to recursive, unitl n / i == 1
				public class Solution {
				    public List<List<Integer>> getFactors(int n) {
				        List<List<Integer>> res = new ArrayList<>();
				        if (n == 0) {
				            return res;
				        }
				        List<Integer> item = new ArrayList<>();
				        helper(res, item, n, 2);
				        return res;
				    }
				    public void helper(List<List<Integer>> res, List<Integer> item, int n, int start) {
				        if (n < 1) {
				            return;
				        }
				        if (n == 1 && item.size() > 1) {
				            res.add(new ArrayList<>(item));
				            return;
				        }
				        for (int i = start; i <= n; i++) {
				            if (n % i == 0) {
				                item.add(i);
				                helper(res, item, n / i, i);
				                item.remove(item.size() - 1);
				            }
				        }
				    }
				}


		4. Bits And Math Backtracking
			4.1 Gray Code
			

			
















