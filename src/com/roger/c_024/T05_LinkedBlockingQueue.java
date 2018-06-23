package com.roger.c_024;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者模式
 * 
 * @author Roger.Li
 */
public class T05_LinkedBlockingQueue {

	// 无界队列
	static BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();
	static Random r = new Random();

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					blockingQueue.put("a" + i);// put如果满了，就会等待
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "p1").start();

		for (int i = 1; i <= 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						// take如果空了，就会等待
						System.out.println(Thread.currentThread().getName() + " take - " + blockingQueue.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();
		}
	}
}
