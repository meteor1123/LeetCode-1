/*
	Math
*/






1. Number Problem
	1.1 Happy Number
		/*
			A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
			Example: 19 is a happy number
				12 + 92 = 82
				82 + 22 = 68
				62 + 82 = 100
				12 + 02 + 02 = 1
		*/	
		public class Solution {
		    public boolean isHappy(int n) {
		        HashSet<Integer> set = new HashSet<>();
		        int sum = 0;
		        int remain = 0;
		        while (set.add(n)) {
		            sum = 0;
		            while (n > 0) {
		                remain = n % 10;
		                sum += remain * remain;
		                n = n / 10;
		            }
		            if (sum == 1) {
		                return true;
		            } else {
		                n = sum;
		            }
		        }
		        return false;
		    }
		}
	1.2 Ugly Number 
		1.2.1 Ugly Number I
			/*
				Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
			*/
			public class Solution {
			    public boolean isUgly(int num) {
			        if (num <= 0) {
			            return false;
			        }
			        for (int i = 2; i <= 5; i++) {
			            while (num % i == 0) {
			                num = num / i;
			            }
			        }
			        return num == 1;
			    }
			}
		1.2.2 Ugly Number II
			//Solution1 DP, O(n)
			public class Solution {
			    public int nthUglyNumber(int n) {
			        int[] ugly = new int[n];
			        ugly[0] = 1;
			        int index2 = 0, index3 = 0, index5 = 0;
			        int factor2 = 2, factor3 = 3, factor5 = 5;
			        for (int i = 1;i < n; i++) {
			            int min = Math.min(Math.min(factor2, factor3), factor5);
			            ugly[i] = min;
			            if (factor2 == min) {
			                factor2 = 2 * ugly[++index2];
			            }
			            if (factor3 == min) {
			                factor3 = 3 * ugly[++index3];
			            }
			            if (factor5 == min) {
			                factor5 = 5 * ugly[++index5];
			            }
			        }
			        return ugly[n - 1];
			    }
			}
			//Solution2 O(nlogk)
			public class Solution {
			    public int nthUglyNumber(int n) {
			        if (n == 1) {
			            return 1;
			        }
			        PriorityQueue<Long> pq = new PriorityQueue<>();
			        pq.offer(1l);
			        for (long i = 1; i < n; i++) {
			            long temp = pq.poll();
			            while (!pq.isEmpty() && pq.peek() == temp) {
			                pq.poll();//remove the duplicate
			            }
			            pq.add(temp * 2);
			            pq.add(temp * 3);
			            pq.add(temp * 5);
			        }
			        return pq.poll().intValue();
			    }
			}
		1.2.3 Super Ugly Number
			/*
				Write a program to find the nth super ugly number.
				Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
				For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

				Note:
				(1) 1 is a super ugly number for any given primes.
				(2) The given numbers in primes are in ascending order.
				(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
			*/
			public class Solution {
			    public int nthSuperUglyNumber(int n, int[] primes) {
			        if (n == 1) {
			            return 1;
			        }
			        int[] res = new int[n];
			        res[0] = 1;
			        int[] index = new int[primes.length];
			        for (int i = 1; i < n; i++) {
			            res[i] = Integer.MAX_VALUE;
			            for (int j = 0; j < primes.length; j++) {
			                res[i] = Math.min(res[i], primes[j] * res[index[j]]);
			            }
			            for (int j = 0; j < primes.length; j++) {
			                if (res[i] == primes[j] * res[index[j]]) {
			                    index[j]++;
			                }
			            }
			        }
			        return res[n - 1];
			    }
			}
	1.3 Strobogrammatic Number 
		1.3.1 Strobogrammatic Number I
			/*
				A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
				Write a function to determine if a number is strobogrammatic. The number is represented as a string.
				For example, the numbers "69", "88", and "818" are all strobogrammatic.
			*/
				public class Solution {
				    public boolean isStrobogrammatic(String num) {
				        if (num == null || num.length() == 0) {
				            return false;
				        }
				        HashMap<Character, Character> map = new HashMap<>();
				        map.put('6', '9');
				        map.put('9', '6');
				        map.put('8', '8');
				        map.put('0', '0');
				        map.put('1', '1');
				        int start = 0;
				        int end = num.length() - 1;
				        while (start <= end) {
				            char c1 = num.charAt(start);
				            char c2 = num.charAt(end);
				            if (!map.containsKey(c1) || !map.containsKey(c2)) {
				                return false;
				            } else if (map.get(c1) == c2) {
				                start++;
				                end--;
				            } else {
				                return false;
				            }
				        }
				        return true;
				    }
				}
		1.3.2 Strobogrammatic Number II
				public class Solution {
				    public List<String> findStrobogrammatic(int n) {
				        return helper(n, n);
				    }
				    List<String> helper(int n, int m) {
				        if (n == 0) {
				            // ArrayList<String> tempRes = new ArrayList<String>();
				            // tempRes.add("");
				            return new ArrayList<String>(Arrays.asList(""));
				        }
				        if (n == 1) {
				            // ArrayList<String> tempRes = new ArrayList<String>();
				            // tempRes.add("0");
				            // tempRes.add("1");
				            // tempRes.add("8");
				            return new ArrayList<String>(Arrays.asList("0", "1", "8"));//asList 的只能读，不能改
				        }
				        List<String> list = helper(n - 2, m);//why n - 2? since every time we add two number, one from head,the other from end
				        List<String> res = new ArrayList<String>();
				        for (int i = 0; i < list.size(); i++) {
				            String s = list.get(i);
				            // n == m, 就是最外层的时候，不能在最外围加上0， 会导致出现010这样的无效数字
				            if (n != m) {
				                res.add("0" + s + "0");
				            }
				            res.add("1" + s + "1");
				            res.add("6" + s + "9");
				            res.add("8" + s + "8");
				            res.add("9" + s + "6");
				        }
				        return res;
				    }
				}
		1.3.3 Strobogrammatic Number III
				public class Solution {
				    public int strobogrammaticInRange(String low, String high) {
				        int count = 0;
				        List<String> res = new ArrayList<>();
				        for (int i = low.length(); i<= high.length(); i++) {
				            res.addAll(helper(i, i));
				        }
				        for (String num : res) {
				            if ((num.length() == low.length() && num.compareTo(low) < 0) || (num.length() == high.length() && num.compareTo(high) > 0)) {
				                continue;
				            }
				            count++;
				        }
				        return count;
				    }
				    public List<String> helper(int cur, int max) {
				        if (cur == 0) {
				            return new ArrayList<String>(Arrays.asList(""));
				        }
				        if (cur == 1) {
				            return new ArrayList<String>(Arrays.asList("1", "8", "0"));
				        }
				        List<String> res = new ArrayList<String>();
				        List<String> center = helper(cur - 2, max);
				        
				        for (int i = 0; i < center.size(); i++) {
				            String tmp = center.get(i);
				            if (cur != max) {
				                res.add("0" + tmp + "0");
				            }
				            res.add("1" + tmp + "1");
				            res.add("6" + tmp + "9");
				            res.add("8" + tmp + "8");
				            res.add("9" + tmp + "6");
				        }
				        return res;
				    }
				}
	1.4 