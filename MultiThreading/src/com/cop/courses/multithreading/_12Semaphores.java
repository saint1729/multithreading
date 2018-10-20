package com.cop.courses.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


class Connection {
	
	private static Connection instance = new Connection();
	
	private int connections = 0;
	private Semaphore semaphore = new Semaphore(10, true);
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() throws InterruptedException {
		
		this.semaphore.acquire();

		try {
			this.doConnect();
		} finally {
			this.semaphore.release();
		}
		
	}
	
	public void doConnect() throws InterruptedException {
		
		synchronized(this) {
			this.connections += 1;
			System.out.println("Current connections: " + this.connections);
		}
		
		Thread.sleep(2000);
		
		synchronized (this) {
			this.connections -= 1;
		}
		
	}
	
}


public class _12Semaphores {
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 40; i++) {
			executor.submit(new Runnable() {
				public void run() {
					try {
						Connection.getInstance().connect();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		executor.shutdown();
		
		executor.awaitTermination(1, TimeUnit.DAYS);
		
	}
	
}
