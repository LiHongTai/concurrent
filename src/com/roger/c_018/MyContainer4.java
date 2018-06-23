package com.roger.c_018;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 * 实现一个容器，提供两个方法，add，size 
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数达到5个是，线程2输出提示并结束
 * 
 * 给objList添加volatile之后，t2能够接到通知，但是t2线程的死循环，浪费cpu
 * 
 * 使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 使用这种方法，必须要保证t2先执行，也就是首先要t2监听才可以
 * 
 * @author Roger.Li
 */
public class MyContainer4 {

	List<Object> objList = new ArrayList<>();

	public void add(Object obj) {
		objList.add(obj);
	}

	public int size() {
		return objList.size();
	}

	public static void main(String[] args) {
		MyContainer2 myContainer1 = new MyContainer2();
		
		final Object lock = new Object();
		
		new Thread(() -> {
			synchronized (lock) {
				System.out.println("t2 启动");
				if(myContainer1.size() != 5) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2 结束");
				//t2结束后需要通知唤醒t1
				lock.notify();
			}
			
		}, "t2").start();
		
		
		new Thread(() -> {
			synchronized (lock) {
				System.out.println("t1 启动");
				for (int i = 1; i <= 10; i++) {
					myContainer1.add(new Object());
					System.out.println("add " + i);
					
					if(myContainer1.size() == 5) {
						lock.notify();
						//因为这里不会释放锁，所以这时t2启动，但是这里锁被t1锁定，故只能等到t1执行完成后，t2才会执行
						//所以这里需要要使t1处于等待状态，让t2可以获得锁
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t1 结束");
			}
			
		}, "t1").start();
		
		
	}

}
