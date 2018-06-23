package com.roger.sort;

/**
 * 加载顺序 
 * 1:父类永远优先于子类 
 * 2:代码块永远优先于构造方法 
 * 3:静态代码块永远优先于非静态代码块,并且静态代码块永远只加载一次（在一个主线程中）
 * 4:方法的执行顺序时谁先调用谁先执行，调用子类重写父类的方法不会执行父类被重写的方法
 * 
 * @author Roger.Li
 */
public class _Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Parent parent = new Child();

		System.out.println("*****************");

		parent = new Child();

		System.out.println("*****************");

		Child child = new Child();
	}
}
