/*
	Merge k Sorted Lists
	Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
	Tags:Divide and Conquer, LinkedList, Heap
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 public class Solution {
 	public ListNode mergeKLists(ArrayList<ListNode> lists) {
 		if (lists == null || lists.length() == 0)
 			return null;
 		return MSort(lists, 0, lists.size() - 1);
 	}

 	public ListNode MSort(ArrayList<ListNode> lists, int low, int high) {
 		if (low >= high)
 			return lists.get(low)
 		int mid = (low + high) / 2;
 		ListNode leftlist = MSort(lists, low, mid);
 		ListNode rightlist = MSort(lists, mid + 1, high);
 		return mergeTwoLists(leftlist, rightlist);
 	}

 	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

 	}
 }