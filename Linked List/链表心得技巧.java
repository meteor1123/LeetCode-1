1. Reverse Linked List
	1.1	最普通的reverse，给你一个ListNode，然后reverse从这个ListNode 开始的所有节点
		public ListNode reverse(ListNode head) {
			ListNode pre = null;
			while (head != null) {
				ListNode next = head.next;
				head.next = pre;
				pre = head;
				head = next;
			}
		}

	1.2	反转从链表的第m个结点至第n个结点，1 <= m <= n <= length of list
		for (int i = 0; i < n; i++) {
			if (i < m - 1) {//先找到m-1这个点
				premNode = premNode.next;
			} else if (i == m - 1) {//这样才方便后续reverse 
									//initial: 1 ->  2  -> 3 ->  4 -> 5 -> NULL, m = 2 and n = 4， final：1->4->3->2->5->NULL.
									        //preM mNode nNode
				mNode = premNode.next;
				nNode = mNode.next;
			} else {
				mNode.next = nNode.next;
				nNode.next = premNode.next;
				premNode.next = nNode;
				nNode = mNode.next;
			}
		}

	1.3 

2. Double Pointer Method
	2.1 Find Median 
	   	ListNode slow = head;
	   	ListNode fast = head;
	   	while (fast.next != null && fast.next.next != null) {
		   	fast = fast.next.next;
		   	slow = slow.next;
	    }
	    链表为奇数时slow就是median，链表为偶数时,slow为最中间的两个结点的左边那个。

3. Merge Two List
	3.1 Just Merge (without order)
		public void merge(ListNode l1, ListNode l2) {
			while (l1 != null && l2 != null) {
				ListNode temp1 = l1.next;
				ListNode temp2 = l2.next;
				l1.next = l2;
				l2.next = temp1;
				l1 = temp1;
				l2 = temp2;
			}
		}
	3.2 Merge With Order 
		public ListNode merge(ListNode l1, ListNode l2) {
	        if (l1 == null) {
	            return l2;
	        }
	        if (l2 == null) {
	            return l1;
	        }
	        
	        ListNode dummy = new ListNode(0);
	        ListNode l3 = dummy;
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
	        if (l1 != null) {
	            l3.next = l1;
	        }
	        if (l2 != null) {
	            l3.next = l2;
	        }
	        return dummy.next;
	    }

4. Find Length Of Linked List
	4.1	int len = 0; 
		ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
		while (fast.next != null) {
		 	fast = fast.next;
		 	len++;
		}//上面的这种方法求出来 pointer是指向最后一个结点，因此我们还可以借由fast.next 执行一些比如反转的操作

    4.2 while (fast != null) {
    		fast = fast.next;
    		len++
    	}//这种方法求出来 fast最后会是最后一个结点的next，也就是null，所以fast结点就废了，没有用处















