package com.roger.c_006;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法m1和非同步方法m2是否可以同时调用
 * 就是说当m1执行的时候,m2是否可以被调用执行
 * 是可以的
 * @author Roger.Li
 */
public class T {
	
	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName() + " m1 start...");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m1 end...");
	}
	
	public void m2() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m2 ");
	}
	
	public static void main(String[] args) {
		T t = new T();
		
		new Thread(()->t.m1(),"t1").start();
		new Thread(()->t.m2(),"t2").start();
		
		//等价于
		/*new Thread(t::m1,"t1").start();
		new Thread(t::m2,"t2").start();*/
		
	}
}
