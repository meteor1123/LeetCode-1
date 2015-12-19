/*
	Sorting
*/




1. Sort Problem


2. Quick Select Problem
	2.1 


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
