package com.roger.c_023;
/**
 * 有N张火车票，每张票都有一个编号
 * 
 * 同时有10个窗口对外售票
 * 
 * 
 * @author Roger.Li
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {

	static List<String> tickets = new ArrayList<>();

	static {
		for (int i = 1; i <= 10000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					//把size和remove整合成一个原子操作中去
					synchronized (tickets) {
						if (tickets.size() <= 0) {
							break;
						}
						try {
							TimeUnit.MILLISECONDS.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("销售了:" + tickets.remove(0));
					}
				}
			}).start();
		}
	}

}
