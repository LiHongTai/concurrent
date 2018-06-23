package com.roger.c_022;

import java.util.Arrays;

/**
 *  
 *  使用内部类 既不加锁，也能实现懒加载
 *  
 * 	因为 内部类是延时加载的，也就是说只会在第一次使用时加载。不使用就不加载，所以可以很好的实现单例模式。
 *  
 * @author Roger.Li
 */
public class Singleton4 {
	
	//使用内部类的方法定义实例
	private static class Inner{
		private static Singleton4 instance = new Singleton4();
		
		static {
			System.out.println("Singleton4 inner class static block...");
		}
	}
	
	//定义一个私有的构造方法，不允许外部创建
	private Singleton4() {
		System.out.println("Singleton4 constructor method...");
	}
	// 提供外部访问得到对象的方法
	public static Singleton4 getInstance() {
		return Inner.instance;
	}
	
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[200];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				Singleton4.getInstance();
			});
		}
		
		Arrays.asList(threads).forEach((th)->{
			th.start();
		});
		
	}
}
