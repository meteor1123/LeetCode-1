/*
	Sorting
*/




1. Sort Problem
		1.1 Wiggle Sort
			/*
				Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
				For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
			*/
			/*
				核心思想：
					i % 2 == 0的nums[i] 要小于左右两边，否则swap
					i % 2 != 0的nums[i] 要大于左右两边，否则swap
			*/
			public class Solution {
			    public void wiggleSort(int[] nums) {
			        for (int i = 0; i < nums.length - 1; i++) {
			            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
			                swap(nums, i, i + 1);
			            }
			        }
			    }
			    public void swap(int[] nums, int i, int j) {
			        int temp = nums[i];
			        nums[i] = nums[j];
			        nums[j] = temp;
			    }
			}
		1.2 Sort Colors I
			/*
				Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
				Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
			*/
			//Solution1: Two Pass
			public class Solution {
			    public void sortColors(int[] nums) {
			        int red = 0;
			        int white = 0;
			        int blue = 0;
			        for (int i = 0; i < nums.length; i++) {
			            if (nums[i] == 0) {
			                red++;
			            } else if (nums[i] == 1) {
			                white++;
			            } else {
			                blue++;
			            }
			        }
			        for (int i = 0; i < red; i++) {
			            nums[i] = 0;
			        }
			        for (int i = red; i < red + white; i++) {
			            nums[i] = 1;
			        }
			        for (int i = red + white; i < nums.length; i++) {
			            nums[i] = 2;
			        }
			    }
			}
			//Solution2: One Pass Two Pointer
			public class Solution {
			    public void sortColors(int[] nums) {
			        if (nums == null || nums.length <= 1) {
			            return;
			        }
			        int index0 = 0;//指示0
			        int index2 = nums.length - 1;//指示2
			        
			        int i = 0;//遍历
			        while (i <= index2) {
			            if (nums[i] == 0) {
			                swap(nums, i++, index0++);
			            } else if (nums[i] == 1) {
			                i++;
			            } else {
			                swap(nums, i, index2--);
			            }
			        }
			    }
			}
		1.3 Sort Colors II
			public void sortColors2(int[] colors, int k) {
		        // write your code here
		        if (colors == null || colors.length == 0 || k <= 1) {
		            return;
		        }
		        int r = colors.length - 1;
		        for (int i = 0; i < k - 1; ++i) {//为什么是 < k - 1 而不是< k?  因为K个数，至多只需要K-1次partition就能排好顺序
		            r = partition(colors, 0, r, k - i - 1);//pivot从k - 1 到  1
		        }
		    }
		    
		    public int partition(int[] a, int low, int high, int pivot) {
		       int i = low;
		       int j = high;
		       while (true) {
		           while (i < j && a[i] <= pivot) {
		               i++;
		           }
		           while (i < j && a[j] > pivot) {
		               j--;
		           }
		           if (i == j) {
		               break;
		           }
		           swap(a, i, j);
		       }
		       swap(a, high, i);
		       return i - 1;
		    }



2. Quick Sort And Quick Select Problem
		2.0 Quick Sort
		    public void quicksort(int[] nums, int start, int end) {
		        if (start < end) {
		            int pivot = partition(nums, start, end);
		            quicksort(nums, start, pivot - 1);
		            quicksort(nums, pivot + 1, end);
		        }
		    }
		    public int partition(int[] nums, int start, int end) {
		        int i = start;
		        int j = end;
		        int pivot = nums[end];
		        while (true) {
		            while (i < j && nums[i] < pivot) {
		                i++;
		            }
		            while (i < j && nums[j] >= pivot) {
		                j--;
		            }
		            if (i == j) {
		                break;
		            }
		            swap(nums, i, j);
		        }
		        swap(nums, l, end);
		        return i;
		    }
		2.1 Quick Select 
			//Version1: quick selcet O(n) time complexity,
			public int select(int a[], int k, int l, int r) {//找出数组中第K小元素  
			 	//数组中只有一个数 
			    if (l == r) {
			    	return a[l];
			    } 
			    int q = partition(a, l, r);  
			    int t = q - l + 1;//左边数的个数  
			    if (k == t) { //k==t那么a[q]就是答案  
			    	return a[q];
			    } else if (k < t) {//在左边进行查找 
			    	return select(a, k, l, q - 1);
			   	} else { //在右边进行查找，注意这里就需要查找第k-t小数了  
			    	return select(a, k - t, q + 1, r);
			    }
			}
			//Version2:
			//找出数组中第K小元素，k代表第K小，不是数组序号
		    public int quickselect(int[] a, int k, int start, int end) {
		        int i = start;
		        int j = end;
		        while (true) {
		            while (i < j && a[i] < a[end]) {
		               i++;
		            }
		            while (i < j && a[j] >= a[end]) {
		               j--;
		            }
		            if (i == j) {
		               break;
		            }
		            swap(a, i, j);
		        }
		        swap(a, i, end);			        
		        if (i + 1 == k) {
		            return a[i];
		        } else if (i + 1 < k) {
		            return quickselect(a, k, i + 1, end);
		        } else {
		            return quickselect(a, k, start, i - 1);
		        }
		    }  

		2.3 Kth Largest Element In An Array / Find Median
			/*
				Version1 and Version2 the avg time complexity is O(n), but the worst case also O(n`2),
				Only we use the shuffle function to random the input array,that's can reach O(n) in probability
			*/
			//Version1:
			public class Solution {
			    public int findKthLargest(int[] nums, int k) {
			        return quickSelect(nums, nums.length - k + 1, 0, nums.length - 1);//找第K大就是等同于找第ums.length - k + 1小

			        // return quickselect(nums, (nums.length + 1) / 2, 0, nums.length - 1);/ /find meadian
			    }
			    public int quickSelect(int[] nums, int k, int start, int end) {
			        int l = start;
			        int r = end;
			        while (true) {
			            while (l < r && nums[l] < nums[end]) {
			                l++;
			            }
			            while (l < r && nums[r] >= nums[end]) {
			                r--;
			            }
			            if (l == r) {
			                break;
			            }
			            swap(nums, l, r);
			        }
			        swap(nums, l, end);
			        
			        if (l + 1 == k) {
			            return nums[l];
			        } else if (l + 1 < k) {
			            return quickSelect(nums, k, l + 1, end);
			        } else {
			            return quickSelect(nums, k, start, l - 1);
			        }
			    }
			}
			//Version2:
			public class Solution {
				public int findKthLargest(int[] nums, int k) {
					k = nums.length - k;
					int low = 0;
					int high = nums.length - 1;
					while (low < high) {
						int j = partition(nums, low, high);
						if (j < k) {
							low = j + 1;
						} else if (j > k) {
							high = j - 1;
						} else {
							break;
						}
					}
					return nums[k];
				}
				//Shuffle Method
				private void shuffle(int a[]) {
			        final Random random = new Random();
			        for(int ind = 1; ind < a.length; ind++) {
			            final int r = random.nextInt(ind + 1);
			            exch(a, ind, r);
			        }
			    }
			}


3. Heap Problem
		2.1 Kth Largest Element In An Array
			public class Solution {
			    public int findKthLargest(int[] nums, int k) {
			        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			        for (int i = 0; i < nums.length; i++) {
			            pq.offer(nums[i]);
			            if (pq.size() > k) {
			                pq.poll();
			            }
			        }
			        return pq.peek();
			    }
			}

4. N Sum Problem
		4.1 Two Sum II - Input Array Is Sorted


5. Median Problem
		5.1 Best Meeting Point
			/*
				A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

				For example, given three people living at (0,0), (0,4), and (2,2):

				1 - 0 - 0 - 0 - 1
				|   |   |   |   |
				0 - 0 - 0 - 0 - 0
				|   |   |   |   |
				0 - 0 - 1 - 0 - 0
				The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

				Hint:

				Try to solve it in one dimension first. How can this solution apply to the two dimension case?
			*/
			public class Solution {
			    public int minTotalDistance(int[][] grid) {
			        List<Integer> x = new ArrayList<>();
			        List<Integer> y = new ArrayList<>();
			        
			        for (int i = 0; i < grid.length; i++) {
			            for (int j = 0; j < grid[0].length; j++) {
			                if (grid[i][j] == 1) {
			                    x.add(i);
			                    y.add(j);
			                }
			            }
			        }
			        return findDistance(x) + findDistance(y);
			    }
			    public int findDistance(List<Integer> list) {
			        Collections.sort(list);
			        int start = 0;
			        int end = list.size() - 1;
			        int sum = 0;
			        while (start < end) {
			            sum += Math.abs(list.get(start++) - list.get(end--));
			        }
			        return sum;
			    }
			}
