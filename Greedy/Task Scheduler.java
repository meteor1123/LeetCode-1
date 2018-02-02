/*
    621. Task Scheduler

    Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

    However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

    You need to return the least number of intervals the CPU will take to finish all the given tasks.

    Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
    Note:
    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].
*/


// Time complexity : O(n). Number of iterations will be equal to resultant time time
// Space complexity : O(1). queuequeue and temptemp size will not exceed O(26).
class Solution {
    class Task {
        public char letter;
        public int count;
        public Task(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0)
            return 0;
        
        HashMap<Character, Integer> map = new HashMap();
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Task> pq = new PriorityQueue<Task>(
            (a, b) -> a.count == b.count ? a.letter - b.letter : b.count - a.count
        );
 
        for (char key : map.keySet()) {
            pq.offer(new Task(key, map.get(key)));
        }
        
        int count = 0;
        Queue<Task> queue = new LinkedList();
        while (!pq.isEmpty()) {
            int k = n + 1; //every group should have n + 1, why? 1 2 3 ,1 2 3  if n = 2, k = 2 + 1 = 3
            queue.clear();
            while (k > 0 && !pq.isEmpty()) { // most frequency task
                Task cur = pq.poll();
                cur.count -= 1; // decrease frequency, meaning it got executed
                queue.offer(cur); // collect task to add back to queue
                k--;
                count++; //successfully executed task
            }
            
            for (Task task : queue) {
                if (task.count > 0)
                    pq.offer(task); // add valid tasks 
            }
            
            if (pq.isEmpty())
                break;
            count += k; // if k > 0, then it means we need to be idle
        }
        return count;
        
    }
}


//Solution2:  time: O(n), space: O(1)
/*
    First consider the most frequent characters, we can determine their relative positions first and use them as a frame to insert the remaining less frequent characters. Here is a proof by construction:

    Let F be the set of most frequent chars with frequency k.
    We can create k chunks, each chunk is identical and is a string consists of chars in F in a specific fixed order.
    Let the heads of these chunks to be H_i; then H_2 should be at least n chars away from H_1, and so on so forth; 
    then we insert the less frequent chars into the gaps between these chunks sequentially one by one ordered by frequency in a decreasing order and try to fill the k-1 gaps as full or evenly as possible each time you insert a character. 
    In summary, append the less frequent characters to the end of each chunk of the first k-1 chunks sequentially and round and round, then join the chunks and keep their heads’ relative distance from each other to be at least n.
*/
public class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }
}