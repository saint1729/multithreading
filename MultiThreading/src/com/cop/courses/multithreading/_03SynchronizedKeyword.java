package com.cop.courses.multithreading;

public class _03SynchronizedKeyword {
	
	private int count = 0;
	
	public synchronized void incrementCount() {
		this.count += 1;
	}
	
	public static void main(String[] args) {
		
		_03SynchronizedKeyword sk = new _03SynchronizedKeyword();
		
		sk.doSynchronize();
	
	}
	
	
	public void doSynchronize() {
		
		Thread t1 = new Thread(
						new Runnable() {
							public void run() {
//								try {
//									Thread.sleep(1000);
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}
								for(int i = 0; i < 10000; i++) {
									incrementCount();
								}
							}
						}
					);
		Thread t2 = new Thread(
						new Runnable() {
							public void run() {
//								try {
//									Thread.sleep(1000);
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}
								for(int i = 0; i < 10000; i++) {
									incrementCount();
								}
							}
						}
					);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(count);
		
	}
	
	
}
