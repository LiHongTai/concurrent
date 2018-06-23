package com.roger.c_025;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * newCachedThreadPool：
 *  1.来一个任务，如果没有空闲的线程就会新建一个线程，如果有线程的话，就分配给空闲的线程，最大上线大概几万个
 * 	2.默认存活时间：60s，意思就是说，如果60s内都没有新的任务，就会销毁所有的线程
 * @author Roger.Li
 */
public class T04_CachedPool {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		System.out.println("初始化后："+executorService);

		for (int i = 0; i < 2; i++) {
			executorService.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println("提交执行任务后："+executorService);
		
		try {
			TimeUnit.SECONDS.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("sleep 80s后："+executorService);
		executorService.shutdown();
	}
}
