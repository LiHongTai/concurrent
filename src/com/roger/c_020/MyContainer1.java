package com.roger.c_020;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount方法
 * 能够支持两个生产者线程以及10个消费者 线程的阻塞的调用
 * 
 * 使用wait和notify/notifyAll来实现
 * @author Roger.Li
 */
public class MyContainer1 <T>{
	
	private final LinkedList<T> linkedList =  new LinkedList<>();
	private final int MAX = 10;
	private volatile int count = 0;
	
	/**
	 * 这里使用while而不是if的原因:
	 * 		如果使用if，则当该等待的线程被唤醒时，直接进入add操作，而不会再次去判断容器的容量是否超标
	 *     那么如果等待的线程超过2个，则都进行add操作，但是容量只有1个，就会出现问题
	 * @param t
	 */
	public synchronized void put(T t) {
		while(linkedList.size() == MAX) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		linkedList.add(t);
		++count;
		this.notifyAll();//通知消费者线程进行消费
	}
	
	public synchronized T get() {
		T t  = null;
		while(linkedList.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = linkedList.remove();
		--count;
		this.notifyAll();
		return t;
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		MyContainer1<String> container1 = new MyContainer1<>();
		//启动消费者线程
		for(int i = 1; i <= 10; i ++) {
			new Thread(()->{
				for (int j = 0; j < 5; j++) {
					System.out.println(container1.get());
				}
			},"Conustmer-"+i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//启动生产者线程
		for(int i = 1; i <= 2; i ++) {
			new Thread(()->{
				for (int j = 0; j < 25; j++) {
					container1.put(Thread.currentThread().getName() + "-" + j);
				}
			},"Productor-"+i).start();
		}
		
	}
	
}
