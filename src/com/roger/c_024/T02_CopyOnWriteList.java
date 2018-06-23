package com.roger.c_024;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 
 * 多线程情况下，写时效率低，读时效率高 适合写小读多的情况
 * 
 * 监听器可以考虑使用
 * 
 * @author Roger.Li
 */
public class T02_CopyOnWriteList {

	public static void main(String[] args) {
		List<String> list
		// = new ArrayList<>();//这个并发时会出现问题 116 97030
		//		= new Vector<>();//130 100000
		 = new CopyOnWriteArrayList<>();//5710ms 100000  写时效率低

		Random ran = new Random();
		Thread[] ths = new Thread[100];
		for (int i = 0; i < ths.length; i++) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						list.add("a" + ran.nextInt(10000));
					}
				}
			};
			ths[i] = new Thread(r);
		}

		runAdnComputeTime(ths);
		System.out.println(list.size());
	}

	private static void runAdnComputeTime(Thread[] ths) {
		long startTime = System.currentTimeMillis();

		Arrays.asList(ths).forEach((th) -> {
			th.start();
		});

		Arrays.asList(ths).forEach((th) -> {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
}
