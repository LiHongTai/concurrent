package com.roger.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronized 保证可见性 又保证原子性
 * @author Roger.Li
 */
public class T {
	int count = 0;

	synchronized void m() {
		for (int i = 0; i < 10000; i++)
			count++;
	}

	public static void main(String[] args) {
		T t = new T();

		List<Thread> threadList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			threadList.add(new Thread(t::m, "thread-" + i));
		}
		
		threadList.forEach((threadInstance)->threadInstance.start());
		
		threadList.forEach((threadInstance) -> {
			try {
				threadInstance.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(t.count);
	}
}
