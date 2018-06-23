package com.roger.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {

	public static void main(String[] args) {
		Queue<String> queues = new ConcurrentLinkedQueue<String>();

		for (int i = 0; i < 10; i++) {
			queues.offer("a" + i);//类似add
		}
		
		System.out.println(queues);
		System.out.println(queues.size());
		
		System.out.println(queues.poll());//拿出来并删除该元素
		System.out.println(queues.size());
		
		
		System.out.println(queues.peek());//只拿出数据，并不删除
		System.out.println(queues.size());
	}
}
