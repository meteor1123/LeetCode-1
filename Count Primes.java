/*
	Count Primes
	Description:
	Count the number of prime numbers less than a non-negative number, n
	Tags: HashTable Math
*/

public class Solution {
	public int countPrimes(int n) {
		int count=0;
	    boolean[] nums = new boolean[n];
	    for(int i = 2; i < nums.length; i++){
	    	if(!nums[i]){
	    		count++;
	            for(int j = 2 * i; j < nums.length; j = j + i){
	            	nums[j] = true;
	            }
	        }
	    }
	    return count;
	}
}