/*
	Heap
*/




1. Array Problem
		1.1 Kth Largest Element In An Array
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
		1.2 Meeting Rooms II
			/*
				Meeting Rooms II
				Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

				For example,
				Given [[0, 30],[5, 10],[15, 20]],
				return 2.
				Tags: Heap, Greedy, Sort
			*/

			/*
			    Solution：
			        1. 先按照interval.start对interval进行排序
			        2. 建一个PriorityQueue，按照interval的end建最小堆，也就是最小的end在前面
			        3. 将排序后的intervals数组取出逐一和最小堆的堆顶interval进行比对，
			            为什么用最小堆，因为此时取出的堆顶元素的pre.start绝对比now.start要小（排序后的数组），
			            我们只需要比较now.start he pre.end
			            3.1 如果now.start比pre.end要大，意味着可以共用一个会议室，merge这个时间段再放入最小堆
			            3.2 如果now.start比pre.end要小，意味着时间重叠，两个都要加入最小堆
			            注意最小堆每次取出的end都是堆顶最小，所以不可能出现 pre（10, 20)  now(22,25) (after20,23  这样忽略的时间段
			*/
			public class Solution {
			    public int minMeetingRooms(Interval[] intervals) {
			        if (intervals == null || intervals.length == 0) {
			            return 0;
			        }
			        // Sort the intervals by start time
			        Comparator<Interval> comp = new Comparator<Interval>() {
			            @Override
			            public int compare(Interval interval1, Interval interval2) {
			                return interval1.start - interval2.start;
			            }
			        };
			        Arrays.sort(intervals, comp);
			        // Use a min heap to track the minimum end time of merged intervals
			        PriorityQueue<Interval> minHeap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
			            public int compare(Interval a, Interval b) {
			                return a.end - b.end;
			            }
			        });
			        // start with the first meeting, put it to a meeting room
			        minHeap.offer(intervals[0]);
			        for (int i = 1; i < intervals.length; i++) {
			            // get the meeting room that finishes earliest
			            Interval interval = minHeap.poll();
			            
			            // if the current meeting starts right after 
			            // there's no need for a new room, merge the interval
			            if (intervals[i].start >= interval.end) {
			                interval.end = intervals[i].end;
			            } else {
			                // otherwise, this meeting needs a new room
			                minHeap.offer(intervals[i]);
			            }
			            //don't forget to put the meeting room back
			            minHeap.offer(interval);
			        }
			        return minHeap.size();
			    }
			}

		1.3 Sliding Window Maximum
			//solution1: O(nlogk)
			public class Solution {
			    public int[] maxSlidingWindow(int[] nums, int k) {
			        int len = nums.length;
			        int[] res = new int[len - k + 1];
			        if (nums.length == 0) {
			            return new int[0];
			        }
			        //maxHeap
			        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			            @Override
			            public int compare(Integer i1, Integer i2) {
			                return i2 - i1;
			            }
			        });
			        
			        for (int i = 0; i < k; i++) {
			            pq.add(nums[i]);
			        }
			        
			        res[0] = pq.peek();
			        for (int i = k; i < len; i++) {
			            pq.remove(nums[i - k]);
			            pq.add(nums[i]);
			            res[i - k + 1] = pq.peek();
			        }
			        return res;
			    }
			}
			//Solution2: O(n),best
			public class Solution {
				public int[] maxSlidingWindow(int[] nums, int k) {
					int n = nums.length;
					if (nums == null || k <= 0) {
						int[] res = new int[0];
						return res;
					}
					int[] res = new int[n - k + 1];
					int j = 0;

					Deque<Integer> queue = new ArrayDeque<>();
					for (int i = 0; i < nums.length; i++) {
						if (!queue.isEmpty() && queue.peek() < i - k + 1) {
							queue.poll();
						}

						while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
							queue.pollLast();
						}

						queue.offer(i);
						if (i >= k - 1) {
							res[j++] = nums[queue.peek()];
						}
					}
					return res;
				}
			}
		1.4 Find Median From Data Stream
			/*
				Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

				Examples: 
				[2,3,4] , the median is 3

				[2,3], the median is (2 + 3) / 2 = 2.5

				Design a data structure that supports the following two operations:

				void addNum(int num) - Add a integer number from the data stream to the data structure.
				double findMedian() - Return the median of all elements so far.
				For example:

				add(1)
				add(2)
				findMedian() -> 1.5
				add(3) 
				findMedian() -> 2
			*/
			class MedianFinder {
			    // Adds a number into the data structure.
			    PriorityQueue<Integer> min = new PriorityQueue<>();
			    PriorityQueue<Integer> max = new PriorityQueue<>(1000, Collections.reverseOrder());
			    public void addNum(int num) {
			        max.offer(num);
			        min.offer(max.poll());
			        if (max.size() < min.size()) {
			            max.offer(min.poll());
			        }
			    }
			    // Returns the median of current data stream
			    public double findMedian() {
			        if (max.size() == min.size()) {
			            return (max.peek() + min.peek()) / 2.0;
			        } else {
			            return max.peek();
			        }
			    }
			};



2. LinkedList
		2.1 Merge K Sorted Lists
			public ListNode mergeKLists(ListNode[] lists) {  
		        // write your code here
		        if (lists == null || lists.length == 0) {
		            return null;
		        }
		        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
		            @Override
		            public int compare(ListNode l1, ListNode l2) {
		                return l1.val - l2.val;
		            }
		        });
		        ListNode dummy = new ListNode(0);
		        ListNode tail = dummy;
		        for (ListNode node : lists) {
		            if (node != null) {
		                queue.offer(node);
		            }
		        }
		        
		        while (!queue.isEmpty()) {
		            tail.next = queue.poll();
		            tail = tail.next;
		            if (tail.next != null) {
		                queue.add(tail.next);
		            }
		        }
		        return dummy.next;
		    }


3. Number, Math
		3.1 Ugly Number II
		/*
			Write a program to find the n-th ugly number.
			Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
			For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
			Note that 1 is typically treated as an ugly number.
		*/
			public class Solution {
			    public int nthUglyNumber(int n) {
			        if (n == 1) {
			            return 1;
			        }
			        PriorityQueue<Long> pq = new PriorityQueue<>();
			        pq.offer(1l);
			        for (long i = 1; i < n; i++) {
			            long temp = pq.poll();
			            while (!pq.isEmpty() && pq.peek() == temp) {
			                pq.poll();//remove the duplicate
			            }
			            pq.add(temp * 2);
			            pq.add(temp * 3);
			            pq.add(temp * 5);
			        }
			        return pq.poll().intValue();
			    }
			}