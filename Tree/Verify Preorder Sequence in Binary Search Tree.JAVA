/*
	Verify Preorder Sequence in Binary Search Tree
		Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

	You may assume each number in the sequence is unique.

	Follow up:
	Could you do it using only constant space complexity?
*/

/*
	Solution 1

	Kinda simulate the traversal, keeping a stack of nodes (just their values) of which we're still in the left subtree.
     If the next number is smaller than the last stack value, 
     then we're still in the left subtree of all stack nodes, 
     so just push the new one onto the stack. But before that, pop all smaller ancestor values,
      as we must now be in their right subtrees (or even further, in the right subtree of an ancestor). 
      Also, use the popped values as a lower bound, since being in their right subtree means 
      we must never come across a smaller number anymore.
*/


/*
    Explation:
    1. Push to stack till you get higher element than the topmost element of the stack. [i.e. you keep pushing till you hit the leftmost leaf]
    2. If you find current value which is greater than the TOP of Stack, POP till you hit higher element, and also mark the last poped value, which is your variable (Left_Tree_Highest). 
       This variable is used to check whether the order is correct or not.
    3. In all the steps, if you find current element lesser than Left_Tree_Highest, then your tree is not a Binary Search Tree and it should return “NO”.
    4. If all the element traversed, and you have not hit “NO”, means given sequence follows the Binary Search Tree rule.

    Above step might be confusing, but take a pen and paper, 
    try to follow the steps by taking stacks and Left_Tree_Highest values at each step.
     Once you are able to track it, you will able to co-relate it with the steps given above.

    So the basic idea is that at any point, left subtree’s highest element should be lowest for the untraversed elements [Right Tree].
*/
public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE;
    Stack<Integer> path = new Stack();
    for (int p : preorder) {
        if (p < low)
            return false;
        while (!path.empty() && p > path.peek())
            low = path.pop();
        path.push(p);
    }
    return true;
}



//Solution 2 ... O(1) extra space, Same as above, but abusing the given array for the stack.

public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE, i = -1;
    for (int p : preorder) {
        if (p < low)
            return false;
        while (i >= 0 && p > preorder[i])
            low = preorder[i--];
        preorder[++i] = p;
    }
    return true;
}