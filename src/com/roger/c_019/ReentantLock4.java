package com.roger.c_019;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 需要注意的是，ReentrantLock是手工锁，必须要必须要必须要手动释放锁
 * 
 * 使用sync锁定的时候，如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * 
 * 使用ReentrantLock可以进行"尝试锁定"，尝试锁定或者在指定的时间内无法锁定，线程可以决定是否等待
 * 
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 * 
 * @author Roger.Li
 */
public class ReentantLock4 {


	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		
		Thread t1 = new Thread(()-> {
			lock.lock();
			try {
				System.out.println("t1 start...");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				System.out.println("t1 interrupted");
			}finally {
				lock.unlock();
			}
		},"t1");
		t1.start();
		
		Thread t2 = new Thread(()-> {
			try {
				//lock.lock();//不能被interrupt()打断
				lock.lockInterruptibly();//可以对interrupt()方法做出响应,进入异常捕获
				System.out.println("t2 start...");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end ...");
			} catch (InterruptedException e) {
				System.out.println("t2 interrupted");
			}finally {
				System.out.println("t2 unlock....");
				lock.unlock();
			}
		},"t2");
		t2.start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.interrupt();//打断线程t2的等待
	}
}
