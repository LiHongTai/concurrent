package com.roger.c_024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SynchronizedList {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<String> strList = new ArrayList<>();
		List<String> syncStrList = Collections.synchronizedList(strList);
	}
}
