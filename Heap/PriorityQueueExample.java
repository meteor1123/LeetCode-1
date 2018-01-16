package com.alg1;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/*
 * PriorityQueue是从JDK1.5开始提供的新的数据结构接口，它是一种基于优先级堆的极大优先级队列。
 * 优先级队列是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素。
 * 如果不提供Comparator的话，优先队列中元素默认按自然顺序排列，也就是数字默认是小的在队列头，字
 * 符串则按字典序排列（参阅 Comparable），也可以根据 Comparator 来指定，这取决于使用哪种构造方法。
 * 优先级队列不允许 null 元素。依靠自然排序的优先级队列还不允许插入不可比较的对象
 */
public class PriorityQueueExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q1 = new PriorityQueue<Integer>();
		q1.add(5);
		q1.add(2);
		q1.add(1);
		q1.add(10);
		q1.add(3);
		
		while (!q1.isEmpty()) {
			System.out.print(q1.poll() + ",");
		}
		Comparator<Integer> cmp = new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) {
				return e2 - e1;
			}
		};
		
		Queue<Integer> q2 = new PriorityQueue<Integer>(5, cmp);
		q2.add(2);
		q2.add(8);
		q2.add(9);
		q2.add(1);
		while (!q2.isEmpty()) {
			System.out.print(q2.poll() + ",");
		}
	}
	

}
