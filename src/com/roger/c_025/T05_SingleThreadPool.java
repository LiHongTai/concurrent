package com.roger.c_025;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * newSingleThreadExecutor：只有一个线程，保证任务一定是顺序执行的
 * @author Roger.Li
 */
public class T05_SingleThreadPool {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 5; i++) {
			final int j = i;
			executorService.execute(()->{
				System.out.println( j + " " + Thread.currentThread().getName());
			});
		}
		
		executorService.shutdown();
	}
}
