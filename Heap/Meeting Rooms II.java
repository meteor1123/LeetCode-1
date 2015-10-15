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