1. The Definition of Tree

	Root – The top node in a tree.
	Parent – The converse notion of a child.
	Siblings – Nodes with the same parent.
	Descendant – a node reachable by repeated proceeding from parent to child.
	Ancestor – a node reachable by repeated proceeding from child to parent.
	Leaf – a node with no children

	Level – The level of a node is defined by 1 + (the number of connections between the node and the root).
	Height of tree – The height of a tree is the number of edges on the longest downward path between the root and a leaf.

	Height of node – The height of a node is the number of edges on the longest downward path between that node and a leaf.
	Depth – The depth of a node is the number of edges from the node to the tree root node.

	Internal node – a node with at least one child.
	External node – a node with no children.
	Degree – number of sub trees of a node.
	Edge – connection between one node to another.
	Path – a sequence of nodes and edges connecting a node with a descendant.
	Forest – A forest is a set of n ≥ 0 disjoint trees.


1.Binary Tree Traversal Problem，树的各种遍历方法
	1.1 Preorder : root -> left -> right 中 左 右
			1.1.1 Recursive prefer
			//DFS
			public class Solution {
				public List<Integer> preorderTraversal(TreeNode root) {
			    	List<Integer> res = new ArrayList<>();
			    	preorder(res, root);
			    	return res;
			    }
			    public void preorder(List<Integer> res, TreeNode root) {
			        if (root == null) {
			            return;
			        }
			        res.add(root.val);
			        preorder(res, root.left);
			        preorder(res, root.right);
			    }
			}
			1.1.2 Iterative1 prefer
			public class Solution {
				public List<Integer> preorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        Stack<TreeNode> stack = new Stack<>();
			        while (root != null || !stack.isEmpty()) {
			            if (root != null) {
			                stack.push(root);//只要有左边的叶子节点就一直遍历入栈
			                res.add(root.val);// 跟inorder遍历的唯一区别在于， inorder中res.add(root.val)放到  遍历完最left的时候才add， 正好如何 
			                root = root.left;
			            } else {
			                root = stack.pop();//左边的遍历完了再出栈给root，将右节点的值 //加入结果
			                root = root.right;
			            }
			        }
			        return res;
			    }
			}
			1.1.3 Iterative2
			//Using stack to imitate the recursive
			public class Solution {
	    		public List<Integer> preorderTraversal(TreeNode root) {
			    	List<Integer> res = new ArrayList<>();
			    	if (root == null)
			    		return res;
			    	Stack<TreeNode> stack = new Stack<>();
			    	stack.push(root);
			    	while (!stack.isEmpty()) {
			    		TreeNode cur = stack.pop();
			    		res.add(cur.val);
			    		if (cur.right != null)
			    			stack.push(cur.right);
			    		if (cur.left != null)
			    			stack.push(cur.left);
			    	}
			    	return res;
	    		}
	    	}
	1.2 Inorder  left -> root -> right
			1.2.1 Recursive 
			public class Solution {
				public List<Integer> inorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        dfs(root, res);
			        return res;
			    }
			    public void dfs(TreeNode root, List<Integer> res) {
			        if (root == null)
			            return ;
			        dfs(root.left, res);
			        res.add(root.val);
			        dfs(root.right, res);
			    }
			}
			1.2.2 Iterative prefer
			public class Solution {
				public List<Integer> inorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        LinkedList<TreeNode> stack = new LinkedList<>();
			        while (root != null || !stack.isEmpty()) {
			            if (root != null) {
			                stack.push(root);
			                root = root.left;
			            } else {
			                root = stack.pop();
			                res.add(root.val);
			                root = root.right;
			            }
			        }
			        return res;
			    }
			}
	1.3 Postorder left -> right -> root
			1.3.1 Recursive
			public class Solution {
			    public List<Integer> postorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        dfs(root, res);
			        return res;
			    }
			    public void dfs(TreeNode root, ArrayList<Integer> res) {
			        if (root == null)
			            return ;
			        dfs(root.left, res);
			        dfs(root.right, res);
			        res.add(root.val);
			    }
			}
			1.3.2 Iterative1 prefer , real postorder
				/*
					后序遍历的情况就复杂多了。我们需要维护当前遍历的cur指针和前一个遍历的pre指针来追溯当前的情况
					注意这里是遍历的指针，并不是真正按后序访问顺序的结点）。具体分为几种情况：
						1）如果pre的左孩子或者右孩子是cur，那么说明遍历在往下走，按访问顺序继续，即如果有左孩子，则是左孩子进栈，
						   否则如果有右孩子，则是右孩子进栈，如果左右孩子都没有，则说明该结点是叶子，可以直接访问并把结点出栈了。
						2）如果反过来，cur的左孩子是pre，则说明已经在回溯往上走了，但是我们知道后序遍历要左右孩子走完才可以访问自己，
						   所以这里如果有右孩子还需要把右孩子进栈，否则说明已经到自己了，可以访问并且出栈了。
						3）如果cur的右孩子是pre，那么说明左右孩子都访问结束了，可以轮到自己了，访问并且出栈即可。
						   算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。

					下面在弹栈的时候需要分情况一下：
						1）如果当前栈顶元素的右结点存在并且还没访问过（也就是右结点不等于上一个访问结点），那么就把当前结点移到右结点继续循环；
						2）如果栈顶元素右结点是空或者已经访问过，那么说明栈顶元素的左右子树都访问完毕，应该访问自己继续回溯了。
					*/
			public class Solution {
				public List<Integer> postorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        Stack<TreeNode> stack = new Stack<>();
			        TreeNode pre = null;
			        while (root != null || !stack.isEmpty()) {
			            //往左探底
			            if (root != null) {
			                stack.push(root);
			                root = root.left;
			            } else {
			                TreeNode peekNode = stack.peek();
			                //一直往右探底
			                if (peekNode.right != null && peekNode.right != pre) {
			                    root = peekNode.right;
			                } else {
			                    //已经探底，加入这个值
			                    stack.pop();
			                    res.add(peekNode.val);
			                    pre = peekNode;
			                }
			                
			            }
			        }
			        return res;
			    }
			}
			1.3.3 Iterative2 
			/* 
			 	it was like cheating:) 
			 	Using root->right->left to do traversal just like preorder，
			 	and then reverse the result
			 	it will be left -> right -> root
			*/
			public class Solution {
			    public List<Integer> postorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        Stack<TreeNode> stack = new Stack<TreeNode>();
			        while (!stack.isEmpty() || root != null) {
			            if (root != null) {
			                stack.push(root);
			                res.add(root.val);
			                root = root.right;
			            } else {
			                root = stack.pop();
			                root = root.left;
			            }
			        }
			        Collections.reverse(res);
			        return res;
			    }
			}
	1.4 Level Order
			1.4.1 Normal DFS
			public class Solution {
			    public List<List<Integer>> levelOrder(TreeNode root) {
			         List<List<Integer>> res = new ArrayList<>();
			         if (root == null) {
			             return res;
			         }
			         dfs(res, root, 0);
			         return res;
			    }
			    public void dfs(List<List<Integer>> res, TreeNode root, int depth) {
			        if (root == null) {
			            return;
			        }
			        if (res.size() <= depth) {
			            res.add(new ArrayList<>());
			        }
			        res.get(depth).add(root.val);
			        /*
			        	LevelOrder II problem
				        if (res.size() <= depth) {
	            			res.add(0, new ArrayList<>());
				        }
	        			res.get(res.size() - depth - 1).add(root.val);
        			*/
			        dfs(res, root.left, depth + 1);
			        dfs(res, root.right, depth + 1);
			    }
			}
			1.4.2 Normal BFS
			public class Solution {
				public List<List<Integer>> levelOrder(TreeNode root) {
					List<List<Integer>> res = new ArrayList<>();
					if (root == null)
						return res;
					Queue<TreeNode> queue = new LinkedList<>();
					queue.add(root);
					int curNodeNum = 1;//to record the number of node which in same level
					int nexNodeNum = 0;//to record the number of node which in next level
					List<Integer> item = new ArrayList<>();// store every result array in same level 
					while (!queue.isEmpty()) {
						TreeNode cur = queue.poll();
						curNodeNum--;
						item.add(cur.val);
						if (cur.left != null) {
							nexNodeNum++;
							queue.add(cur.left);
						}
						if (cur.right != null) {
							nexNodeNum++;
							queue.add(cur.right);
						}
						//假如一个层上的结点数量为0，则表示该层遍历完，开始下一层的遍历，next设为cur
						if (curNodeNum == 0) {
							curNodeNum = nexNodeNum;
							nexNodeNum = 0;
							res.add(item);
							//res.add(0, levelres);  the only place we need to modify, and it can be used in Level Order II problem
							item = new ArrayList<>();
						}
					}
					return res;
				}
			}
			1.4.3 ZigZag DFS
			public class Solution {
				public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
			        List<List<Integer>> res = new ArrayList<>();
			        if (root == null) {
			            return res;
			        }
			        dfs(res, 0, root);
			        return res;
			    }
			    public void dfs(List<List<Integer>> res, int depth, TreeNode root) {
			        if (root == null) {
			            return;
			        }
			        if (res.size() <= depth) {
			            res.add(new ArrayList<>());
			        }
			        if (depth % 2== 0) {
			            res.get(depth).add(root.val);
			        } else {
			            res.get(depth).add(0, root.val);
			        }
			        dfs(res, depth + 1, root.left);
			        dfs(res, depth + 1, root.right);
			    }
			}
			1.4.4 ZigZag BFS
			public class Solution {
				public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
					List<List<Integer>> res = new ArrayList<>();
					if (root == null)
						return res;
					boolean reverse = false;
					Queue<TreeNode> queue = new LinkedList<>();
					queue.add(root);
					int curNum = 1;
					int nextNum = 0;
					List<Integer> item = new ArrayList<>();
					while(!queue.isEmpty()) {
						TreeNode cur = queue.poll();
						curNum--;
						item.add(cur.val);
			            if (cur.left != null) {
							queue.add(cur.left);
							nextNum++;
						}
						if (cur.right != null) {
							queue.add(cur.right);
							nextNum++;
						}
						if (curNum == 0) {
							curNum = nextNum;
							nextNum = 0;
							if (reverse) {			
								Collections.reverse(item);//关键 reverse为true时，用Collections的reverse转置
								reverse = false;
							} else {
								reverse = true;
							}
							res.add(item);
							item = new ArrayList<>();
						}
					}
					return res;
				}
			}

	/*
        Why we need Morris?
        Morris Traversal方法可以做到这两点，与前两种方法的不同在于该方法只需要O(1)空间，而且同样可以在O(n)时间内完成。
        要使用O(1)空间进行遍历，最大的难点在于，遍历到子节点的时候怎样重新返回到父节点（假设节点中没有指向父节点的p指针），
        由于不能用栈作为辅助空间。为了解决这个问题，Morris方法用到了线索二叉树（threaded binary tree）的概念。
        在Morris方法中不需要为每个节点额外分配指针指向其前驱（predecessor）和后继节点（successor），
        只需要利用叶子节点中的左右空指针指向某种顺序遍历下的前驱节点或后继节点就可以了。
    */
	1.5 Morris Travseral
			1.5.1 Preorder 
		    /*
		    	Preorder Morris Solution:
		    	前序遍历与中序遍历相似，代码上只有一行不同，不同就在于输出的顺序。
				1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
				2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
					a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。输出当前节点（在这里输出，这是与中序遍历唯一一点不同）。当前节点更新为当前节点的左孩子。
					b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。当前节点更新为当前节点的右孩子。
				3. 重复以上1、2直到当前节点为空。
		    */

			//Morris用的pre，不管在哪个遍历里，实际上最后都是当前节点在inorder下的前驱结点！！！
			public class Solution {
				public List<Integer> preorderTraversal(TreeNode root) {
			        if (root == null) {
			            return Collections.emptyList();
			        }
			        List<Integer> res = new ArrayList<>();
			        TreeNode pre = null;
			        while (root != null) {
			            if (root.left == null) {
			                res.add(root.val);
			                root = root.right;
			            } else {
			                pre = root.left;
			                while (pre.right != null && pre.right != root) {
			                    pre = pre.right;
			                }
			                //假如pre.right 为空，叶子节点，记录root的信息，方便回溯
			                if (pre.right == null) {
			                    pre.right = root;
			                    res.add(root.val);//关键步骤，在这里将当前的root结点输出到结果，先将root输出，再遍历下面的叶子节点输出
			                    root = root.left;
			                }  else {
			                    pre.right = null;
			                    root = root.right;
			                }
			            } 
			        }
			        return res;
			    }
			}
			1.5.2 Inorder
			/*  Inorder Morris Solution:
		        例子图片在这里：http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
		        1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
		        2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
		            a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
		            b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
		        3. 重复以上1、2直到当前节点为空。
		    */
			public class Solution {
			    public List<Integer> inorderTraversal(TreeNode root) {
			        if(root == null) return new ArrayList<Integer>();
			        List<Integer> res = new ArrayList<Integer>();
			        TreeNode pre = null;
			        while(root != null){
			            if(root.left == null){
			                res.add(root.val);
			                root = root.right;
			            } else{
			                pre = root.left;
			                while (pre.right != null && pre.right != root){
			                    pre = pre.right;
			                }
			                if(pre.right == null){
			                    pre.right = root;
			                    root = root.left;
			                    //res.add(root.val); //preorder是在这里加入res
			                }else{
			                    pre.right = null;
			                    res.add(root.val);//与preorder唯一的不同
			                    root = root.right;
			                }
			            }
			        }
			        return res;
			    }
			}
			1.6 Vertical Level Traversal Of Tree
			//Print arbitrary binary tree by vertical levels / columns from left to right,high to low
			public class Solution {
				public List<List<Integer>> verticalLevelTraversalofTree(TreeNode root){
					List<List<Integer>> res = new ArrayList<>();
					//map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
					HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
					//use a queue to do bfs
					LinkedList<TreeNode> queue = new LinkedList<>();
					//use a HashMap to store the TreeNode and the according cloumn value
					HashMap<TreeNode, Integer> weight = new HashMap<>();
					if (root == null) {
						return res;
					}
					queue.add(root);
					weight.put(root, 0);
					int min = 0;
					while (!queue.isEmpty()) {
						TreeNode node = queue.poll();
						int w = weight.get(node);
						//if map doesn't has this column value, just create a list ,and put into map
						if (!map.containsKey(w)) {
							ArrayList<Integer> list = new ArrayList<>();
							list.add(node.val);
							map.put(w, list);
						} else {
							ArrayList<Integer> list = map.get(w);
							list.add(node.val);
							map.put(w, list);
						}
						//enqueue
						if (node.left != null) {
							queue.add(node.left);
							//put the node to weight hashmap
							weight.put(node.left, w - 1);
						}
						if (node.right != null) {
							queue.add(node.right);
							weight.put(node.right, w + 1);
						}
						//update min ,min means the minimum column value, which is the left most node
						min = Math.min(min, w);
					}
					//generate res
					while(map.containsKey(min)) {
						res.add(map.get(min++));
					}
					return res;
				}
			}

   


2. Path Problem In Binary Tree
	2.1 Get All Binary Tree Paths， 
		2.1.1 DFS
		//Hint: Do not need to backtracking
		//在终止返回的语句中，是res.add(item + root.val)， 而且往下走的时候item + root.val + “->"只会影响当前节点，并不会影响上一层的item使用
			public class Solution {
				public List<String> binaryTreePaths(TreeNode root) {
					List<String> res = new ArrayList<>();
					if (root == null) {
						return res;
					}
					helper(root, "", res);
					return res;
				}
				public void helper(TreeNode root, String item, List<String> res) {
					if (root.left == null && root.right == null) {
						res.add(item + root.val);
					}
					if (root.left != null) {
						helper(root.left, item + root.val + "->", res);
					}
					
					if (root.right != null) {
						helper(root.right, item + root.val + "->", res);
					}	
				}
			}
		2.1.2 BFS
			public List<String> binaryTreePaths(TreeNode root) {
		        List<String> res = new ArrayList<>();
		        if (root == null) {
		            return res;
		        }
		        Queue<TreeNode> queue = new LinkedList<>();
		        Queue<String> path = new LinkedList<>();
		        path.offer(root.val + "");
		        queue.offer(root);
		        while (!queue.isEmpty()) {
		            TreeNode cur = queue.poll();
		            String item = path.poll();
		            if (cur.left == null && cur.right == null) {
		                res.add(item);
		            }
		            if (cur.left != null) {
		                queue.offer(cur.left);
		                path.offer(item + "->" + cur.left.val + "");
		            }
		            if (cur.right != null) {
		                queue.offer(cur.right);
		                path.offer(item + "->" + cur.right.val + "");
		            }
		        }
		        return res;
		    }
	2.2 Path Sum Problem
		2.2.1 Path Sum I， find a path sum == sum, path (root to leaf)
			//DFS
			public boolean hasPathSum(TreeNode root, int sum) {
		        if (root == null) {
		            return false;
		        }
		        if (root.left == null && root.right == null) {
		            return root.val == sum;
		        }
		        int newSum = sum - root.val;
		        return hasPathSum(root.left, newSum) || hasPathSum(root.right, newSum);
		    }
		2.2.2 Path Sum II, need to return all the possible path node
			//DFS
			public class Solution {
			    public List<List<Integer>> pathSum(TreeNode root, int sum) {
			        List<List<Integer>> res = new ArrayList<>();
			        if (root == null) {
			            return res;
			        }
			        dfs(root, sum, res, new ArrayList<Integer>());
			        return res;
			    }
			    public void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> item) {
			        if (root == null) {
			            return;
			        }
			        item.add(root.val);
			        if (root.left == null && root.right == null) {
			            if (root.val == sum) {
			                res.add(new ArrayList<>(item));
			            }
			        }
			        dfs(root.left, sum - root.val, res, item);
			        dfs(root.right, sum - root.val, res, item);
			        item.remove(item.size() - 1);
			    }
			}
		2.2.3 Maximum Path Sum
			//DFS
			public class Solution {
				int max;
				public int maxPathSum(TreeNode root) {
					if (root == null) {
						return 0;
					}
					max = Integer.MIN_VALUE;
					findMax(root);
					return max;
				}
				public int findMax(TreeNode root) {
					if (root == null) {
						return 0;
					}
					int leftSum = Math.max(0, findMax(root.left));
					int rightSum = Math.max(0, findMax(root.right));
					max = Math.max(leftSum + rightSum + root.val, max);
					return Math.max(leftSum, rightSum) + root.val;
				}
			}
		2.2.4 Sum Root To Leaf Numbers
			//DFS
			public class Solution {
			    public int sumNumbers(TreeNode root) {
			        if (root == null) {
			            return 0;
			        }
			        return pathSum(root, 0);
			    }
			    public int pathSum(TreeNode root, int sum) {
			        if (root == null) {
			            return 0;
			        }
			        int newSum = sum * 10 + root.val;
			        if (root.left == null && root.right == null) {
			            return newSum;
			        }
			        return pathSum(root.left, newSum) + pathSum(root.right, newSum);
			    }
			}
			//BFS
			public int sumNumbers(TreeNode root) {
		        if (root == null) {
		            return 0;
		        }
		        int res = 0;
		        Queue<TreeNode> queue = new LinkedList<>();
		        Queue<Integer> pathSum = new LinkedList<>();
		        queue.offer(root);
		        pathSum.offer(root.val);
		        while (!queue.isEmpty()) {
		            TreeNode cur = queue.poll();
		            int newSum = pathSum.poll();
		            if (cur.left == null && cur.right == null) {
		                res += newSum;
		            }
		            if (cur.left != null) {
		                queue.offer(cur.left);
		                pathSum.offer(newSum * 10 + cur.left.val);
		            }
		            if (cur.right != null) {
		                queue.offer(cur.right);
		                pathSum.offer(newSum * 10 + cur.right.val);
		            }
		        }
		        return res;
    		}


3. The Property Of Tree
	3.1 Depth And Height
		3.1.1 Height Of Tree
		    public int height(TreeNode root) {
		        if (root == null) {
		            return 0;
		        }
		        return 1 + height(root.left);
		    }
		3.1.2 Maximum Depth
			public int maxDepth(TreeNode root) {
				if (root == null)
					return 0;
				int leftMaxDepth = maxDepth(root.left);
				int rightMaxDepth = maxDepth(root.right);
				return Math.max(leftMaxDepth, rightMaxDepth) + 1;
			}
		3.1.3 Minimum Depth
			public int minDepth(TreeNode root) {
		    	if (root == null)
		    		return 0;
		    	if (root.left == null)
		    		return minDepth(root.right) + 1;
		    	if (root.right == null)
		    		return minDepth(root.left) + 1;
		    	return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
		    }
		3.1.4 Count Complete Tree
			/*
				In a complete binary tree every level, except possibly the last, is completely filled, 
				and all nodes in the last level are as far left as possible. 
				It can have between 1 and 2h nodes inclusive at the last level h.
			*/
		    public int countNodes(TreeNode root) {
		        int rootHeight = height(root);//3.1.1 Height of Tree Method
		        if (rootHeight < 0) {
		            return 0;
		        } 
		        if (height(root.right) == rootHeight - 1) {
		            return (1 << (rootHeight - 1)) - 1 + countNodes(root.right) + 1;// 2^(h - 1)(左子树) - 1 + count(root.right) + 1(root);
		            /*
		                     1
		                   /   \
		                  2     3
		                 / \   / 
		                4   5 6
		            */
		        } else {
		            /*
		                     1
		                   /  \
		                  2    3
		                 / \
		                4   5
		            */
		            return (1 << (rootHeight - 2)) - 1 + countNodes(root.left) + 1;// 2^(h - 2)（右子树） - 1 + cout(root.left) + 1(root);
		        }
		    }





