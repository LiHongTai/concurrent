package com.roger.c_007;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁 
 * 对业务读方法不加锁
 * 会出现脏读
 * 
 * @author Roger.Li
 */
public class Account {
	String name;
	double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}

	public /*synchronized*/ double get(String name) {
		return this.balance;
	}

	public static void main(String[] args) {
		Account a = new Account();

		new Thread(() -> a.set("Roger", 100)).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(a.get("roger"));

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(a.get("roger"));
	}
}
