/*
	Palindrome Linked List
	Given a singly linked list, determine if it is a palindrome.

	Follow up:
	Could you do it in O(n) time and O(1) space?
*/

public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode partNode = slow.next;
        slow.next = null;
        ListNode newList = reverse(partNode);
        ListNode pointer = head;
        while (newList != null) {
            if (pointer.val != newList.val) {
                return false;
            }
            pointer = pointer.next;
            newList = newList.next;
        }
        return true;
    }
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}