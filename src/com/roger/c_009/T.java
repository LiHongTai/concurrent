package com.roger.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 子类同步方法是可以调用父类的同步方法
 * 
 * @author Roger.Li
 */
public class T {
	
	synchronized void parent_m() {
		System.out.println("parent sync m start...");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("parent sync m end...");
	}
	
	public static void main(String[] args) {
		new TT().child_m();
	}
}

class TT extends T{
	
	synchronized void child_m() {
		System.out.println("child sync m start...");
		super.parent_m();
		System.out.println("child sync m end...");
	}
}