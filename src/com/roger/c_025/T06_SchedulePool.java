package com.roger.c_025;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * ScheduledExecutorService:定时任务
 * @author Roger.Li
 */
public class T06_SchedulePool {

	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
		
		executorService.scheduleAtFixedRate(()->{
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
		
		//executorService.shutdown();
	}
}
