package com.roger.c_020;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount方法
 * 能够支持两个生产者线程以及10个消费者 线程的阻塞的调用
 * 
 * 使用lock和condition实现
 * 这种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 * 
 * @author Roger.Li
 */
public class MyContainer2 <T>{
	
	private final LinkedList<T> linkedList =  new LinkedList<>();
	private final int MAX = 10;
	private volatile int count = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
	
	public void put(T t) {
		try {
			lock.lock();
			while (linkedList.size() == MAX) {
				producer.await();
			}
			linkedList.add(t);
			++count;
			consumer.signalAll();//通知消费者线程进行消费
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public T get() {
		lock.lock();
		T t  = null;
		try {
			while(linkedList.size() == 0) {
				consumer.await();
			}
			t = linkedList.remove();
			count--;
			producer.signalAll();//通知生产者进行生产
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return t;
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		MyContainer2<String> container = new MyContainer2<>();
		//启动消费者线程
		for(int i = 1; i <= 10; i ++) {
			new Thread(()->{
				for (int j = 0; j < 5; j++) {
					System.out.println(container.get());
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
					container.put(Thread.currentThread().getName() + " " + j);
				}
			},"Produ-"+i).start();
		}
		
	}
	
}
