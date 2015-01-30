/*
	Swap Nodes in Pairs
	Given a linked list, swap every two adjacent nodes and return its head.
	For example:
		Given 1->2->3->4, you should return the list as 2->1->4->3.
	Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
 	public ListNode swapPairs(ListNode head) {
 		LinkedList newhead = new LinkedList(0);//使用fakehead指向头结点保存指针头
 		newhead.next = head;
 		LinkedList cur = newhead;//用cur进行遍历
 		
 		while (cur.next != null && cur.next.next != null) {
 			cur.next = swap(cur.next, cur.next.next);
 			cur = cur.next.next;
 		}
 	}

 	public ListNode swap(ListNode l1, ListNode l2) {
 		l1.next = l2.next;
 		l2.next = l1;
 		return l2;
 	}
 }