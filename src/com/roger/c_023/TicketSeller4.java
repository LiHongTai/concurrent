package com.roger.c_023;
/**
 * 有N张火车票，每张票都有一个编号
 * 
 * 同时有10个窗口对外售票
 * 
 * Queue：并发容器
 * 
 * @author Roger.Li
 */

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {

	static Queue<String> tickets = new ConcurrentLinkedQueue<>();

	static {
		for (int i = 1; i <= 10000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					//因为这时我们只会取数据，没有对队列进行修改的操作
					String ticketCode = tickets.poll();
					
					if(ticketCode == null)
						break;
					
					System.out.println("销售了:" + ticketCode);
				}
			}).start();
		}
	}

}
