package com.roger.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法是可以调用另一个该类的同步方法的
 * 一个线程已经有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是synchronized获得的锁是可重入的
 * @author Roger.Li
 */
public class T {
	
	public synchronized void m1() {
		System.out.println("m1 start...");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		m2();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m1 end...");
	}
	
	public synchronized void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(" m2 ");
	}
	
	public static void main(String[] args) {
		T t  = new T();
		
		new Thread(()->t.m1()).start();
	}
}
