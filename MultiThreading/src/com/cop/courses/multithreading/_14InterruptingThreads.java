package com.cop.courses.multithreading;

import java.util.Random;

public class _14InterruptingThreads {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Starting.");
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				Random random = new Random();
				
				for(int i = 0; i < 1E8; i++) {
					
//					if(Thread.currentThread().isInterrupted()) {
//						System.out.println("Interrupted! i = " + i);
//						break;
//					}
					
					try {
						if(Thread.currentThread().isInterrupted()) {
							throw new InterruptedException("Interrupting");
						}
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
						break;
					}
					
					Math.sin(random.nextDouble());
				}
				
			}
		});
		
		t1.start();
		
		Thread.sleep(1000);
		
		t1.interrupt();

		t1.join();
		
		System.out.println("Finished.");
		
	}
	
}
