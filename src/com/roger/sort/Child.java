package com.roger.sort;

public class Child extends Parent{
	
	static {
		System.out.println("Child static block execute....");
	}
	
	{
		System.out.println("Child not static block execute....");
	}
	
	public Child() {
		System.out.println("Child constructor method execute...");
	}
	
	@Override
	public void m1() {
		System.out.println("Child override method m1 execute...");
	}
}
