package com.cop.courses.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class _04Worker {
	
	private Random random = new Random();
	
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	
	
	public void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		list1.add(random.nextInt(100));
	}
	
	
	public void main() {
		System.out.println("Hello");
	}
	
}
