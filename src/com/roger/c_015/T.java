package com.roger.c_015;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 优化
 * 同步代码块中语句越少越好
 * @author Roger.Li
 */
public class T {
	
	int count = 0;
	
	synchronized void m1() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		count++;
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			count++;
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
