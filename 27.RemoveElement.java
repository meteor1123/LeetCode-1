/*
	Remove Element
	Given an array and a value, remove all instances of that value in place and return the new length.
	The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/



public class Solution {

    /*
        这道题有几个关键字需要注意: in place, the order of elements can be changed.所以我们可以尝试用two pointers，
        一个记录remove过后的数组最后一个元素位置，一个用来遍历整个数组，判断条件就是比较该元素和target的值，
        如果不相等就把它copy给另一个pointer.因为题目只需要求最后的数组长度，所以返回最后记录位置就可以了。
        复杂度： 时间复杂度O(n)，空间复杂度O(1).
    */
        //Solution best
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) {
            return 0;
        }
        //index record the length after remove the elem
        int index = 0;
        //traverse the arry
        for (int i = 0; i < A.length; i++) {
            //if A[i] don't equals the elem, that we put this A[i] to A[index],and index ++
            if (A[i] != elem) {
                A[index] = A[i];
                index++;//
            }
        }
        return index;
    }
    
    //Solution2
    public int removeElement(int[] A, int elem) {
    	if (A == null || A.length == 0)
    		return 0;
    	int len = A.length - 1;
    	for (int i = 0; i < len; i++) {
    		while (A[i] == elem && i < len) {
    			A[i] = A[--len];
    		}
    	}
    	return len;
    }

    //Solution3 
    public int removeElement(int[] A, int elem) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            if (A[0] == elem){
                return 0;
            } else {
                return 1;
            }
        }
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            if (A[i] == elem) {
                while (A[j] == elem && j > i) {
                    j--;
                }
                if (j <= i) {
                    break;
                }
                swap(A, i++, j--);
            } else {
                i++;
            }
        }
        int k = 0;
        while (k < A.length) {
            if (A[k] != elem) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }
    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}





}