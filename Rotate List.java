/*
	Rotate List 
	Given a list, rotate the list to the right by k places, where k is non-negative.

	For example:
	Given 1->2->3->4->5->NULL and k = 2,
	return 4->5->1->2->3->NULL.
	Tags: LinkedList Two Pointers
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
    public ListNode rotateRight(ListNode head, int n) {
        if ( head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;//指向head的空指针
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        int count = 0;
        while (faster.next != null) {
            faster = faster.next;
            count++;
        } // fast指针作用是遍历到初始链表尾，取得链表长度
       
        for (count j = count - n % count; j > 0 ; j--){
            slow = slow.next;//slow指针作用是遍历到断点。并且 slow.next 等于头结点
        }
        fast.next = dummy.next;//尾结点直接指向头结点
        dummy.next = slow.next;//空指针指向现在的头结点
        slow.next = null;//将slow指针的next置空，表示尾结点
        return dummy.next;
    }
}