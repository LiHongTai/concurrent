package com.roger.c_024;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 * @author Roger.Li
 */
public class T07_DelayQueue {
	// 无界有序的队列
	static BlockingQueue<MyTask> blockingQueue = new DelayQueue<>();
	static Random r = new Random();

	static class MyTask implements Delayed {

		long runTime;
		String name;
		MyTask(long rTime,String name) {
			this.runTime = rTime;
			this.name = name;
		}

		@Override
		public int compareTo(Delayed o) {
			if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
				return -1;
			if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
				return 1;
			return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		@Override
		public String toString() {
			return "MyTask [runTime=" + runTime + ", name=" + name + "]";
		}

	}
	
	public static void main(String[] args) throws InterruptedException {
		long now = System.currentTimeMillis();
		MyTask t1 = new MyTask(now + 1000,"t1");
		MyTask t2 = new MyTask(now + 2000,"t2");
		MyTask t3 = new MyTask(now + 1500,"t3");
		MyTask t4 = new MyTask(now + 2500,"t4");
		MyTask t5 = new MyTask(now + 500,"t5");
		
		blockingQueue.put(t1);
		blockingQueue.put(t2);
		blockingQueue.put(t3);
		blockingQueue.put(t4);
		blockingQueue.put(t5);
		
		System.out.println(blockingQueue);
		
		for (int i = 0; i < 5; i++) {
			System.out.println(blockingQueue.take());
		}
	}
}
