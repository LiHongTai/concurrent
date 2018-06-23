package com.roger.c_025;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 分叉合并
 * @author Roger.Li
 */
public class T07_ForkJoinPool {

	static int[] nums = new int[1000000];
	static final int MAX_NUM  = 50000;
	static Random r = new Random();
	
	static {
		for(int i = 0; i < nums.length;i ++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println("静态代码块得到的结果:"+Arrays.stream(nums).sum());
	}
	
	/*static class AddTask extends RecursiveAction{

		int start,end;
		
		AddTask(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		protected void compute() {
			if((end - start)<=MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				System.out.println("from:"+start + " to:" + end + " = " + sum);
				return;
			}
		
			int middle = start + (end -start)/2;
			AddTask subTask1 = new AddTask(start, middle);
			AddTask subTask2 = new AddTask(middle, end);
			subTask1.fork();
			subTask2.fork();
		}
		
	}*/
	
	static class AddTask extends RecursiveTask<Long>{
		private static final long serialVersionUID = -7102869090875897730L;
		
		int start,end;
		
		AddTask(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		protected Long compute() {
			if((end - start)<=MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				return sum;
			}
		
			int middle = start + (end -start)/2;
			AddTask subTask1 = new AddTask(start, middle);
			AddTask subTask2 = new AddTask(middle, end);
			subTask1.fork();
			subTask2.fork();
			
			return subTask1.join() + subTask2.join();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		
		forkJoinPool.execute(task);
		
		Long result = task.join();//阻塞
		System.out.println("通过forkJoin得到的结果:" + result);
		
		//System.in.read();//阻塞
	}
}
