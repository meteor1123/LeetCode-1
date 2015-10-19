/*
	Meeting Rooms II
	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

	For example,
	Given [[0, 30],[5, 10],[15, 20]],
	return 2.
	Tags: Heap, Greedy, Sort
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

/*
    Solution：
        1. 先按照interval.start对interval进行排序
        2. 建一个PriorityQueue，按照interval的end建最小堆，也就是最小的end在前面
        3. 将排序后的intervals数组取出逐一和最小堆的堆顶interval进行比对，
            为什么用最小堆，因为此时取出的堆顶元素的pre.start绝对比now.start要小（排序后的数组），
            我们只需要比较now.start he pre.end
            3.1 如果now.start比pre.end要大，意味着可以共用一个会议室，merge这个时间段再放入最小堆
            3.2 如果now.start比pre.end要小，意味着时间重叠，两个都要加入最小堆
            注意最小堆每次取出的end都是堆顶最小，所以不可能出现 pre（10, 20)  now(22,25) (after20,23  这样忽略的时间段
*/
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // Sort the intervals by start time
        Comparator<Interval> comp = new Comparator<Interval>() {
            @Override
            public int compare(Interval interval1, Interval interval2) {
                return interval1.start - interval2.start;
            }
        };
        Arrays.sort(intervals, comp);
        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> minHeap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });
        // start with the first meeting, put it to a meeting room
        minHeap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            // get the meeting room that finishes earliest
            Interval interval = minHeap.poll();
            
            // if the current meeting starts right after 
            // there's no need for a new room, merge the interval
            if (intervals[i].start >= interval.end) {
                interval.end = intervals[i].end;
            } else {
                // otherwise, this meeting needs a new room
                minHeap.offer(intervals[i]);
            }
            //don't forget to put the meeting room back
            minHeap.offer(interval);
        }
        return minHeap.size();
    }
}