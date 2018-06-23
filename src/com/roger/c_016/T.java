package com.roger.c_016;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，如果对象o的属性发声改变，不影响锁的使用
 * 但是如果o变成另一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 * @author Roger.Li
 */
public class T {
	Object o = new Object();
	
	void m() {
		synchronized (o) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		T t = new T();
		
		new Thread(t::m,"t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread t2 = new Thread(t::m,"t2");
		
		t.o = new Object();//锁对象发生改变，所以t2线程得以执行
		
		t2.start();
	}
}
