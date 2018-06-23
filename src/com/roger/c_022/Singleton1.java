package com.roger.c_022;

/**
 * 饱汉模式缺点：
 * 	类加载的时候，就直接new一个对象，如果这样的对象比较多的时候，导致程序启动缓慢
 * @author Roger.Li
 */
public class Singleton1 {
	
	//直接定义一个对象
	private static Singleton1 instance = new Singleton1();
	//定义一个私有的构造方法，不允许外部创建
	private Singleton1() {
		
	}
	// 提供外部访问得到对象的方法
	public static Singleton1 getInstance() {
		return instance;
	}
}
