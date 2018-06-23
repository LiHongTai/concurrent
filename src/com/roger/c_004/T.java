package com.roger.c_004;

/**
 * synchronized 静态方法锁定 的是 T.class
 * @author Roger.Li
 */
public class T {

	private static int count = 10;

	public synchronized static void m() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		synchronized(T.class) {
			count--;
		}
	}
}
