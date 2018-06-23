package com.roger.c_014;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法，使用AtomicXXX类
 * AtomicXXX类本身方法保证都是原子性的，但不能保证多个方法连续调用是原子性的
 * @author Roger.Li
 */
public class T {

    AtomicInteger count = new AtomicInteger(0);

	void m() {
		for (int i = 0; i < 10000; i++) {
			//if count.get() < 1000
			//在count.get()和count.incrementAndGet()之间存在问题
			//A线程在count.get()=999时进入 在还未执行count.incrementAndGet()之前
			//B线程也进行判断这时count.get()仍然是999
			//这样线程A和线程B都对count进行了+1操作，导致count最终结果是1002，或者其他大于1000的数字
			//if(count.get() < 1000) {
				count.incrementAndGet();//count++不具备原子性的
			//}
		}
	}

	public static void main(String[] args) {
		T t = new T();

		List<Thread> threadList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			threadList.add(new Thread(t::m, "thread-" + i));
		}

		threadList.forEach((threadInstance) -> threadInstance.start());

		threadList.forEach((threadInstance) -> {
			try {
				threadInstance.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.count);
	}
}
