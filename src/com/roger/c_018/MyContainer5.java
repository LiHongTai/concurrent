package com.roger.c_018;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 * 实现一个容器，提供两个方法，add，size 
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数达到5个是，线程2输出提示并结束
 * 
 * 使用Latch(门闩)替代wait notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * countDownLatch不涉及锁定，当count值为零时当前线程继续执行
 * 当不涉及同步，只是涉及线程通信的时候，使用synchronized ,wait/notify 就显得太重了
 * 这时应该使用countDownLatch/cyclicbarrier/semaphore
 * 
 * @author Roger.Li
 */
public class MyContainer5 {

	List<Object> objList = new ArrayList<>();

	public void add(Object obj) {
		objList.add(obj);
	}

	public int size() {
		return objList.size();
	}

	public static void main(String[] args) {
		MyContainer3 myContainer1 = new MyContainer3();
		
		CountDownLatch latch = new CountDownLatch(1);
		
		new Thread(() -> {
			System.out.println("t2 启动");
			if(myContainer1.size() != 5) {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("t2 结束");
			
		}, "t2").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() -> {
			System.out.println("t1 启动");
			for (int i = 1; i <= 10; i++) {
				myContainer1.add(new Object());
				System.out.println("add " + i);
				
				if(myContainer1.size() == 5) {
					//打开门闩，让t2得以执行
					latch.countDown();
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("t1 结束");
			
		}, "t1").start();
		
		
	}

}
