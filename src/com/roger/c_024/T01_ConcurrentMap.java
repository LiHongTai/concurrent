package com.roger.c_024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {

	public static void main(String[] args) {
		Map<String,String> concurrentMap = new ConcurrentHashMap<>();
		//适用高并发并排序需求
		Map<String,String> skipListMap = new ConcurrentSkipListMap<>();
		
		Map<String,String> hashTableMap = new Hashtable<>();
		Map<String,String> hashMap = new HashMap<>();
		
		
		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(()->{
				for(int j = 0; j<10000; j ++) {
					//concurrentMap 570ms 效率高，因为锁的粒度小，分段锁
					concurrentMap.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
					skipListMap.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));//1100ms
					hashTableMap.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));//723ms
					hashMap.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));//549ms
				}
				latch.countDown();
			});
		}
		Arrays.asList(ths).forEach((th)->{
			th.start();
		});
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		
	}
}
