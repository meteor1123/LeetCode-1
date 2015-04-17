/*
	Merge Intervals
	Given a collection of intervals, merge all overlapping intervals.

	For example,
	Given [1,3],[2,6],[8,10],[15,18],
	return [1,6],[8,10],[15,18].
	Tags: Array, Sort
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
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() <= 1)
            return intervals;
        //Comparator 实现排序，但是这里需要重写排序方法
        Comparator<Interval> comp = new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {// sort the Interval by start
                if(i1.start == i2.start) 
                    return i1.end - i2.end;
                return i1.start - i2.start;
            }
        };
        Collections.sort(intervals, comp);
        res.add(intervals.get(0));//加入排序后的第一个数
        for(int i = 1; i < intervals.size(); i++) {//从第二个数开始比较
            if(res.get(res.size() - 1).end >= intervals.get(i).start) {//始终从list的最后一个数的end和未加入的数的start进行比较
                res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, intervals.get(i).end);  
            } else 
                res.add(intervals.get(i));  
        }
        return res;
    }
}