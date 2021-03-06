package com.cop.courses.multithreading;

import java.util.Scanner;

class Processor extends Thread {
	
	private volatile boolean running = true;
	
	public void run() {
		while(running) {
			System.out.println("Alpha");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void modifyState() {
		running = false;
	}
	
}

public class _02BasicThreadSynchronization {
	public static void main(String[] args) {
		Processor p = new Processor();
		p.start();
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		p.modifyState();
	}
}