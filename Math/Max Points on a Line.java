/*
	Max Points on a Line 
	Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
	Tags: HashTable, Math:
*/


/*
	Solution:这道题就是给一个2D平面，然后给的数据结构是由横纵坐标表示的点，然后看哪条直线上的点最多。
	(1) 两点确定一条直线
	(2) 斜率相同的点落在一条直线上   f(x1) - f(x2) / (x1 - x2) = slope
	(3) 坐标相同的两个不同的点算作两个点
	
	tips:
		1.利用HashMap，Key值存斜率，Value存此斜率下的点的个数。
		2.同时考虑特殊情况，如果恰巧遍历到一个相同坐标的点，那么就维护一个local的counter来记录相同的点。
		3.维护一个localmax，计算当前情况下的最大值；
		4.再维护一个全局Max来计算总的最大值。返回全局Max即可。

	step:
		1.loop all the points.
		2. in the first loop, we use a hashmap to store <slope, amount> 
		   also maintain 2 variables : one store the numbers of samepoint,the other is localmax amount
		3. in the second loop, we travesal all the point except the point with same array index,
			and if find any point that (x,y) is  same, we add samepoint +1
		4. after every second  loop. we travseal the hashmap find the maxmium amount of slope,and update the localmax
			and plus the amount of same point
		5. finally update the max with localmax.
*/	

public class Solution {
    public int maxPoints(Point[] points) {
        if (points.length == 0 || points == null)
            return 0;
        if (points.length == 1)
            return 1;
        int max = 1; //the final max;
        //从第一个点开始循环
        for (int i = 0; i < points.length; i++) {
            HashMap<Float, Integer> hm = new HashMap<Float, Integer>();
            int samepoint = 0;
            int localmax = 1;
            
            //将剩余的所有点与上一个循环里的那个点做斜率，放入hashtable中计算，哪个斜率最多，相同的情况维护一个same，之后+上
            for (int j = 0; j < points.length; j++) {
                //在数组里下标相同的数跳过此回合判断
                if (i == j)
                    continue;
                //Same point,jump and continue next loop
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    samepoint++;
                    continue;
                }
                float slope = ((float)(points[i].y - points[j].y)/(points[i].x - points[j].x));
                if (hm.containsKey(slope))
                    hm.put(slope, hm.get(slope) + 1);
                else 
                    hm.put(slope, 2);//find two points;
            }
            
            for (Integer value : hm.values())
                localmax = Math.max(localmax, value);
            localmax += samepoint;
            max = Math.max(max,localmax);
        }
        return max;
    }
}
