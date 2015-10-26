/*
	Course Schedule II 
	There are a total of n courses you have to take, labeled from 0 to n - 1.

	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

	Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

	There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

	For example:
	2, [[1,0]]
	There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

	4, [[1,0],[2,0],[3,1],[3,2]]
	There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
*/



/*
	Solution: in this problem, we can not use int[][] to represent the graph matrix,since that will be memory exceed
			  instead of that, we use boolean[][] to represent the graph matrix!
*/

//Solution1
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        ArrayList<Integer> item = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        //初始化graph
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        //构建graph。 [1,0]代表 0 -> 1 ,要修课程1之前需要修课程0， 1的入度为1， 0的入度为0
        for (int i = 0; i < prerequisites.length; i++) {
            map.get(prerequisites[i][1]).add(prerequisites[i][0);
            indegree[prerequisites[i][0]]++;
        }
        //首先将入度为0的点入队列
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        //遍历队列里的点
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[count++] = course;
            for (int i : map.get(course)) {
                if (--indegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        return count == numCourses ? res : new int[0];

    }
}


//Solution2
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        boolean[][] matrix = new boolean[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][cur] == false) {
                indegree[cur]++;
                matrix[pre][cur] = true;
            }
            
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[count] = course;
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != false) {
                    if (--indegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }
        return count == numCourses ? res : new int[0];
    }
}