package com.cop.courses.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor3 implements Runnable {
	
	private CountDownLatch countDownLatch;
	
	public Processor3(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
	
	public void run() {
		System.out.println("Started");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countDownLatch.countDown();
	}
}


public class _06CountdownLatches {
	
	
	public static void main(String[] args) {
	
		CountDownLatch countDownLatch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 3; i++) {
			executor.submit(new Processor3(countDownLatch));
		}
		
		executor.shutdown();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed");
		
	}

}
