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
 
/*
    注意到输入采用一个ArrayList存放各个链表的头结点，因此可以采用ArrayList的get(index)取出排序好的链表,形如数组下标进行
*/
public class Solution {
 	public ListNode mergeKLists(ArrayList<ListNode> lists) {
 		if (lists == null || lists.size() == 0)
 			return null;
 		return MSort(lists, 0, lists.size() - 1);
 	}
    
    //算法的关键部分；
 	public ListNode MSort(ArrayList<ListNode> lists, int low, int high) {
 	    //将K个链表的头结点存入一个ArrayList中，方便
 		if (low >= high)
 			return lists.get(low);//递归终止条件
 		int mid = (low + high) / 2;//递归逼近语句
 		ListNode leftlist = MSort(lists, low, mid);
 		ListNode rightlist = MSort(lists, mid + 1, high);
 		return mergeTwoLists(leftlist, rightlist);
 	}
    
    //直接采用mergeTwoSortedLists的方法
 	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
 		if (l1 == null)
 			return l2;
 		if (l2 == null)
 			return l1;

 		ListNode newhead = new ListNode(-1);
 		ListNode l3 = newhead;
 		while (l1 != null && l2 != null) {
 			if (l1.val < l2.val) {
 				l3.next = l1;
 				l1 = l1.next;
 			} else {
 				l3.next = l2;
 				l2 = l2.next;
 			}
 			l3 = l3.next;
 		}

 		if (l1 != null) 
 			l3.next = l1;
 		if (l2 != null)
 			l3.next = l2;
 		return newhead.next;
 	}
 }