package com.roger.c_003;
/**
 * synchronized 锁定的是 this 即当前对象
 * @author Roger.Li
 */
public class T {
	private int count = 10;
	
	/**
	 * 等价于包c_002的写法
	 */
	public synchronized void m() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
}
