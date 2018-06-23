package com.roger.c_024;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * 消费者先启动 如果生产者生产一个产品，
 * 	校验是否有消费者，如果有消费者则直接通过transfer直接给消费者，而不会在扔到队列中去
 * 				如果没有消费者，则会阻塞
 * @author Roger.Li
 */
public class T08_TransferQueue {
	
	public static void main(String[] args) throws InterruptedException {
		
		LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
		new Thread(() -> {
			try {
				//take方法：没有则等待
				System.out.println(linkedTransferQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t1").start();
		
		TimeUnit.SECONDS.sleep(3);
		
		linkedTransferQueue.transfer("aaa");
	}
}
