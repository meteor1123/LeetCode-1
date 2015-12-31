/*
	Min Stack
	Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	getMin() -- Retrieve the minimum element in the stack.
*/

/*
	Using  two stack,one stack for store normal element, the other store the min elements.
*/
//Solution1 : Two Stack
public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }
    public void pop() {
        int peek = stack.pop();
        if (minStack.peek() == peek) {
            minStack.pop();
        }
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek();
    }
}



//Solution2:
public class MinStack {
    int min;
    Stack<Integer> stack;
    public MinStack () {
        stack = new Stack<>();
    }
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0);
            min = x;
        } else {
            stack.push(x - min);
            if (x < min) {
                min = x;
            }
        }
    }
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int pop = stack.pop();
        if (pop < 0) {
            min = min - pop;
        }
    }
    public int top() {
        int top = stack.peek();
        if (top > 0) {
            return top + min;
        } else {
            return min;
        }
    }
    public int getMin() {
        return min;
    }
}


//Solution3:One Stack, Store the value and the min's diff
public class MinStack {
    long min;
    Stack<Long> stack;

    public MinStack(){
        stack=new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(0L);
            min=x;
        }else{
            stack.push(x-min);//Could be negative if min value needs to change
            if (x<min) min=x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long pop=stack.pop();

        if (pop<0)  min=min-pop;//If negative, increase the min value

    }

    public int top() {
        long top=stack.peek();
        if (top>0){
            return (int)(top+min);
        }else{
           return (int)(min);
        }
    }

    public int getMin() {
        return (int)min;
    }
}


//Solution4: do not use api stack
public class MinStack {
    private ListNode head = null;
    private ListNode min = null;
     
    public void push(int x) {
        ListNode newNode = new ListNode(x);
         
        if (head == null) {
            head = newNode;
        } else {
            head.next = newNode;
            newNode.prev = head;
            head = newNode;
        }
         
        // Update the min value
        if (min == null || newNode.val <= min.val) {
            min = newNode;
        }
    }
     
    public int pop() {
        ListNode node = head;
         
        head = head.prev;
        // Be careful only one node in the list
        if (head != null) {
            head.next = null;
        }
         
        // Find out the new min
        if (min == node) {
            ListNode p = head;
            min = head;
            while (p != null) {
                if (p.val < min.val) {
                    min = p;
                }
                p = p.prev;
            }
        }
         
        return node.val;
    }
     
    public int getMin() {
        return min.val;
    }
}