/*
	Design 
*/



/*
	Java中的Iterator功能比较简单，并且只能单向移动：
　　(1) 使用方法iterator()要求容器返回一个Iterator。第一次调用Iterator的next()方法时，它返回序列的第一个元素。注意：iterator()方法是java.lang.Iterable接口,被Collection继承。
　　(2) 使用next()获得序列中的下一个元素。
　　(3) 使用hasNext()检查序列中是否还有元素。
　　(4) 使用remove()将迭代器新返回的元素删除。

	
	list l = new ArrayList();
	l.add("aa");
	l.add("bb");
	l.add("cc");
	for (Iterator iter = l.iterator(); iter.hasNext();) {
	  	String str = (String)iter.next();
	 	System.out.println(str);
	}
	/*迭代器用于while循环
	Iterator iter = l.iterator();
	while(iter.hasNext()){
		String str = (String) iter.next();
	 	System.out.println(str);
	}
*/

1. Iterator Problem
		1.1 List Iterator
			1.1.1 Zigzag Iterator
				/*
					For example, given two 1d vectors:
					v1 = [1, 2]
					v2 = [3, 4, 5, 6]
					By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

					The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

					[1,2,3]
					[4,5,6,7]
					[8,9]
					It should return [1,4,8,2,5,9,3,6,7].
				*/
				/**
				 * Your ZigzagIterator object will be instantiated and called as such:
				 * ZigzagIterator i = new ZigzagIterator(v1, v2);
				 * while (i.hasNext()) v[f()] = i.next();
				 */
				//Simple Version
				public class ZigzagIterator {
				    private Iterator<Integer> i1, i2, temp;
				    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
				        i1 = v1.iterator();
				        i2 = v2.iterator();
				    }
				    public int next() {
				        if (i1.hasNext()) {
				            temp = i1;
				            i1 = i2;
				            i2 = temp;
				        }
				        return i2.next();
				    }
				    public boolean hasNext() {
				        return i1.hasNext() || i2.hasNext();
				    }
				}

				//Can be used to k-D vector
				public class ZigzagIterator {
				    private LinkedList<Iterator> queue;
				    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
				        queue = new LinkedList<Iterator>();
				        if (!v1.isEmpty()) {
				            queue.offer(v1.iterator());
				        }
				        if (!v2.isEmpty()) {
				            queue.offer(v2.iterator());
				        }
				    }
				    public int next() {
				        Iterator head = queue.poll();// using queue, every time poll the head iterator and get the next() value, if hasNext(),and offer to the queue again
				        int res = (Integer)head.next();
				        if (head.hasNext()) {
				            queue.offer(head);
				        }
				        return res;
				    }
				    public boolean hasNext() {
				        return !queue.isEmpty();
				    }
				}
			1.1.2 Flatten 2D Vector
				/*
					Given 2d vector =
					[
					  [1,2],
					  [3],
					  [4,5,6]
					]

					By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
				*/
				public class Vector2D {
				    Iterator<List<Integer>> i;
				    Iterator<Integer> j;
				    public Vector2D(List<List<Integer>> vec2d) {
				        i = vec2d.iterator();
				    }
				    public int next() {
				        hasNext();
				        return j.next();
				    }
				    public boolean hasNext() {
				        while ((j == null || !j.hasNext()) && i.hasNext()) {
				            j = i.next().iterator();
				        }
				        return j != null && j.hasNext();
				    }
				}
		1.2 Iterator Interface
				// Java Iterator interface reference:
				// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
				class PeekingIterator implements Iterator<Integer> {
				    Integer nextVal;
				    Iterator<Integer> iter ;
					public PeekingIterator(Iterator<Integer> iterator) {
					    // initialize any member here.
					    iter = iterator;
					    if (iter.hasNext()) {
					         nextVal = iter.next();
					    }
					}
				    // Returns the next element in the iteration without advancing the iterator.
					public Integer peek() {
				        return nextVal;
					}
					// hasNext() and next() should behave the same as in the Iterator interface.
					// Override them if needed.
					@Override
					public Integer next() {
					    Integer res = nextVal;
					    nextVal = iter.hasNext() ? iter.next() : null;
					    return res;
					}
					@Override
					public boolean hasNext() {
					    return nextVal != null;
					}
				}
		1.3 Binary Search Tree Iterator
			/*
				Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
			*/
			public class BSTIterator {
				private Stack<TreeNode> stack = new Stack<TreeNode>();
				public BSTIterator(TreeNode root) {
					pushAll(root);
				}
				public boolean hasNext() {
					return !stack.isEmpty();
				}
				public int next() {
					TreeNode curNode = stack.pop();
					pushAll(curNode.right);
					return curNode.val;
				}
				private void pushAll(TreeNode node) {
					while (node != null) {
						stack.push(node);
						node = node.left;
					}
				}
			}


2. Stack And Queue Design
		2.1 Implement Queue Using Stacks
			/*
			    O(1) amortized for each operation
			    Each element only ever gets moved like that once, though, and only after we already spent time pushing it, 
			    so the overall amortized cost for each operation is O(1).
			*/
			class MyQueue {
			    Stack<Integer> input = new Stack();
			    Stack<Integer> output = new Stack();
			    // Push element x to the back of queue.
			    public void push(int x) {
			        input.push(x);
			    }

			    // Removes the element from in front of queue.
			    public void pop() {
			        peek();
			        output.pop();
			    }

			    // Get the front element.
			    public int peek() {
			        if (output.empty()) {
			            while (!input.empty()) {
			                output.push(input.pop());
			            }
			        }
			        return output.peek();
			    }
			    // Return whether the queue is empty.
			    public boolean empty() {
			        return input.isEmpty() && output.isEmpty();
			    }
			}
		2.2 Implement Stack Using Queues
			class MyStack {
			    // Push element x onto stack.
			    Queue<Integer> queue;
			    public MyStack() {
			        queue = new LinkedList<>();
			    }
			    public void push(int x) {
			        queue.offer(x);
			        for (int i = 0; i < queue.size() - 1; i++) {
			            queue.offer(queue.poll());
			        }
			    }
			    // Removes the element on top of the stack.
			    public void pop() {
			        queue.poll();
			    }
			    // Get the top element.
			    public int top() {
			        return queue.peek();
			    }
			    // Return whether the stack is empty.
			    public boolean empty() {
			        return queue.isEmpty();
			    }
			}

3. String And Word Design
		3.1 Shortest Word Distance II 
			/*
				Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
				Given word1 = “coding”, word2 = “practice”, return 3.
				Given word1 = "makes", word2 = "coding", return 1.
				Note:
				You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
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


			