package com.cop.courses.multithreading;

import java.util.Scanner;

class Processor4 {
	
	public void produce() throws InterruptedException {
		
		synchronized(this) {
			System.out.println("Producer thread running ....");
			wait();
			System.out.println("Resumed");
		}
		
	}
	
	public void consume() throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized(this) {
			System.out.println("Consumer thread. Enter a new line.");
			scanner.nextLine();
			System.out.println("New line entered");
			notify();
			Thread.sleep(5000);
		}
	}
	
}

public class _08WaitAndNotify {
	
	public static void main(String[] args) {
		
		Processor4 processor = new Processor4();
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("End");
		
	}
	
}