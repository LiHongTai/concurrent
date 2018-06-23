package com.roger.c_019;

import java.util.concurrent.TimeUnit;

/**
 * ReentrantLock 用于替代synchronized
 * 
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 
 * @author Roger.Li
 */
public class ReentantLock1 {

	synchronized void m1() {
		System.out.println("m1 start...");
		for(int i = 0; i < 10;i ++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("m1 end...");
	}
	
	synchronized void m2() {
		System.out.println("m2 .....");
	}
	
	public static void main(String[] args) {
		ReentantLock1 r1 = new ReentantLock1();
		new Thread(r1::m1, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2, "t2").start();
	}
}
