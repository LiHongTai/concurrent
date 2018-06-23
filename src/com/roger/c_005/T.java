package com.roger.c_005;

public class T implements Runnable {

	private int count = 10;

	/**
	 * 如果不加入关键字 synchronized 则会出现线程重入的情况，出现 数据不一致情况
	 * Thread-0 count = 8 
	 * Thread-3 count = 6
	 * Thread-2 count = 7 
	 * Thread-1 count = 8 
	 * Thread-4 count = 5
	 */
	@Override
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void main(String[] args) {
		T t = new T();

		for (int i = 0; i < 5; i++) {
			new Thread(t, "Thread-" + i).start();
		}
	}
}
