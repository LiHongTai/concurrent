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
 * @author Roger.Li
 */
public class ReentantLock3 {

	Lock lock = new ReentrantLock();

	void m1() {
		lock.lock();
		System.out.println("m1 start...");
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(":" + (i + 1));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		System.out.println("m1 end...");
	}

	/**
	 * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行 可以根据tryLock的返回值来判定是否锁定
	 * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unLock的处理，必须放到finally中
	 */
	void m2() {
		boolean locked = false;
		try {
			locked = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("m2 .... " + locked);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (locked) {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		ReentantLock3 r1 = new ReentantLock3();
		new Thread(r1::m1, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2, "t2").start();
	}
}
