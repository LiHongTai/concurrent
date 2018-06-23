package com.roger.c_024;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者模式，消费者模式
 * 
 * @author Roger.Li
 */
public class T06_ArrayBlockingQueue {
	// 有界队列
	static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			blockingQueue.put("a" + i);// put如果满了，就会等待
		}

		// blockingQueue.put("aaa");//put如果满了，就会等待,一直等待，阻塞
		// blockingQueue.add("aaa");//报异常
		boolean status = blockingQueue.offer("aaa");// 不报异常，通过返回值，判断当前值是否加成功
		// blockingQueue.offer("aaa",1,TimeUnit.SECONDS);//按时间段阻塞

		System.out.println(blockingQueue + " " + status);
	}
}