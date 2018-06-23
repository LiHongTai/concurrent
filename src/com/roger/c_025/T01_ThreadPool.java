package com.roger.c_025;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executor 接口                         父接口
 * ExecutorService 接口    子接口
 * Executors 工具类 创建ExecutorService接口的实现类
 * 
 * @author Roger.Li
 */
public class T01_ThreadPool {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		for (int i = 0; i < 6; i++) {
			executorService.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println("调用shutdown方法前....");
		System.out.println(executorService);
		//shutdown 方法等所有线程执行完成，才会关闭 shutdownNow方法会立即关闭所有线程
		executorService.shutdown();
		
		System.out.println("调用shutdown方法后....");
		System.out.println(executorService.isTerminated());//false
		System.out.println(executorService.isShutdown());//true
		System.out.println(executorService);
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("调用shutdown方法后，并且睡5秒后....");
		System.out.println(executorService.isTerminated());//true
		System.out.println(executorService.isShutdown());//true
		System.out.println(executorService);
	}
}
