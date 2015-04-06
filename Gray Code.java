/*
	Gray Code 
	The gray code is a binary numeral system where two successive values differ in only one bit.

	Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

	For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

	00 - 0
	01 - 1
	11 - 3
	10 - 2

	Note:
	For a given n, a gray code sequence is not uniquely defined.

	For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

	For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

	Tags: Backtracking
*/

public class Solution {
	//Iterative 
	public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (n < 0) {
            return res;
        }
        //n == 0 ,only has 0
        if (n == 0) {
            res.add(0);
            return res;
        }

        //initialize (也就是n == 1)的情况
        res.add(0);
        res.add(1);
        
        //start from 2;
        for (int i = 2; i <= n; i++) {
        	//use size to get the size of ArrayList
            int size = res.size();
            //cause we only need to reverse the ArrayList and add the value with 
            for (int j = size - 1; j >= 0; j--) {
            	//从后面取起来数，逆序 加上（ 1 左移  n - 1 位）
                res.add(res.get(j) + (1 << (i - 1)));
            }
        }
        return res;
    }

    //Recursive
    public ArrayList<Integer> grayCode(int n) {
        if (n == 0) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            result.add(0);
            return result;
        }
        
        ArrayList<Integer> result = grayCode(n - 1);
        int addNumber = 1 << (n - 1);
        int originalsize = result.size();
        for (int i = originalsize - 1; i >= 0; i--) {
            result.add(addNumber + result.get(i));
        }
        return result;
    }
}