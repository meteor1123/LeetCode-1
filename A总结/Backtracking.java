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
















