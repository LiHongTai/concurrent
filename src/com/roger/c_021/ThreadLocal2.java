package com.roger.c_021;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 线程局部变量
 * 		每个线程有各自的ThreadLocal，在各自线程中修改自己的ThreadLocal，对其他线程中的ThreadLocal对象互不影响，空间换取时间
 * @author Roger.Li
 */
public class ThreadLocal2 {

	//volatile static Person p = new Person();
	static ThreadLocal<Person> threadLocal = new ThreadLocal<>();
	
	public static void main(String[] args) {
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadLocal.get());
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//这里修改了threadLocal变量，对上面的线程中的threadLocal没有任何影响
			threadLocal.set(new Person());
		}).start();
		
	}
	
	static class Person{
		String name = "zhangsan";
	}
}

