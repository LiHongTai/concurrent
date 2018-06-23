package com.roger.c_025;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 并行计算:
 * 	为什么不平均:
 * 		因为数越大，计算质数花费的时间越长
 * @author Roger.Li
 */
public class T03_ParallelComputing {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long startTime = System.currentTimeMillis();
		getPrime(1,200000);
		long endTime = System.currentTimeMillis();
		System.out.println("直接调用方法：getPrime花费 " + (endTime-startTime) + "ms");
		
		
		final int cpuCoreNum = 4;
		
		ExecutorService executorService = Executors.newFixedThreadPool(cpuCoreNum);
		MyTask t1 = new MyTask(1, 80000);
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);
		
		Future<List<Integer>> f1 = executorService.submit(t1);
		Future<List<Integer>> f2 = executorService.submit(t2);
		Future<List<Integer>> f3 = executorService.submit(t3);
		Future<List<Integer>> f4 = executorService.submit(t4);
		
		startTime = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		executorService.shutdown();
		endTime = System.currentTimeMillis();
		System.out.println("采用线程池分段调用：getPrime花费 " + (endTime-startTime) + "ms");
	}
	
	static class MyTask implements Callable<List<Integer>>{
		
		int startPos,endPos;
		
		public MyTask(int startPos, int endPos) {
			this.startPos = startPos;
			this.endPos = endPos;
		}

		@Override
		public List<Integer> call() throws Exception {
			return getPrime(startPos,endPos);
		}
		
	}
	
	static boolean isPrime(int num) {
		for(int i = 2; i <= num/2; i ++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private static List<Integer> getPrime(int startPos, int endPos) {
		List<Integer> results = new ArrayList<>();
		for (int i = startPos; i <= endPos; i++) {
			if(isPrime(i)) {
				results.add(i);
			}
		}
		return results;
	}
}
