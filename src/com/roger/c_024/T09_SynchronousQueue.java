package com.roger.c_024;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 特殊的transferQueue
 * @author Roger.Li
 */
public class T09_SynchronousQueue {
	
	public static void main(String[] args) throws InterruptedException {
		//容量为0的队列，即生产者生成一个产品，必须立即消费
		BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
		
		new Thread(() -> {
			try {
				System.out.println(blockingQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t1").start();
		
		blockingQueue.put("aaa");//阻塞，等待消费者消费
		//blockingQueue.add("aaa");//报错
		
		System.out.println(blockingQueue.size());
	}
}
