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
 * @author Roger.Li
 */
public class ReentantLock2 {

	Lock lock = new ReentrantLock();
	
	void m1() {
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		System.out.println("m1 end...");
	}

	void m2() {
		lock.lock();
		System.out.println("m2 .....");
		lock.unlock();
	}

	public static void main(String[] args) {
		ReentantLock2 r1 = new ReentantLock2();
		new Thread(r1::m1, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2, "t2").start();
	}
}
