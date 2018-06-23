package com.roger.c_025;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T09_ParallelStreamAPI {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 10000; i++) {
			nums.add(1000000 + r.nextInt(1000000));
		}

		long startTime = System.currentTimeMillis();
		nums.forEach(num -> isPrime(num));
		long endime = System.currentTimeMillis();
		System.out.println("常规方法:" + (endime - startTime));
		
		
		startTime = System.currentTimeMillis();
		//默认使用多线程
		nums.parallelStream().forEach(T09_ParallelStreamAPI::isPrime);
		endime = System.currentTimeMillis();
		System.out.println("使用ParallelStream方法:" + (endime - startTime));
	}

	static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
