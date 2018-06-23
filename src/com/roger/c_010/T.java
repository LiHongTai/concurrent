package com.roger.c_010;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Roger.Li
 */
public class T {

	int count = 0;

	@SuppressWarnings("unused")
	synchronized void m() {
		System.out.println(Thread.currentThread().getName() + " start");
		while (true) {
			count++;
			System.out.println(Thread.currentThread().getName() + " count = " + count);

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 5) {
				int i = 1 / 0;// 此处抛出异常，锁被释放，要想不被释放，可以在这里进行catch，继续循环
			}
		}
	}

	public static void main(String[] args) {
		T t = new T();
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		};
		
		new Thread(r,"t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r,"t2").start();
	}
}
