package com.roger.c_018;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 * 实现一个容器，提供两个方法，add，size 
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数达到5个是，线程2输出提示并结束
 * 
 * @author Roger.Li
 */
public class MyContainer1 {

	List<Object> objList = new ArrayList<>();

	public void add(Object obj) {
		objList.add(obj);
	}

	public int size() {
		return objList.size();
	}

	public static void main(String[] args) {
		MyContainer1 myContainer1 = new MyContainer1();
		//线程1 添加10个元素
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				myContainer1.add(new Object());
				System.out.println("add " + i);
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();
		//线程2监控元素个数
		new Thread(() -> {
			while(true) {
				if(myContainer1.size() == 5)
					break;
			}
			System.out.println("t2 结束");
		}, "t2").start();
	}

}
