package com.roger.c_019;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 需要注意的是，ReentrantLock是手工锁，必须要必须要必须要手动释放锁
 * 
 * 使用sync锁定的时候，如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * 
 * 3 使用ReentrantLock可以进行"尝试锁定"，尝试锁定或者在指定的时间内无法锁定，线程可以决定是否等待
 * 
 * 4 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 * 
 * 
 * 5 ReentrantLock还可以指定公平锁
 * 
 * @author Roger.Li
 */
public class ReentantLock5 extends Thread{

	//使用下面的构造方法可以指定公平锁,当参数为true时
	private static ReentrantLock lock = new ReentrantLock(true);
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i ++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " 获得锁");
			}finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		
		ReentantLock5 r1 = new ReentantLock5();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		Thread t3 = new Thread(r1);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
