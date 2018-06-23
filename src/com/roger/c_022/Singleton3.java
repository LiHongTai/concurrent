package com.roger.c_022;

/**
 * 饱汉模式缺点：
 * 	类加载的时候，就直接new一个对象，如果这样的对象比较多的时候，导致程序启动缓慢
 * 
 *  解决办法：使用延迟加载 在第一次使用的时候，在创建对象，即懒汉模式
 *  
 * @author Roger.Li
 */
public class Singleton3 {
	
	//使用延迟加载的方式
	private static Singleton3 instance ;
	//定义一个私有的构造方法，不允许外部创建
	private Singleton3() {
		
	}
	// 提供外部访问得到对象的方法
	//使用双重锁机制
	public synchronized static Singleton3 getInstance() {
		if(instance == null) {
			synchronized (Singleton3.class) {
				if(instance == null) {
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
}
