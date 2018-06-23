package com.roger.c_025;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/**
 * Executor 接口                         父接口
 * ExecutorService 接口    子接口
 * Executors 工具类 创建ExecutorService接口的实现类
 * 
 * @author Roger.Li
 */
public class T02_Future {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		});
		
		new Thread(futureTask).start();
		
		System.out.println(futureTask.get());//阻塞，等等结果
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Future<Integer> future = executorService.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		
		//System.out.println(future.get());
		System.out.println(future.isDone());
		System.out.println(future.get());
		System.out.println(future.isDone());
		
		executorService.shutdown();
	}
}
