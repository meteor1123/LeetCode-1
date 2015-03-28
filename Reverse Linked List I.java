/*
	Reverse a linked list.
	Example
	For linked list 1->2->3, the reversed linked list is 3->2->1

	Challenge
	Reverse it in-place and in one-pass
*/

/*
	(head) -> [ ] -> [ ] -> [ ] -> (tail)
     head      1      2      3      null

	 step1 new a ListNode pre, pre = 
     step2 new a ListNode nex, nex = head.next;

     (head) -> [ ]
				
*/


public class Solution {
    public ListNode reverse(ListNode head) {

        ListNode pre = null;
        while (head != null) {
            ListNode nex = head.next;
            head.next = pre;
            pre = head;
            head = nex;
        }
        return pre;
    }
}