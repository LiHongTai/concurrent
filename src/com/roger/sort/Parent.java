package com.roger.sort;

public class Parent {
	
	static {
		System.out.println("Parent static block execute....");
	}
	
	{
		System.out.println("Parent not static block execute....");
	}
	
	public Parent() {
		System.out.println("Parent constructor method execute...");
	}
	
	public void m1() {
		System.out.println("Parent method m1 execute...");
	}
}
